package bookdeepdive.chap9_1

import bookdeepdive.log
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

suspend fun main() = coroutineScope {
    ex1()
    println()

    ex2()
    println()
}

private suspend fun ex1() = runBlocking {
    val job = launch {
        repeat(1000) { i ->
            delay(200)
            log.info { "Printing $i" }
        }
    }

    delay(1100)

//    job.cancel()
//    job.join()
    job.cancelAndJoin()

    log.info { "Cancelled successfully" }
}

private suspend fun ex2() = runBlocking {
    val job = launch {
        repeat(1000) { i ->
            delay(100)
            Thread.sleep(100) // 오래 걸리는 연산으로 가정
            log.info { "Printing $i" }
        }
    }

    delay(1000)
    job.cancel()
    // join 이 없으면 경쟁 상태(race condition)가 될 수 있다. 즉 'Cancelled successfully' 가 출력된 후 'Printing $i' 가 출력될 수 있음.
    // job.join()
    log.info { "Cancelled successfully" }
}

