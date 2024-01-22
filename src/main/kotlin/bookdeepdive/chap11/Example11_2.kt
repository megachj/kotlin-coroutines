package bookdeepdive.chap11_2

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

private fun CoroutineScope.log(text: String) {
    val name = this.coroutineContext[CoroutineName]?.name
    bookdeepdive.log.info { "[$name] $text" }
}

// 0: main(Parent)
// 1: withContext(Child1), withContext(Child2)
fun main() = runBlocking(CoroutineName("Parent")) {
    log("Before")

    // withContext(context) 동작 방식은 async(context) { ... }.await() 과 비슷하다.
    withContext(CoroutineName("Child 1")) {
        delay(1000)
        log("Hello 1")
    }

    withContext(CoroutineName("Child 2")) {
        delay(1000)
        log("Hello 2")
    }

    log("After")
}