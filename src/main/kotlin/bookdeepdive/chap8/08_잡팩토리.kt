package bookdeepdive.chap8.p08

import bookdeepdive.log
import bookdeepdive.printLine
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

suspend fun main() = coroutineScope {
    ex1()
    printLine("예제1 종료")

    ex2()
    printLine("예제2 종료")
}

private suspend fun ex1() = runBlocking {
    val job = Job()
    launch(job) {
        delay(1000)
        log.info { "Text 1" }
    }
    launch(job) {
        delay(2000)
        log.info { "Text 2" }
    }
    job.complete()
    job.join()
}

private suspend fun ex2() = runBlocking {
    val parentJob = Job()
    val job = Job(parentJob)
    launch(job) {
        delay(1000)
        log.info { "Text 1" }
    }
    launch(job) {
        delay(2000)
        log.info { "Text 2" }
    }
    delay(1100)
    parentJob.cancel()
    job.children.forEach { it.join() }
}