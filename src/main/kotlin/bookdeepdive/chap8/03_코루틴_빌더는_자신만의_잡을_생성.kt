package bookdeepdive.chap8.p03

import bookdeepdive.log
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Job
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    val parentName = CoroutineName("부모")
    val parentJob = Job()

    launch(parentName + parentJob) {
        val childName = coroutineContext[CoroutineName]
        log.info { "코루틴 빌더로 만들어진 코루틴은 부모 컨텍스트를 물려받아서 만들어진다. ${childName == parentName}" } // true

        val childJob = coroutineContext.job
        log.info { "코루틴 빌더의 잡은 부모 잡을 물려받지 않고 자신만의 잡이 만들어진다. ${childJob == parentJob}" } // false
        log.info { "코루틴 빌더의 잡은 하지만 부모-자식 관계를 맺게되어서 부모 잡에서 자식 잡을 접근할 수 있고, 자식도 부모 잡을 접근할 수 있다. ${childJob == parentJob.children.first()}, ${childJob.parent == parentJob}" } // true, true
    }
}