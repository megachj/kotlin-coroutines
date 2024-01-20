package bookdeepdive.chap5_1

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mu.KotlinLogging
import kotlin.concurrent.thread

private val log = KotlinLogging.logger {  }
fun main() {
    example1()
    example2()
}

private fun example1() {
    GlobalScope.launch {
        delay(1000L)
        log.info { "World!" }
    }
    GlobalScope.launch {
        delay(1000L)
        log.info { "World!" }
    }
    GlobalScope.launch {
        delay(1000L)
        log.info { "World!" }
    }
    log.info { "Hello, "}
    Thread.sleep(2000L)
}

private fun example2() {
    thread(isDaemon = true) {
        Thread.sleep(1000L)
        log.info { "World!" }
    }
    thread(isDaemon = true) {
        Thread.sleep(1000L)
        log.info { "World!" }
    }
    thread(isDaemon = true) {
        Thread.sleep(1000L)
        log.info { "World!" }
    }
    log.info { "Hello, "}
    Thread.sleep(2000L)
}