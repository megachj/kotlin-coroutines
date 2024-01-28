package bookdeepdive.chap12_2

import bookdeepdive.log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

suspend fun main(): Unit = coroutineScope {
    launch {
        // 스레드 풀이 64 개이기 때문에, 100번을 1초씩 블로킹하면 2초가 걸린다.
        printCoroutinesTime(Dispatchers.IO)
    }

    launch {
        // limitedParallelism 함수를 사용하면 독립적인 스레드 풀을 가진 새로운 디스패처를 만든다.
        val dispatcher = Dispatchers.IO.limitedParallelism(100)
        // 스레드 풀이 100 개이기 때문에, 100번을 1초씩 블로킹하면 1초가 걸린다.
        printCoroutinesTime(dispatcher)
    }
}

private suspend fun printCoroutinesTime(
    dispatcher: CoroutineDispatcher
) {
    val test = measureTimeMillis {
        coroutineScope {
            repeat(100) {
                launch(dispatcher) {
                    Thread.sleep(1000)
                }
            }
        }
    }
    log.info { "$dispatcher took: $test" }
}