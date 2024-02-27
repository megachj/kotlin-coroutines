package bookdeepdive.chap27.p01

import bookdeepdive.chap27.etc1.mapAsync
import bookdeepdive.log
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

suspend fun main() = coroutineScope {
    listOf(1, 2)
        .mapAsync(1) { userId -> getUserName(userId) }

    Unit
}

private suspend fun getUserName(userId: Int): String = coroutineScope {
    delay(1000)
    val userName = "Person" + userId
    log.info { "$userName" }

    userName
}
