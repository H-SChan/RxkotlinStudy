package part3

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

fun main() {
    runBlocking {
        val observable: Observable<Long> = Observable.interval(100, TimeUnit.MILLISECONDS)
        val observer: Observer<Long> = object: Observer<Long> {
            lateinit var disposable: Disposable

            override fun onSubscribe(d: Disposable) {
                disposable = d
            }

            override fun onNext(t: Long) {
                println("Receive $t")
                if (t >= 10 && !disposable.isDisposed) { // 값이 10보다 크거나 같고, 배출이 이미 중단되거나 해지되지 않으면
                    disposable.dispose() // 해지
                    println("Disposed")
                }
            }

            override fun onError(e: Throwable) {
                println("Error ${e.message}")
            }

            override fun onComplete() {
                println("Complete")
            }

        }

        observable.subscribe(observer)
        delay(1500) // 0.5초를 더 기다려도 구독이 해지돼 더이상 출력되지 않음
    }
}