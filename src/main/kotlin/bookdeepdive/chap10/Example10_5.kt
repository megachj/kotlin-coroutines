package bookdeepdive.chap10_5

import bookdeepdive.log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    val handler = CoroutineExceptionHandler { ctx, exception ->
        log.error { "Caught $exception" }
    }

    // 1-0 에서 예외가 발생하면 0 으로 예외 전파된다.
    // 0 은 supervisorJob 이므로 무시되는데, 예외 handler가 있어서 에러로깅된다.

    // 아래 트리 구조
    // 0: supervisorJob+handler
    // 1: launch, launch
    val scope = CoroutineScope(SupervisorJob() + handler)
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