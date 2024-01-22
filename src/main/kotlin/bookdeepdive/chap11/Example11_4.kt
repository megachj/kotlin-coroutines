package bookdeepdive.chap11

import bookdeepdive.log
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.withTimeout

suspend fun main(): Unit = coroutineScope {
    try {
        test()
    } catch (e: TimeoutCancellationException) {
        log.info { "Cancelled" }
    }
    delay(1000)
}

private suspend fun test(): Int = withTimeout(1500) {
    delay(1000)
    log.info { "Still thinking" }
    delay(1000)
    log.info { "Done" }
    42
}
