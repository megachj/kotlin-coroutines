package bookdeepdive.chap7.p01

import bookdeepdive.log
import bookdeepdive.printLine
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

fun main() {
    `CoroutineContext에서 원소 찾기`()
    printLine("원소 찾기 예제")

    `컨텍스트 더하기`()
    printLine("더하기 예제")

    `컨텍스트 더하기2`()
    printLine("더하기 예제2")

    `비어 있는 컨텍스트`()
    printLine("비어 있는 컨텍스트 예제")

    `원소 제거`()
    printLine("원소 제거 예제")

    `컨텍스트 폴딩`()
    printLine("컨텍스트 폴딩 예제")
}

private fun `CoroutineContext에서 원소 찾기`() {
    val ctx: CoroutineContext = CoroutineName("A name")

    // ctx.get(CoroutineName) 도 된다. CoroutineName 대신 CoroutineName.Key 도 가능.
    val coroutineName: CoroutineName? = ctx[CoroutineName]
    log.info { "${coroutineName?.name}" }
    val job: Job? = ctx[Job]
    log.info { "$job" }
}

private fun `컨텍스트 더하기`() {
    val ctx1: CoroutineContext = CoroutineName("Name1")
    log.info { "${ctx1[CoroutineName]?.name}" }
    log.info { "${ctx1[Job]?.isActive}" }

    val ctx2: CoroutineContext = Job()
    log.info { "${ctx2[CoroutineName]?.name}" }
    log.info { "${ctx2[Job]?.isActive}" }

    val ctx3 = ctx1 + ctx2
    log.info { "${ctx3[CoroutineName]?.name}" }
    log.info { "${ctx3[Job]?.isActive}" }
}

private fun `컨텍스트 더하기2`() {
    val ctx1: CoroutineContext = CoroutineName("Name1")
    log.info { "${ctx1[CoroutineName]?.name}" }

    val ctx2: CoroutineContext = CoroutineName("Name2")
    log.info { "${ctx2[CoroutineName]?.name}" }

    val ctx3 = ctx1 + ctx2
    log.info { "${ctx3[CoroutineName]?.name}" }
}

private fun `비어 있는 컨텍스트`() {
    val empty: CoroutineContext = EmptyCoroutineContext
    log.info { "${empty[CoroutineName]}" }
    log.info { "${empty[Job]}" }

    val ctxName = empty + CoroutineName("Name1") + empty
    log.info { "${ctxName[CoroutineName]}" }
}

private fun `원소 제거`() {
    val ctx = CoroutineName("Name1") + Job()
    log.info { "${ctx[CoroutineName]?.name}" }
    log.info { "${ctx[Job]?.isActive}" }

    val ctx2 = ctx.minusKey(CoroutineName)
    log.info { "${ctx2[CoroutineName]?.name}" }
    log.info { "${ctx2[Job]?.isActive}" }

    val ctx3 = (ctx + CoroutineName("Name2")).minusKey(CoroutineName)
    log.info { "${ctx3[CoroutineName]?.name}" }
    log.info { "${ctx3[Job]?.isActive}" }
}

private fun `컨텍스트 폴딩`() {
    val ctx = CoroutineName("Name1") + Job()

    ctx.fold("") { acc, element -> "$acc$element" }
        .also {
            log.info { it }
        }

    val empty = emptyList<CoroutineContext>()
    ctx.fold(empty) { acc, element -> acc + element }
        .joinToString()
        .also {
            log.info { it }
        }
}