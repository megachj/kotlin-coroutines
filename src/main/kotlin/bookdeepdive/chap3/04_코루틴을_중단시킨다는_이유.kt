package bookdeepdive.chap3.p04

import bookdeepdive.log
import bookdeepdive.printLine
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

suspend fun main() {
//    재개가_안되는_잘못된_예제()
//    printLine()

    재개가_되도록_수정한_예제()
    printLine()
}

// 이렇게 컨티뉴에이션을 저장해서 구현하는 방법은 예시를 보여주기 위함이지, 실제로는 이렇게 하면 안된다. (메모리 누수 발생 가능성 등의 이유)
var continuation: Continuation<Unit>? = null
private suspend fun suspendAndSetContinuation() {
    suspendCoroutine<Unit> { cont ->
        continuation = cont
    }
}

private suspend fun 재개가_안되는_잘못된_예제() {
    log.info { "Before" }

    suspendAndSetContinuation() // 코루틴이 중단된 곳이 여기이다.
    continuation?.resume(Unit) // 이쪽 코드는 코루틴이 재개되어야 올 수 있기 때문에, 해당 예제는 재개가 안되어서 끝나지 않음.

    log.info { "After" }
}

private suspend fun 재개가_되도록_수정한_예제() = coroutineScope {
    log.info { "Before" }

    launch {
        delay(1000)
        continuation?.resume(Unit) // 새로운 코루틴이 아래의 코루틴을 재개해준다.
    }

    suspendAndSetContinuation() // 코루틴이 중단된다.
    log.info { "After" }
}