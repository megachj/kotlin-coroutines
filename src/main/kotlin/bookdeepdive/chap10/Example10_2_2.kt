package bookdeepdive.chap10_2_2

import bookdeepdive.log
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    // 아래 트리 구조가 되어서, 2-0 launch 에서 에러가 나도 1, 2-1 번이 모두 취소가 된다.
    // 0: SupervisorJob
    // 1: launch
    // 2: launch, launch
    launch(SupervisorJob()) {
        launch {
            delay(1000)
            throw Error("Some error")
        }

        launch {
            delay(2000)
            log.info { "Will not be printed" }
        }
    }

    delay(3000)
}