package bookdeepdive.chap27.p05

import bookdeepdive.chap27.etc1.retryWhen
import bookdeepdive.log
import kotlinx.coroutines.delay

suspend fun main() {
    try {
        val result = requestWithRetry()
    } catch (e: Throwable) {
        log.error { "예외: $e" }
        e.printStackTrace()
    }
}

private suspend fun requestWithRetry() = retryWhen(
    predicate = { e, retries ->
        delay(1000)
        val proceed = retries < 3
        if (proceed && retries > 0) {
            log.error { "Retry($retries).." }
        }
        proceed
    }
) {
    requestData()
}
private suspend fun requestData(): String {
    delay(1000)
    throw Error()
}