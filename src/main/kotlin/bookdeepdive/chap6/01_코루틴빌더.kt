package bookdeepdive.chap6.p01

import bookdeepdive.log
import bookdeepdive.printLine
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.newCoroutineContext
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.EmptyCoroutineContext

suspend fun main() {
    launch_예제1()
    printLine("launch 예제1")

    launch_예제2()
    printLine("launch 예제2")

    async_예제1()
    printLine("async 예제1")

    async_예제2()
    printLine("async 예제2")
}

private fun launch_예제1() {
    GlobalScope.launch {
        delay(1000L)
        log.info { "World!" }
    }
    GlobalScope.launch {
        delay(1000L)
        log.info { "World!" }
    }
    GlobalScope.launch {
        delay(1000L)
        log.info { "World!" }
    }
    log.info { "Hello, "}
    Thread.sleep(2000L)
}

private suspend fun launch_예제2() = runBlocking {
    GlobalScope.launch {
        delay(1000L)
        log.info { "World!" }
    }
    GlobalScope.launch {
        delay(1000L)
        log.info { "World!" }
    }
    GlobalScope.launch {
        delay(1000L)
        log.info { "World!" }
    }
    log.info { "Hello," }
    delay(2000L) // 여전히 필요
}

private fun async_예제1() = runBlocking {
    // async 호출되자마자 코루틴을 즉시 시작한다.
    val resultDeferred: Deferred<Int> = GlobalScope.async {
        delay(1000L)
        42
    }

    // 다른 작업을 한다

    // 값이 Deferred 내부에 저장되어 있으면 바로 리턴이고 아니라면 결과가 나올 때까지 블로킹하게 된다.
    val result: Int = resultDeferred.await()
    log.info { "$result" }
}

private fun async_예제2() = runBlocking {
    val res1 = GlobalScope.async {
        delay(1000L)
        "Text 1"
    }
    val res2 = GlobalScope.async {
        delay(3000L)
        "Text 2"
    }
    val res3 = GlobalScope.async {
        delay(2000L)
        "Text 3"
    }
    val resResult1 = res1.await()
    log.info { "$resResult1" }
    val resResult2 = res2.await()
    log.info { "$resResult2" }
    val resResult3 = res3.await()
    log.info { "$resResult3" }
}