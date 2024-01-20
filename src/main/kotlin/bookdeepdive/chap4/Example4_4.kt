package bookdeepdive.chap4

import kotlinx.coroutines.delay
import mu.KotlinLogging

private val log = KotlinLogging.logger {  }
suspend fun main() {
    a()
}

private suspend fun a() {
    log.info { "Before" }
    b()
    b()
    b()
    log.info { "After" }
}

private suspend fun b() {
    for (i in 1..10) {
        c(i)
    }
}

private suspend fun c(i: Int) {
    delay(i * 100L)
    log.info { "Tick - $i" }
}