package bookdeepdive.chap27.p04

import bookdeepdive.chap27.etc1.raceOf
import bookdeepdive.log
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

suspend fun main() = coroutineScope {
    log.info { "Start!" }

    val result1 = raceOf({b()}, {c()})
    log.info { "$result1" }

    val result2 = raceOf({c()}, {b()}, {a()})
    log.info { "$result2" }
}

private suspend fun a(): String {
    delay(1000)
    return "A"
}

private suspend fun b(): String {
    delay(2000)
    return "B"
}

private suspend fun c(): String {
    delay(3000)
    return "C"
}