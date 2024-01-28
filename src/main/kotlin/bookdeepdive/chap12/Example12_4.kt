package bookdeepdive.chap12_4

import bookdeepdive.log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExecutorCoroutineDispatcher
import kotlinx.coroutines.Runnable
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.util.concurrent.Executor
import kotlin.coroutines.CoroutineContext
import kotlin.system.measureTimeMillis

// 가상 스레드 사용을 위해서 jdk19 가 필요하고, VM options 에 '--enable-preview' 옵션이 필요함.
suspend fun main() = coroutineScope {
    // Loom 디스패처(가상 스레드)
    measureTimeMillis {
        coroutineScope {
            repeat(20_000) {
                launch(Dispatchers.Loom) {
                    Thread.sleep(1000)
                }
            }
        }
    }.let { log.info { "Loom 디스패처: 20_000개 1초 스레드 블로킹 작업 완료 시간: $it(ms)" } }

    // IO 디스패처
    val dispatcher = Dispatchers.IO.limitedParallelism(20_000)
    measureTimeMillis {
        coroutineScope {
            repeat(20_000) {
                launch(dispatcher) {
                    Thread.sleep(1000)
                }
            }
        }
    }.let { log.info { "IO 디스패처: 20_000개 1초 스레드 블로킹 작업 완료 시간: $it(ms)" } }
}

// Dispatchers 객체의 확장 프로퍼티 정의
private val Dispatchers.Loom: CoroutineDispatcher
    get() = LoomDispatcher

// Loom 디스패처
private object LoomDispatcher : ExecutorCoroutineDispatcher() {

    override val executor: Executor = Executor { command ->
        Thread.startVirtualThread(command)
    }

    override fun dispatch(context: CoroutineContext, block: Runnable) {
        executor.execute(block)
    }

    override fun close() {
        error("Cannot be invoked on Dispatchers.LOOM")
    }
}