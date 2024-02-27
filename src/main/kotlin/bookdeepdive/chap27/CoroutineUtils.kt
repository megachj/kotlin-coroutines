package bookdeepdive.chap27.etc1

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.job
import kotlinx.coroutines.selects.select
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.sync.withPermit

// ------------------------------ 비동기맵
suspend fun <T, R> List<T>.mapAsync(
    transformation: suspend (T) -> R
): List<R> = coroutineScope {
    this@mapAsync.map { async { transformation(it) } }.awaitAll()
}

// 동시처리 수를 제한한 비동기 맵
// TODO: RateLimiter 라이브러리 사용해, TPS 를 제한하도록 만들기
suspend fun <T, R> List<T>.mapAsync(
    concurrencyLimit: Int = Int.MAX_VALUE,
    transformation: suspend (T) -> R
): List<R> = coroutineScope {
    // 동시에 실행될 수 있는 수를 concurrencyLimit 로 제한
    val semaphore = Semaphore(concurrencyLimit)
    this@mapAsync.map {
        async {
            semaphore.withPermit {
                transformation(it)
            }
        }
    }.awaitAll()
}

// ------------------------------ 지연 초기화
fun <T> suspendLazy(
    initializer: suspend () -> T
): suspend () -> T {
    var initializer: (suspend () -> T)? = initializer // 초기화가 끝나면 null 설정하여 람다식 해제(메모리 누수 방지)
    val mutex = Mutex() // 오직 하나의 프로세스만 초기화시키기 위해서 Mutex 사용
    var holder: Any? = Any() // 데이터

    return {
        if (initializer == null) holder as T
        else mutex.withLock {
            initializer?.let {
                holder = it()
                initializer = null
            }
            holder as T
        }
    }
}

// ------------------------------ 코루틴 경합: 여러 프로세스중 먼저 끝나는 것 결과 기다리는 함수
suspend fun <T> raceOf(
    racer: suspend CoroutineScope.() -> T,
    vararg racers: suspend CoroutineScope.() -> T
): T = coroutineScope {
    select {
        (listOf(racer) + racers).forEach { racer ->
            async { racer() }.onAwait {
                coroutineContext.job.cancelChildren()
                it
            }
        }
    }
}

// 이해하기 쉽게 2개만 경합하는 메소드 구현
suspend fun <T> raceOf(
    racer1: suspend CoroutineScope.() -> T,
    racer2: suspend CoroutineScope.() -> T,
): T = coroutineScope {
    select<T> {
        async { racer1() }.onAwait { it }
        async { racer2() }.onAwait { it }
    }.also {
        // select 가 값을 생성한 뒤 스코프를 명시적으로 취소한다.
        this.coroutineContext.cancelChildren()
    }
}

// 이렇게 하면 안되나?
//suspend fun <T> raceOf(
//    vararg racers: suspend CoroutineScope.() -> T
//): T = coroutineScope {
//    select {
//        racers.forEach { racer ->
//            async { racer() }.onAwait {
//                coroutineContext.job.cancelChildren()
//                it
//            }
//        }
//    }
//}

// ------------------------------ 중단 가능한 프로세스 재시작하기
inline fun <T> retryWhen(
    predicate: (Throwable, retries: Int) -> Boolean,
    operation: () -> T
): T {
    var retries = 0
    var fromDownstream: Throwable? = null
    while(true) {
        try {
            return operation()
        } catch (e: Throwable) {
            if (fromDownstream != null) {
                // 예외 스택 트레이스 연결(재시도하며 발생했던 예외들을 모두 한 스택 트레이스로 보여주기 위함)
                e.addSuppressed(fromDownstream)
            }
            fromDownstream = e
            // 코루틴 취소 과정에 영향을 주면 안되기 때문에 취소 예외는 재시도하지 않는다.
            // 재시도 조건이 만족하지 않으면 재시도하지 않는다.
            if (e is CancellationException || !predicate(e, retries++)) {
                throw e
            }
        }
    }
}