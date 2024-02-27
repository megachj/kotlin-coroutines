package bookdeepdive.chap28.p01

import bookdeepdive.chap28.AnkiConnector
import kotlinx.coroutines.runBlocking

class AnkiConnectorBlocking {
    private val connector = AnkiConnector()

    fun checkConnection(): Boolean = runBlocking {
        connector.checkConnection()
    }
}
