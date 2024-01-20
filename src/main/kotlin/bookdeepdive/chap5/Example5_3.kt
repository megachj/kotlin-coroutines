package bookdeepdive.chap5

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import mu.KotlinLogging

private val log = KotlinLogging.logger {  }
fun main() = runBlocking {
    // async 호출되자마자 코루틴을 즉시 시작한다.
    val resultDeferred: Deferred<Int> = GlobalScope.async {
        delay(1000L)
        42
    }

    // 다른 작업을 한다

    // 값이 Deferred 내부에 저장되어 있으면 바로 리턴이고 아니라면 결과가 나올 때까지 블로킹하게 된다.
    val result: Int = resultDeferred.await()
    log.info { "$result" }
}