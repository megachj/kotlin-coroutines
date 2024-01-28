package bookdeepdive.chap12_2_1

import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.coroutineScope
import java.util.concurrent.Executors

suspend fun main() = coroutineScope {
    // limitedParallelism 은 kotlinx-coroutines 버전 1.6 에서 나왔다.
    // 그래서 이전 버전에서는 Executors 클래스를 이용해 독립적인 스레드 풀을 가진 디스패처를 만들었다.

    // ExecutorService.asCoroutineDispatcher() 로 만들어진 디스패처의 가장 큰 문제점은 'close' 함수로 닫혀야 한다는 것이다.
    val numberOfThread = 20
    val dispatcher = Executors.newFixedThreadPool(numberOfThread)
        .asCoroutineDispatcher()

    // 디스패처가 다 사용되고 앞으로 사용될 일이 없을때 close() 를 해준다??
    // 스프링 애플리케이션은 보통 빈으로 등록하고 계속 쓰니 굳이 문제점 같지는 않음.
    dispatcher.close()
}