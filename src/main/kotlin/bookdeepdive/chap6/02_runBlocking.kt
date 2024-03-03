package bookdeepdive.chap6.p02

import bookdeepdive.log
import bookdeepdive.printLine
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() {
    example1()
    printLine("예제 1")

    example2()
    printLine("예제 2")
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
    runBlocking {
        delay(10_000L)
        log.info { "World!" }
    }
    log.info { "Hello, " }
}
