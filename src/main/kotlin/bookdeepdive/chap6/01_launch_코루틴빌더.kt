package bookdeepdive.chap6.p01

import bookdeepdive.log
import bookdeepdive.printLine
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

suspend fun main() {
    example1()
    printLine("예제1")

    example2()
    printLine("예제2")
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

private suspend fun example2() = runBlocking {
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