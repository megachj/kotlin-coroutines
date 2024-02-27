package bookdeepdive.chap28.p04

import bookdeepdive.chap28.AnkiConnector
import bookdeepdive.log
import kotlinx.coroutines.reactor.mono
import reactor.core.publisher.Mono

class AnkiConnectorReactor {
    private val connector = AnkiConnector()

    fun checkConnection(): Mono<Boolean> {
        return mono {
            log.info { "리액터 함수" }
            connector.checkConnection()
        }
    }
}