package bookdeepdive.chap4

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import mu.KotlinLogging

private val log = KotlinLogging.logger {  }
fun main() = runBlocking {
    myFunction()
}

private suspend fun myFunction() {
    log.info { "Before" }
    delay(1000)
    log.info { "After" }
}
