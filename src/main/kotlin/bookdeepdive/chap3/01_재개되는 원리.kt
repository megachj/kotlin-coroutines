package bookdeepdive.chap3.p01

import bookdeepdive.log
import bookdeepdive.printLine
import kotlin.concurrent.thread
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

suspend fun main() {
//    중단점이없는_예제()
//    printLine()

//    중단지점이_존재하는_예제()
//    printLine()

//    중단지점이_존재하는_예제2()
//    printLine()

    중단지점이_존재하고_재개하는_예제()
    printLine()
}

private suspend fun 중단점이없는_예제() {
    log.info { "Before" }

    log.info { "After" }
}

private suspend fun 중단지점이_존재하는_예제() {
    log.info { "Before" }
    suspendCoroutine<Unit> {
        // 코루틴을 중단하지만, 재개하는 코드가 없다.
    }
    // 재개가 되지 않아서 After 가 실행이 되지 않음.
    log.info { "After" }
}

private suspend fun 중단지점이_존재하는_예제2() {
    log.info { "Before" }
    suspendCoroutine<Unit> { continuation ->
        // 코루틴을 중단하지만, 재개하는 코드가 없다.
        log.info { "Before too" }
    }
    // 재개가 되지 않아서 After 가 실행이 되지 않음.
    log.info { "After" }
}

private suspend fun 중단지점이_존재하고_재개하는_예제() {
    log.info { "Before" }
    suspendCoroutine<Unit> { continuation ->
        // 코루틴을 중단하고 바로 재개한다.
        continuation.resume(Unit)
    }
    // 재개가 되어서 After 가 실행된다.
    log.info { "After" }
}
