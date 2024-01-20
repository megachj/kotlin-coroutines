package bookdeepdive.chap4

import kotlinx.coroutines.delay
import mu.KotlinLogging

private val log = KotlinLogging.logger {}
suspend fun main() {
    myFunction()
}

//fun main() = runBlocking {
//    myFunction()
//}

/**
 * 상태(지역 변수나 파라미터 등)를 가진 함수
 * -> 상태가 컨티뉴에이션에 저장된다.
 * -> 지역 변수나 파라미터 같이 함수 내에서 사용되던 값들은 중단되기 직전에 저장되고, 이후 함수가 재개될 때 복구된다.
 */
private suspend fun myFunction() {
    log.info { "Before" }
    var counter = 0
    delay(1000)

    counter++
    log.info { "Counter: $counter" }
    log.info { "After" }
}
