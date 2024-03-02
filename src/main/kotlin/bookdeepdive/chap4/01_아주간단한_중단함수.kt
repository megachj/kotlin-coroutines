package bookdeepdive.chap4.p01

import bookdeepdive.log
import kotlinx.coroutines.delay

suspend fun main() {
    myFunction()
}

private suspend fun myFunction() {
    log.info { "Before" }
    delay(1000)
    log.info { "After" }
}
