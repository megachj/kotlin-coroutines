package bookdeepdive.chap8.p02

import bookdeepdive.log
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    val job: Job = launch {
        delay(1000)
        log.info { "Test" }
    }

    val deferred: Deferred<String> = async {
        delay(1000)
        "Test"
    }
    val  job2: Job = deferred

    log.info { coroutineContext.job.isActive }
}