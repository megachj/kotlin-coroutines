package bookdeepdive.chap9_4

import bookdeepdive.log
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
    val job = launch {
        delay(1000)
    }
    job.invokeOnCompletion { exception: Throwable? ->
        log.info { "Finished" }
    }
    delay(400)
    job.cancelAndJoin()
}

private suspend fun ex2() = runBlocking {
    val job = launch {
        delay(Random.nextLong(2400))
        log.info { "Finished" }
    }
    delay(800)

    job.invokeOnCompletion { exception: Throwable? ->
        log.info { "Will always be printed" }
        log.info { "The exception was: $exception" }
    }
    delay(800)

    job.cancelAndJoin()
}