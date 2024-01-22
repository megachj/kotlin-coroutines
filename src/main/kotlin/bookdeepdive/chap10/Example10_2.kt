package bookdeepdive.chap10_2

import bookdeepdive.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// 코루틴 예외는 try-catch 문을 래핑해서 처리할 수 없다.
fun main(): Unit = runBlocking {
    try {
        launch {
            delay(1000)
            throw Error("Some error")
        }
    } catch (e: Throwable) {
        log.info { "Will not be printed" }
    }

    launch {
        delay(2000)
        log.info { "Will not be printed" }
    }
}

