package bookdeepdive.chap5_2_1

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import mu.KotlinLogging

private val log = KotlinLogging.logger {  }
fun main() = runBlocking {
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
    log.info { "Hello," }
    delay(2000L) // 여전히 필요
}