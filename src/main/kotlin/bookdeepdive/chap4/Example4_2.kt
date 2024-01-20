package bookdeepdive.chap4

import kotlinx.coroutines.delay
import mu.KotlinLogging

private val log = KotlinLogging.logger {}
suspend fun main() {
    myFunction()
}

//fun main() = runBlocking {
//    myFunction()
//}

private suspend fun myFunction() {
    log.info { "Before" }
    var counter = 0
    delay(1000)

    counter++
    log.info { "Counter: $counter" }
    log.info { "After" }
}
