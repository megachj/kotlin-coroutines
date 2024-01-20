package bookdeepdive.chap4

import kotlinx.coroutines.delay
import mu.KotlinLogging

private val log = KotlinLogging.logger {  }

// main 스레드: Before
// kotlinx.coroutines.DefaultExecutor 스레드: After
suspend fun main() {
    myFunction()
}

// main 스레드: Before
// main 스레드: After
//fun main() = runBlocking {
//    myFunction()
//}

private suspend fun myFunction() {
    log.info { "Before" }
    delay(1000)
    log.info { "After" }
}
