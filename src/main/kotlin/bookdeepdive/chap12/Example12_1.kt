package bookdeepdive.chap12_1

import bookdeepdive.log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.random.Random

suspend fun main() = coroutineScope {
    repeat(1000) {
        launch(Dispatchers.Default) {
            List(1000) {
                Random.nextLong()
            }.maxOrNull()

            log.info { "running" }
        }
    }
}