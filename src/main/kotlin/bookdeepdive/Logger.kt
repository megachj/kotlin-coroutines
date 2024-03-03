package bookdeepdive

import mu.KotlinLogging

val log = KotlinLogging.logger {  }

fun printLine() {
    println("==============================================\n")
}

fun printLine(endTitle: String) {
    println("End: $endTitle ==============================================\n")
}