package bookdeepdive.chap11_3_1

import bookdeepdive.log
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main() = runBlocking {
    log.info { "Before" }

    // main?
    // SupervisorJob()
    // withContext
    // launch, launch
    withContext(SupervisorJob()) {
        launch {
            delay(1000)
            throw Error()
        }
        launch {
            delay(2000)
            log.info { "Done" }
        }
    }

    log.info { "After" }
}

