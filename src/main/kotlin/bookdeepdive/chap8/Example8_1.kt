package bookdeepdive.chap8

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mu.KotlinLogging

private val log = KotlinLogging.logger {  }
suspend fun main() = coroutineScope {
    val job = Job()
    log.info { "$job" }
    job.complete()
    log.info { "$job" }

    val activeJob =launch {
        delay(1000)
    }
    log.info { "$activeJob" }

    activeJob.join()
    log.info { "$activeJob" }

    val lazyJob = launch(start = CoroutineStart.LAZY) {
        delay(1000)
    }
    log.info { "$lazyJob" }

    lazyJob.start()
    log.info { "$lazyJob" }

    lazyJob.join()
    log.info { "$lazyJob" }
}