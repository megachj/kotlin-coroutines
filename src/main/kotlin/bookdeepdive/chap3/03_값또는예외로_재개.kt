package bookdeepdive.chap3.p03

import bookdeepdive.log
import bookdeepdive.printLine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

suspend fun main() {
    값으로_재개_예제()
    printLine()

    예외로_재개_예제()
    printLine()
}

private suspend fun 값으로_재개_예제() {
    log.info { "Before" }
    // 재개된 지점(중단됐었던)에 곳에 리턴된다.
    val str: String = suspendCoroutine<String> { cont ->
        cont.resume("Some text")
    }

    log.info { "$str" }
    log.info { "After" }
}

private suspend fun 예외로_재개_예제() {
    log.info { "Before" }
    try {
        // 재개된 지점(중단됐었던)에 예외가 던져진다.
        suspendCoroutine<Unit> { cont ->
            cont.resumeWithException(RuntimeException("런타임 예외 발생!"))
        }
    } catch (e: Exception) {
        log.warn { "코루틴에서 예외 발생: ${e.message}" }
    }
    log.info { "After" }
}