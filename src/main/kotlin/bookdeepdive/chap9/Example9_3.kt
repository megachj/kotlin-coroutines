package bookdeepdive.chap9_3

import bookdeepdive.log
import kotlinx.coroutines.Job
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

suspend fun main() = coroutineScope {
    ex1()
    println()

    ex2()
    println()
}

private suspend fun ex1() = runBlocking {
    val job = Job()
    launch(job) {
        try {
            delay(2000)
            log.info { "Job is done" }
        } finally {
            log.info { "Finally" }
            launch {
                // 무시된다.
                log.info { "Will not be printed" }
            }
            delay(1000) // 여기서 예외가 발생함.
            log.info { "Will not be printed" }
        }
    }

    delay(1000)
    job.cancelAndJoin()
    log.info { "Cancel done" }
}

private suspend fun ex2() = runBlocking {
    val job = Job()
    launch(job) {
        try {
            delay(200)
            log.info { "Coroutine finished" }
        } finally {
            log.info { "Finally" }
            withContext(NonCancellable) {
                delay(1000)
                log.info { "Cleanup done" }
            }
        }
    }

    delay(100)
    job.cancelAndJoin()
    log.info { "Done" }
}