package bookdeepdive.chap8.p04

import bookdeepdive.log
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    val job: Job = launch {
        delay(1000)
    }

    val parentJob: Job = coroutineContext.job
    log.info { job == parentJob } // false

    val parentChildren: Sequence<Job> = parentJob.children
    log.info { parentChildren.first() == job } // true
}