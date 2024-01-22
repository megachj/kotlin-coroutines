package bookdeepdive.chap10_2_1

import bookdeepdive.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// SupervisorJob 은 예외를 전파시키지 않는다.
fun main(): Unit = runBlocking {
    val scope = CoroutineScope(SupervisorJob())
    scope.launch {
        delay(1000)
        throw Error("Some error")
    }

    scope.launch {
        delay(2000)
        log.info { "Will be printed" }
    }

    delay(3000)
}