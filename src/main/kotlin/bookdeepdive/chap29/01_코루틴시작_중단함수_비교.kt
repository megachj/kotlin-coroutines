package bookdeepdive.chap29.p01

import bookdeepdive.log
import bookdeepdive.printLine
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope

val notificationScope = CoroutineScope(SupervisorJob())
val sender = NotificationSender(notificationScope)

fun main() {
    코루틴_시작_sender_예제()
    printLine()

    중단함수_sender_예제()
    printLine()
}

private fun 코루틴_시작_sender_예제() {
    runBlocking {
        sender.sendNotifications1(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
        log.info { "보내기 끝" }
        delay(1100)
    }
}

private fun 중단함수_sender_예제() {
    runBlocking {
        sender.sendNotifications2(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
        log.info { "보내기 끝" }
    }
}

class NotificationSender(
    private val notificationScope: CoroutineScope
) {
    fun sendNotifications1(userIds: List<Int>) {
        for (userId in userIds) {
            notificationScope.launch {
                delay(100)
                log.info { "[코루틴 시작하는 sender] 유저($userId) 에게 메시지를 보냄." }
            }
        }
    }

    suspend fun sendNotifications2(userIds: List<Int>) = supervisorScope {
        for (userId in userIds) {
            launch {
                delay(100)
                log.info { "[중단 함수 sender] 유저($userId) 에게 메시지를 보냄." }
            }
        }
    }
}