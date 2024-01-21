package bookdeepdive.chap9_2

import bookdeepdive.log
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

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
            repeat(1000) { i ->
                delay(200)
                log.info { "Printing $i" }
            }
        } catch (e: CancellationException) {
            log.error { e }
            throw e
        }
    }

    delay(1100)
    job.cancelAndJoin()
    log.info { "Cancelled successfully" }
    delay(1000)
}

private suspend fun ex2() = runBlocking {
    val job = Job()
    launch(job) {
        try {
            delay(Random.nextLong(2000))
            log.info { "Done" }
        } finally {
            log.info { "Will always be printed" }
        }
    }

    delay(1000)
    job.cancelAndJoin()
}