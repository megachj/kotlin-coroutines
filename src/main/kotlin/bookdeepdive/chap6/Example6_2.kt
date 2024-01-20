package bookdeepdive.chap6_2

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import mu.KotlinLogging

private val log = KotlinLogging.logger {  }
fun main() {
    example1()
    example2()
}

private fun example1() {
    runBlocking {
        delay(1000L)
        log.info { "World!" }
    }
    runBlocking {
        delay(1000L)
        log.info { "World!" }
    }
    runBlocking {
        delay(1000L)
        log.info { "World!" }
    }
    log.info { "Hello, " }
}

private fun example2() {
    Thread.sleep(1000L)
    log.info { "World!" }
    Thread.sleep(1000L)
    log.info { "World!" }
    Thread.sleep(1000L)
    log.info { "World!" }
    log.info { "Hello, " }
}