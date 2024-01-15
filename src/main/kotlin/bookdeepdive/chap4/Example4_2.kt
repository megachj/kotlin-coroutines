package bookdeepdive.chap4

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import mu.KotlinLogging

private val log = KotlinLogging.logger {}
fun main() = runBlocking {
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
