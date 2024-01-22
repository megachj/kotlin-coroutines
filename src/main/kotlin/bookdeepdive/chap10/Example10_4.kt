package bookdeepdive.chap10_4

import bookdeepdive.log
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object MyNonPropagatingException: CancellationException()

suspend fun main(): Unit = coroutineScope {
    // CancellationException 의 서브타입은 예외가 부모로 전파되지 않는다.
    // 자기자신과 자식들에게만 취소가 나가게 된다.

    // 0: 부모
    // 1: 1-0 launch, 1-1 launch
    // 2: 2-0 launch
    launch {// 1-0
        launch {// 2-0
            delay(2000)
            log.info { "Will not be printed" }
        }
        throw MyNonPropagatingException
    }
    launch {// 1-1
        delay(2000)
        log.info { "Will be printed" }
    }
}