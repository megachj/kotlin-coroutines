package bookdeepdive.chap7_2

import bookdeepdive.log
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

private fun CoroutineScope.log(msg: String) {
    val name = coroutineContext[CoroutineName]?.name
    log.info { "[$name] $msg" }
}

fun main() = runBlocking(CoroutineName("main")) {
    `예제1`()
    println()

    `예제2`()
    println()
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