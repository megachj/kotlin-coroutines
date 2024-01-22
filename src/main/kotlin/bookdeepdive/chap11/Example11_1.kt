package bookdeepdive.chap11_1

import bookdeepdive.log
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

// 0: main runblocking
// 1: coroutineScope, coroutineScope
fun main() = runBlocking {
    val a = coroutineScope {
        delay(1000)
        10
    }
    log.info { "a is calculated" }
    val b = coroutineScope {
        delay(1000)
        20
    }
    log.info { "$a, $b" }
}