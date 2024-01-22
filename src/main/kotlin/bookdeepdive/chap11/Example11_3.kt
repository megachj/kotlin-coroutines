package bookdeepdive.chap11_3

import bookdeepdive.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope

fun main() = runBlocking {
    log.info { "Before" }

    // supervisorScope 은 예외를 무시하기 때문에, 서로 독립적인 작업을 시작하는 함수에서 주로 사용된다.
    supervisorScope {
        launch {
            delay(1000)
            throw Error()
        }

        launch {
            delay(2000)
            log.info { "Done" }
        }
    }

    log.info { "After" }
}