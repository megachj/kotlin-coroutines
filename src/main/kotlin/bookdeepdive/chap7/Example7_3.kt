package bookdeepdive.chap7_3

import bookdeepdive.log
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

suspend fun main(): Unit = withContext(CounterContext("Outer")) {
    printNext()
    launch {
        printNext()
        launch {
            printNext()
        }
        launch(CounterContext("Inner")) {
            printNext()
            printNext()
            launch {
                printNext()
            }
        }
    }
    printNext()
}

private suspend fun printNext() {
    coroutineContext[CounterContext]?.printNext()
}

private class CounterContext(
    private val name: String
): CoroutineContext.Element {
    override val key: CoroutineContext.Key<*> = Key
    private var nextNumber = 0

    fun printNext() {
        log.info { "$name: $nextNumber" }
        nextNumber++
    }

    companion object Key: CoroutineContext.Key<CounterContext>
}

private class MyCustomContext: CoroutineContext.Element {
    override val key: CoroutineContext.Key<*> = Key

    companion object Key :
        CoroutineContext.Key<MyCustomContext>
}
