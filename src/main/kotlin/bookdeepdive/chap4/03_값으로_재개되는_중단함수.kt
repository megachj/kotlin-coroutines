package bookdeepdive.chap4.p03

import bookdeepdive.log
import kotlinx.coroutines.delay

suspend fun main() {
    printUser("chimchak")
}

/**
 * 값을 받아 재개되는 함수
 * -> 함수가 값으로 재개되었다면 결과는 Result.Success(value) 가 된다. 결과를 리턴하면서 재개되었을 때를 말한다.
 * -> 함수가 예외로 재개되었다면 결과는 Result.Failure(exception) 이 된다. 이때는 예외를 던진다.
 */
private suspend fun printUser(token: String) {
    log.info { "Before" }
    val userId = getUserId(token)
    log.info { "Got userId: $userId" }

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
