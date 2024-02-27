package bookdeepdive.chap28.p03

import bookdeepdive.chap28.AnkiConnector
import bookdeepdive.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.future.future
import java.util.concurrent.CompletableFuture

class AnkiConnectorFuture {
    private val connector = AnkiConnector()
    private val scope = CoroutineScope(SupervisorJob())

    fun checkConnection(): CompletableFuture<Boolean> = scope.future {
        log.info { "퓨처 함수" }
        connector.checkConnection()
    }
}