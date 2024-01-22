package bookdeepdive.chap11_1_2

import bookdeepdive.log
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// job 이 취소되어서 아직 끝나지 않은 자식들에 취소가 전파된다.

// 0: main
// 1: job(Parent-launch)
// 2: coroutineScope
// 3: longTask-launch, longTask-launch
fun main() = runBlocking {
    val job = launch(CoroutineName("Parent")) {
        longTask()
    }
    delay(1500)
    job.cancel()
}

private suspend fun longTask() = coroutineScope {
    launch {
        delay(1000)
        val name = coroutineContext[CoroutineName]?.name
        log.info { "[$name] Finished task 1" }
    }
    launch {
        delay(2000)
        val name = coroutineContext[CoroutineName]?.name
        log.info { "[$name] Finished task 2" }
    }
}