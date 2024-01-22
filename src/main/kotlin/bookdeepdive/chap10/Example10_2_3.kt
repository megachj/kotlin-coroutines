package bookdeepdive.chap10_2_3

import bookdeepdive.log
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    // 0: supervisorJob
    // 1: launch, launch
    val job = SupervisorJob()
    launch(job) {
        delay(1000)
        throw Error("Some error")
    }

    launch(job) {
        delay(2000)
        log.info { "Will be printed" }
    }
    job.join()
}