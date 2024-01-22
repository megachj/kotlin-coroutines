package bookdeepdive.chap10_2_4

import bookdeepdive.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope

fun main(): Unit = runBlocking {
    // 코루틴 빌더를 supervisorScope 로 래핑한다.
    // 다른 코루틴에서 발생한 예외를 무시하고 부모와의 연결을 유지한다는 점에서 편리하다.
    supervisorScope {
        launch {
            delay(1000)
            throw Error("Some error")
        }

        launch {
            delay(2000)
            log.info { "Will be printed" }
        }
    }
    delay(1000)
    log.info { "Done" }
}