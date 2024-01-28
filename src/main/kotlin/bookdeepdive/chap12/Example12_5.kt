package bookdeepdive.chap12_5

import bookdeepdive.log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume

suspend fun main(): Unit = withContext(newSingleThreadContext("Thread1")) {
    var continuation: Continuation<Unit>? = null

    launch(newSingleThreadContext("Thread2")) {
        delay(1000)
        continuation?.resume(Unit)
    }

    // 제한받지 않는 디스패처
    // TODO(sunset): 무슨 코드인지 잘 모르겠음.
    launch(Dispatchers.Unconfined) {
        log.info { "Unconfined step1" }

        suspendCancellableCoroutine<Unit> {
            continuation = it
        }

        log.info { "Unconfined step2" }

        delay(1000)

        log.info { "Unconfined step3" }
    }
}