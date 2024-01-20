package bookdeepdive.chap6_3_1

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
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
    bookdeepdive.log.info { "$resResult1" }
    val resResult2 = res2.await()
    bookdeepdive.log.info { "$resResult2" }
    val resResult3 = res3.await()
    bookdeepdive.log.info { "$resResult3" }
}