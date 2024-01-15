package bookdeepdive.chap4

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import mu.KotlinLogging

private val log = KotlinLogging.logger {}

fun main() = runBlocking {
    printUser("chimchak")
}

private suspend fun printUser(token: String) {
    log.info { "Before" }
    val userId = getUserId(token)
    log.info("Got userId: $userId")

    val userName = getUserName(userId, token)
    log.info { "${User(userId, userName)}" }
    log.info { "After" }
}

private suspend fun getUserId(token: String): Long {
    log.info { "userId 조회 요청. token: $token" }
    delay(1000)

    val userId = 1L
    log.info { "userId 조회 응답. userId: $userId" }

    return userId
}

private suspend fun getUserName(userId: Long, token: String): String {
    log.info { "userName 조회 요청. userId: $userId, token: $token" }
    delay(1000)

    val userName = "침착맨"
    log.info { "userName 조회 응답. userName: $userName" }

    return userName
}

private data class User(
    val userId: Long,
    val userName: String
)
