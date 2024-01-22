package bookdeepdive.chap11_4_1

import bookdeepdive.log
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout

suspend fun main(): Unit = coroutineScope {
    launch {
        launch {
            delay(2000)
            log.info { "Will not be printed" }
        }
        withTimeout(1000) {
            delay(1500)
        }
    }
    launch {
        delay(2000)
        log.info { "Done!" }
    }
}