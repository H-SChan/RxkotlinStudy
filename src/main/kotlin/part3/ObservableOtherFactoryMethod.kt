package part3

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

fun main() {
    val observer: Observer<Any> = object : Observer<Any> {
        override fun onSubscribe(d: Disposable) {
            println("New Subscription")
        }

        override fun onNext(t: Any) {
            println("Next $t")
        }

        override fun onError(e: Throwable) {
            println("Error Occurred ${e.message}")
        }

        override fun onComplete() {
            println("All Completed")
        }

    }

    Observable.range(1, 10).subscribe(observer)
    Observable.empty<String>().subscribe(observer)

    /**
     * interval 구독자 생성 ->
     * (3초 경과) print 0, delay(3000) 지나감 ->
     * (6초 경과) print 1, delay(6000) 지나감 ->
     * (9초 경과) print 2, delay(9000) 끝, timer 구독자 생성 ->
     * (12초 경과) print 3, delay(3000) 지나감 ->
     * (13초 경과) timer.print 0 후 완수, delay(4000)지나감 ->
     * (13.5초 경과) delay 끝, runBlocking 블록 종료
     */
    runBlocking {
        // interval 은 지정된 간격만큼의 숫자를 0부터 순차적으로 내보낸다. 이는 구독을 취소하거나 프로그램이 종료될 때까지 이어짐
        Observable.interval(3, TimeUnit.SECONDS).subscribe(observer)
        delay(9000)
        // timer 은 지정된 시간이 경과한 후에 한 번만 실행된다
        Observable.timer(4, TimeUnit.SECONDS).subscribe(observer)
        delay(4500)
    }
}