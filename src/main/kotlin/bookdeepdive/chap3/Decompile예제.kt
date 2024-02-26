package bookdeepdive.chap3.p99

import bookdeepdive.log
import kotlin.concurrent.thread
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

suspend fun main() {
    log.info { "Before" }

    suspendCoroutine<Unit> { continuation ->
        thread {
            log.info { "Suspended" }
            Thread.sleep(1000)
            continuation.resume(Unit)
            log.info { "Resumed" }
        }
    }

    log.info { "After" }
}