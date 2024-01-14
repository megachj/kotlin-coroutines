package sunset.example

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    myFunction()
}

suspend fun myFunction() {
    println("Before")
    delay(1000)
    println("After")
}
