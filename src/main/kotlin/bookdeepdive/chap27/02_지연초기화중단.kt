package bookdeepdive.chap27.p02

import bookdeepdive.chap27.etc1.suspendLazy
import bookdeepdive.log
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

private suspend fun makeConnection(): String {
    log.info { "Creating connection" }
    delay(1000)
    return "Connection"
}

val getConnection = suspendLazy { makeConnection() }
suspend fun main() {
    val connection1 = getConnection()
    val connection2 = getConnection()
    val connection3 = getConnection()

    log.info { "$connection1" }
    log.info { "$connection2" }
    log.info { "$connection3" }
}


// --------------------
// inline 함수안의 람다식이 suspend 제어자가 없더라도 람다를 넘기는 곳이 중단가능하다면 중단 함수를 호출할 수 있다.
// inline 함수는 컴파일되면 함수바디가 호출된 곳에 들어가기 때문에
private suspend fun getUserNames(
    userIds: List<Int>
): List<String> = coroutineScope {
    userIds
        .map { async { getUserName(it) } }
        // .map1 { it.await() }
        .map { it.await() }
}

private suspend fun getUserName(userId: Int): String = coroutineScope {
    delay(1000)
    val userName = "Person" + userId
    log.info { "$userName" }

    userName
}

private fun <T, R> Iterable<T>.map1(transform: (T) -> R): List<R> {
    return map(transform)
}