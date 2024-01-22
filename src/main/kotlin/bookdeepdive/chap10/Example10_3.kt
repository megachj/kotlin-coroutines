package bookdeepdive.chap10_3

import bookdeepdive.log
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.supervisorScope

private class MyException : Throwable()

suspend fun main() = supervisorScope {
    val str1 = async<String> {
        delay(1000)
        throw MyException()
    }
    val str2 = async {
        delay(2000)
        "Text2"
    }

    try {
        val str1Value = str1.await()
        log.info { str1Value }
    } catch (e: MyException) {
        log.error { e }
    }
    val str2Value = str2.await()
    log.info { str2Value }
}