package bookdeepdive.chap10_2_5

import bookdeepdive.log
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main(): Unit = runBlocking {
    // 아래 트리 구조라서 예외가 전파된다.
    // 0: supervisorJob
    // 1: withContext
    // 2: launch, launch
    withContext(SupervisorJob()) {
        launch {
            delay(1000)
            throw Error("Some error")
        }

        launch {
            delay(2000)
            log.info { "Will not be printed" }
        }
    }
    delay(1000)
    log.info { "Done" }
}

