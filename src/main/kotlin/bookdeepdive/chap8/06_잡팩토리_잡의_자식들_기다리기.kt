package bookdeepdive.chap8.p06

import bookdeepdive.log
import bookdeepdive.printLine
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

suspend fun main(): Unit = coroutineScope {
    example2()
    printLine("잡 팩토리로 생성한 잡의 자식들 기다리기 예제")
}

private suspend fun example1() = runBlocking {
    val job = Job()
    launch(job) {
        delay(1000)
        log.info { "Text 1" }
    }
    launch(job) {
        delay(2000)
        log.info { "Text 2" }
    }
    job.join() // 여기서 영원히 대기함.
    log.info { "Will not be printed" }
}

private suspend fun example2() = runBlocking {
    val job = Job()
    launch(job) {
        delay(1000)
        log.info { "Text 1" }
    }
    launch(job) {
        delay(2000)
        log.info { "Text 2" }
    }

    val beforeChildren = job.children.toList()
    log.info { "(잡 팩토리로 생성한 부모 잡) 자식들 잡 끝나기 전: $job, $beforeChildren" }

    job.children.forEach { it.join() }

    val afterChildren = job.children.toList()
    log.info { "(잡 팩토리로 생성한 부모 잡) 자식들 잡 끝난 후: $job, $beforeChildren, $afterChildren" }
}
