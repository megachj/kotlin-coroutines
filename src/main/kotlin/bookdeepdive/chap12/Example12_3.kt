package bookdeepdive.chap12_3

import bookdeepdive.log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.Executors
import kotlin.system.measureTimeMillis


suspend fun main() = coroutineScope {
    launch { ex1() }.join()
    println()

    launch { ex2() }.join()
    println()

    launch { ex3() }.join()
    println()
}

private suspend fun ex1() = coroutineScope {
    var i = 0

    // 멀티 스레드를 사용하는 모든 디스패처는 공유 상태로 인한 문제를 생각해야 한다.
    repeat(10_000) {
        launch(Dispatchers.IO) {
            i++
        }
    }
    delay(1000)
    log.info { "멀티스레드 공유 데이터 문제로 인해 결과가 10000 이 아니다. $i" }
}

// 예전에는 싱글 스레드 디스패처를 executors 로 만들었음.
val legacyDispatcher = Executors.newSingleThreadExecutor()
    .asCoroutineDispatcher()

// 최근에는 스레드가 블로킹될 수 있다면 Dispatchers.IO 를 1로 제한해서 사용한다.
val dispatcher = Dispatchers.IO.limitedParallelism(1)
private suspend fun ex2() = coroutineScope {
    // 멀티 스레드 공유 문제를 간단히 해결하는 법은 싱글 스레드로 만드는 것이다.
    var i = 0

    repeat(10000) {
        launch(dispatcher) {
            i++
        }
    }
    delay(1000)
    log.info { i }
}

private suspend fun ex3() = coroutineScope {
    // 싱글 스레드는 블로킹이 되면 작업이 병목이 생기는 단점이 있다.
    val job = Job()
    repeat(5) {
        launch(dispatcher + job) {
            Thread.sleep(1000)
        }
    }
    job.complete()
    val time = measureTimeMillis { job.join() }
    log.info { "Took $time ms" }
}