package bookdeepdive.chap9_5

import bookdeepdive.log
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield

suspend fun main() = coroutineScope {
    launch { ex1() }.join()
    println()

    launch { ex2() }.join()
    println()

    launch { ex3() }.join()
    println()

    launch { ex4() }.join()
    println()
}

// 중단점(delay 등)이 없어서 취소가 안된다.
private suspend fun ex1() = coroutineScope {
    val job = Job()
    launch(job) {
        repeat(20) {i ->
            Thread.sleep(200)
            log.info { "Printing $i" }
        }
    }

    delay(1000)
    job.cancelAndJoin()

    log.info { "Cancelled successfully" }
}

// 잡 중간마다 중단점 yield 를 추가해둬서 취소가 가능하다.
private suspend fun ex2() = coroutineScope {
    val job = Job()
    launch(job) {
        repeat(20) { i ->
            Thread.sleep(200)
            yield() // 중단점
            log.info { "Printing $i" }
        }
    }

    delay(1000)
    job.cancelAndJoin()

    log.info { "Cancelled successfully" }
}

// 잡 중간마다 잡 상태를 확인하고, 활성화일 때만 잡을 수행하도록 한다.
private suspend fun ex3() = coroutineScope {
    val job = Job()
    launch(job) {
        do {
            Thread.sleep(200)
            log.info { "Printing" }
        } while (isActive)
    }

    delay(1100)
    job.cancelAndJoin() // 부모 코루틴이 취소되므로 자식 코루틴도 취소된다.

    log.info { "Cancelled successfully" }
}

// 잡 중간마다 ensureActive() 로 활성화 상태인지 검증한다
private suspend fun ex4() = coroutineScope {
    val job = Job()
    launch(job) {
        repeat(1000) { num ->
            Thread.sleep(200)
            ensureActive()
            log.info { "Printing $num" }
        }
    }

    delay(1100)
    job.cancelAndJoin()
    log.info { "Cancelled successfully" }
}
