package bookdeepdive.chap7_2_1

import bookdeepdive.log
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext

private suspend fun printName() {
    val coroutineName = coroutineContext[CoroutineName]?.name
    log.info { "$coroutineName" }
}

suspend fun main() = withContext(CoroutineName("Outer")) {
    printName()
    launch(CoroutineName("Inner")) {
        printName()
    }
    delay(10)
    printName()
}