package bookdeepdive.chap5_4

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// runBlocking 의 CoroutineScope 로 연결이 되어서 부모-자식 관계의 코루틴이 되었다.
fun main() = runBlocking {
    this.launch {
        delay(1000L)
        bookdeepdive.log.info { "World!" }
    }
    this.launch {
        delay(2000L)
        bookdeepdive.log.info { "World!" }
    }
    bookdeepdive.log.info { "Hello," }
}