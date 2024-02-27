package bookdeepdive.chap28.p02

import bookdeepdive.chap28.AnkiConnector
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class AnkiConnectorCallback {
    private val connector = AnkiConnector()
    private val scope = CoroutineScope(SupervisorJob())

    fun checkConnection(
        callback: (Result<Boolean>) -> Unit
    ): Cancellable = toCallback(callback) {
        connector.checkConnection()
    }

    private fun <T> toCallback(
        callback: (Result<T>) -> Unit,
        body: suspend () -> T
    ): Cancellable {
        val job = scope.launch {
            try {
                val result = body()
                callback(Result.success(result))
            } catch (t: Throwable) {
                callback(Result.failure(t))
            }
        }
        return Cancellable(job)
    }

    class Cancellable(private val job: Job) {
        fun cancel() {
            job.cancel()
        }
    }

//    interface CallbackInterface<T> {
//        fun invoke(result: Result<T>)
//    }
}