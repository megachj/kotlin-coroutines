package bookdeepdive.chap10_1

import bookdeepdive.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// 코루틴 예외는 자식에서 부모로 전파된다.
// 부모는 취소되면, 자식도 취소되기 때문에 쌍방으로 전파된다.
// 예외 전파가 정지되지 않으면 해당 트리의 모든 코루틴이 취소된다.
fun main(): Unit = runBlocking {
    launch {
        launch {
            delay(1000)
            throw Error("Some error")
        }

        launch {
            delay(2000)
            log.info { "Will not be printed" }
        }

        launch {
            delay(500)
            log.info { "Will be printed" }
        }
    }

    launch {
        delay(2000)
        log.info { "Will not be printed" }
    }
}