package bookdeepdive.chap6.p04

import bookdeepdive.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    this.launch {
        delay(1000L)
        log.info { "World!" }
    }
    this.launch {
        delay(2000L)
        log.info { "World!" }
    }
    log.info { "Hello," }
}