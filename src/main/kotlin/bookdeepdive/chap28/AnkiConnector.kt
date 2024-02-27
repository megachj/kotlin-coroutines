package bookdeepdive.chap28

import kotlinx.coroutines.delay

class AnkiConnector {
    suspend fun checkConnection(): Boolean {
        delay(100)
        return true
    }
}