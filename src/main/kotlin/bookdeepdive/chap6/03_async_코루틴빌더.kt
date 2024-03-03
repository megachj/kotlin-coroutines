package bookdeepdive.chap6.p03

import bookdeepdive.log
import bookdeepdive.printLine
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() {
    example1()
    printLine("예제 1")

    example2()
    printLine("예제 2")
}

private fun example1() = runBlocking {
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

private fun example2() = runBlocking {
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