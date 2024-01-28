package bookdeepdive.chap12_1_1

import bookdeepdive.log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// Dispatchers.Default 와 Dispatchers.IO 는 같은 스레드 풀을 거의 사용한다.
// - Dispatchers.Default: 최소 2개 이상이고, cpu 코어 수의 스레드 개수를 갖는다.
// - Dispatchers.IO: 최소 64개 이상이고, cpu 코어 수의 스레드 개수를 갖는다.
// 예) 스레드 12개이면, Default 12 + IO 64 = 76 개의 스레드 수를 갖는 스레드 풀(DefaultDispatcher-worker) 를 갖게된다.
suspend fun main(): Unit = coroutineScope {
    launch(Dispatchers.Default) {
        log.info { "Dispatchers.Default" }
        withContext(Dispatchers.IO) {
            log.info { "Dispatchers.IO" }
        }
    }
}