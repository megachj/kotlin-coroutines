package bookdeepdive.chap11_1_1

import bookdeepdive.log
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// 0: Main-Parent
// 1: coroutineScope
// 2: launch, launch
fun main() = runBlocking(CoroutineName("Parent")) {
    log.info { "Before" }
    longTask()
    log.info { "After" }
}

private suspend fun longTask() = coroutineScope {
    launch {
        delay(1000)
        val name = coroutineContext[CoroutineName]?.name
        log.info { "[$name] Finished task 1" }
    }
    launch {
        delay(2000)
        val name = coroutineContext[CoroutineName]?.name
        log.info { "[$name] Finished task 2" }
    }
}