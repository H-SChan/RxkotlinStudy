package part2

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis
import kotlinx.coroutines.async

// 함수를 일시 중지로 표시하는 suspend 키워드 사용. 함수를 실행하는 동안 프로그램은 결과를 기다야함 -> 메인스레드 일시 중단 금지
suspend fun longRunningTsk(): Long {
    val time = measureTimeMillis { // 전달된 블록을 실행하고 실행 시간을 측정한 후 반환
        println("Please wait")
        delay(2000)
        println("Delay Over")
    }
    return time
}

/*fun main(args: Array<String>) {
    // runBlocking 블록은 longRunningTsk() 함수가 완료될 때까지 프로그램을 대기 상태로 만듦
    runBlocking {
        val exeTime = longRunningTsk()
        println("Execution Time is $exeTime")
    }
}*/

fun main(args: Array<String>) = runBlocking {
    // async 코드 블록은 전달된 코루틴 컨텍스트에서 비동기적으로 블록 내부의 코드를 실행한다
    val time = async { longRunningTsk() }
    println("Print after async")
//    runBlocking { println("printing time ${time.await()}") }
    println("printing time ${time.await()}")
}
