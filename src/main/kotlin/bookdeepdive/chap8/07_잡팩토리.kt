package bookdeepdive.chap8.p07

import bookdeepdive.log
import bookdeepdive.printLine
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

suspend fun main() = coroutineScope {
    complete_예제()
    printLine("complete 예제 종료")

    completeExceptionally_예제()
    printLine("completeExceptionally 예제 종료")
}

private suspend fun complete_예제() = runBlocking {
    val job = Job()

    launch(job) {
        repeat(5) {num ->
            delay(200)
            log.info { "Loop $num" }
        }
    }

    launch {
        delay(500)
        val isComplete = job.complete()
        log.info { "complete() 후: $job, $isComplete, ${job.children.toList()}" } // 이 시간에는 자식들 잡이 완료가 안되었기 때문에 `Completing` 상태
    }

    job.join() // job 이 완료될 때까지 기다리기(자식들도 모두 완료되어야 해당 job 도 완료가 된다)
    log.info { "join() 후: $job, ${job.children.toList()}" }

    // job 이 이미 Completed 상태이기 때문에 자식 잡이 생성되지 않는다.
    // 좀 더 자세히 코루틴 내부 코드에서는, 생성되자마자 부모 잡이 종료되었기 때문에 Cancelling 상태를 거쳐 Cancelled 가 되어서 부모-자식 관계가 없어진다.
    launch(job) {
        log.info { "Will not be printed" }
        delay(10_000)
    }
    log.info { "completed 상태일 땐 자식 잡이 생성되지 않는다: ${job.children.toList()}" }
}

private suspend fun completeExceptionally_예제() = runBlocking {
    val job = Job()

    launch(job) {
        repeat(5) {num ->
            delay(200)
            log.info { "Loop $num" }
        }
    }

    launch {
        delay(500)
        val isComplete = job.completeExceptionally(Error("Some error")) // 예외를 발생시킨다. 자식들 잡에도 예외를 전파하게 된다.
        log.info { "completeExceptionally() 후: $job, $isComplete, ${job.children.toList()}" } // 이 시간에는 잡이 예외로 취소되는 중이기 때문에 `Cancelling` 상태
    }

    job.join()
    log.info { "join() 후: $job, ${job.children.toList()}" }

    launch(job) {
        log.info { "Will not be printed" }
        delay(10_000)
    }
    log.info { "cancelled 상태일 땐 자식 잡이 생성되지 않는다: ${job.children.toList()}" }
}