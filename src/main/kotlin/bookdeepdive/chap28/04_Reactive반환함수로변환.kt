package bookdeepdive.chap28.p04

import bookdeepdive.chap28.AnkiConnector
import bookdeepdive.log
import kotlinx.coroutines.future.future
import reactor.core.publisher.Mono
import java.util.concurrent.CompletableFuture

class AnkiConnectorReactive {
    private val connector = AnkiConnector()

    fun checkConnection(): Mono<Boolean> = Mono {
        log.info { "퓨처 함수" }
        connector.checkConnection()
    }
}