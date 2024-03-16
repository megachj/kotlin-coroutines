package bookdeepdive.chap7.p031

import bookdeepdive.log
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext

private suspend fun printName() {
    // 중단함수는 continuation 객체가 넘어오고, continuation 객체는 CoroutineContext 를 가지고 있다.
    // 따라서 중단함수 내에서 coroutineContext 에 접근할 수 있다.
    val coroutineName = coroutineContext[CoroutineName]?.name
    log.info { "$coroutineName" }
}

suspend fun main() = withContext(CoroutineName("Outer")) {
    printName()
    launch(CoroutineName("Inner")) {
        printName()
    }
    delay(10)
    printName()
}