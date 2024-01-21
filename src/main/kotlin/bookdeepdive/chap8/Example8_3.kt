package bookdeepdive.chap8_3

import bookdeepdive.log
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Job
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    val name = CoroutineName("Some name")
    val job = Job()

    launch(name + job) {
        val childName = coroutineContext[CoroutineName]
        log.info { childName == name } // true

        val childJob = coroutineContext.job
        log.info { childJob == job } // false
        log.info { childJob == job.children.first() } // true
    }
}