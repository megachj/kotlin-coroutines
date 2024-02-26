package bookdeepdive.chap3.p02

import bookdeepdive.log
import bookdeepdive.printLine
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

suspend fun main() {
    중단지점에서_중단되고_잠시후_재개되는_예제1()
    printLine()

    중단지점에서_중단되고_잠시후_재개되는_예제2_함수추출()
    printLine()

    중단지점에서_중단되고_잠시후_재개되는_예제3_executor()
    printLine()

    중단지점에서_중단되고_잠시후_재개되는_예제4_delay구현()
    printLine()
}

private suspend fun 중단지점에서_중단되고_잠시후_재개되는_예제1() {
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

private suspend fun 중단지점에서_중단되고_잠시후_재개되는_예제2_함수추출() {
    log.info { "Before" }

    suspendCoroutine<Unit> { continuation ->
        continueAfterSecond(continuation)
    }

    log.info { "After" }
}
private fun continueAfterSecond(continuation: Continuation<Unit>) {
    thread {
        Thread.sleep(1000)
        continuation.resume(Unit)
    }
}

private suspend fun 중단지점에서_중단되고_잠시후_재개되는_예제3_executor() {
    log.info { "Before" }

    suspendCoroutine<Unit> { continuation ->
        executor.schedule({
            continuation.resume(Unit)
        }, 1000, TimeUnit.MILLISECONDS)
    }

    log.info { "After" }
}
private val executor = Executors.newSingleThreadScheduledExecutor {
    Thread(it, "scheduler").apply { isDaemon = true }
}

private suspend fun 중단지점에서_중단되고_잠시후_재개되는_예제4_delay구현() {
    log.info { "Before" }

    delay(1000)

    log.info { "After" }
}
private suspend fun delay(timeMillis: Long): Unit =
    suspendCoroutine { cont ->
        executor.schedule({
            cont.resume(Unit)
        }, timeMillis, TimeUnit.MILLISECONDS)
    }