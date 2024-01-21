package bookdeepdive.chap8_4

import bookdeepdive.log
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

suspend fun main(): Unit = coroutineScope {
    example1()
    println()

    example2()
    println()
}

private suspend fun example1() = runBlocking {
    val job1 = launch {
        delay(1000)
        log.info { "Test1" }
    }
    val job2 = launch {
        delay(2000)
        log.info { "Test2" }
    }

    job1.join()
    job2.join()
    log.info { "All tests are done" }
}

private suspend fun example2() = runBlocking {
    launch {
        delay(1000)
        log.info { "Test1" }
    }
    launch {
        delay(2000)
        log.info { "Test2" }
    }

    val children = coroutineContext.job?.children

    val childrenNum = children?.count()
    log.info { "자식 수: $childrenNum" }

    children?.forEach { it.join() }
    log.info { "All tests are done" }
}