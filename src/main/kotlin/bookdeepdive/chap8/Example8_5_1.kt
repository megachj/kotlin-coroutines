package bookdeepdive.chap8_5_1

import bookdeepdive.log
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

suspend fun main() = coroutineScope {
    example_complete()
    println()

    example_completeExceptionally()
    println()
}

private suspend fun example_complete() = runBlocking {
    val job = Job()

    launch(job) {
        repeat(5) {num ->
            delay(200)
            log.info { "Rep$num" }
        }
    }

    launch {
        delay(500)
        val isComplete = job.complete()
        log.info { "job: $job, $isComplete" }
    }

    job.join()

    launch(job) {
        log.info { "Will not be printed" }
    }

    log.info { "Done" }
}

private suspend fun example_completeExceptionally() = runBlocking {
    val job = Job()

    launch(job) {
        repeat(5) {num ->
            delay(200)
            log.info { "Rep$num" }
        }
    }

    launch {
        delay(500)
        job.completeExceptionally(Error("Some error"))
    }

    job.join()

    launch(job) {
        log.info { "Will not be printed" }
    }

    log.info { "Done" }
}