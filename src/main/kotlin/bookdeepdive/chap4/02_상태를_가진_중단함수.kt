package bookdeepdive.chap4.p02

import bookdeepdive.log
import kotlinx.coroutines.delay

suspend fun main() {
    myFunction()
}

private suspend fun myFunction() {
    log.info { "Before" }
    var counter = 0
    delay(1000)

    counter++
    log.info { "Counter: $counter" }
    log.info { "After" }
}
