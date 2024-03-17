package bookdeepdive.chap8.p01

import bookdeepdive.log
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mu.KotlinLogging

suspend fun main() = coroutineScope {
    // 잡 팩토리로 생성된 잡은 메서드로 완료시킬 때까지 Active 상태
    val job = Job()
    log.info { "[1.잡 팩토리 잡 예제] 잡 팩토리로 잡 생성: $job" }

    // 메서드로 잡을 완료상태로 변경시킨다.
    job.complete()
    log.info { "[1.잡 팩토리 잡 예제] job.complete() 호출 후: $job" }

    // 코루틴 빌더로 생성된 잡은 잡이 완료될 때까지 Active 상태
    val activeJob =launch {
        delay(1000)
    }
    log.info { "[2. 코루틴 빌더 잡 예제] 코루틴 빌더로 생성된 잡 생성: $activeJob" }

    // 잡이 완료될 때까지 기다린다.
    activeJob.join()
    log.info { "[2. 코루틴 빌더 잡 예제] job.join() 으로 완료될 때까지 기다린 후: $activeJob" }

    // 코루틴 빌더로 생성할때 LAZY 로 실행하면 start 전까지 실행하지 않아서 New 상태
    val lazyJob = launch(start = CoroutineStart.LAZY) {
        delay(1000)
    }
    log.info { "[3. 코루틴 빌더 LAZY 잡 예제] 코루틴 빌더로 생성된 LAZY 잡 생성: $lazyJob" }

    // Active 상태가 되려면 시작하는 함수를 호출해야 한다.
    lazyJob.start()
    log.info { "[3. 코루틴 빌더 LAZY 잡 예제] job.start() 를 호출한 후: $lazyJob" }

    lazyJob.join()
    log.info { "[3. 코루틴 빌더 LAZY 잡 예제] job.join() 으로 완료될 때까지 기다린 후: $lazyJob" }
}