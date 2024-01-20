package bookdeepdive.chap6_5

import bookdeepdive.log
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun main(): Unit = coroutineScope {
    launch {
        delay(1000L)
        log.info { "World!" }
    }
    log.info { "Hello," }
}
