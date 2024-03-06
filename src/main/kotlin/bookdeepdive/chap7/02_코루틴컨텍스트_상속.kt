package bookdeepdive.chap7.p02

import bookdeepdive.log
import bookdeepdive.printLine
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking(CoroutineName("main")) {
    `예제1`()
    printLine("예제1")

    `예제2`()
    printLine("예제2")
}

private fun CoroutineScope.log(msg: String) {
    val name = coroutineContext[CoroutineName]?.name
    log.info { "[$name] $msg" }
}

private suspend fun `예제1`() = coroutineScope {
    log("Started")
    val v1 = async {
        delay(500)
        log("Running async")
        42
    }
    launch {
        delay(1000)
        log("Running launch")
    }
    log("The answer is ${v1.await()}")
}

private suspend fun `예제2`() = coroutineScope() {
    log("Started")
    val v1 = async(CoroutineName("c1")) {
        delay(500)
        log("Running async")
        42
    }
    launch(CoroutineName("c2")) {
        delay(1000)
        log("Running launch")
    }
    log("The answer is ${v1.await()}")
}