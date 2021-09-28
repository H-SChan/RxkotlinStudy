package part3

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import kotlin.system.measureTimeMillis

fun main() {
    val observable: Observable<Int> = Observable.range(1, 5)

    observable.subscribe({
        // onNext 매서드
        println("Next $it")
    }, {
        // onError 매서드
        println("Error ${it.message}")
    }, {
        // onComplete 매서드
        println("Done")
    })

    val observer: Observer<Int> = object : Observer<Int> {
        override fun onSubscribe(d: Disposable) {
            println("New Subscription")
        }

        override fun onNext(t: Int) {
            println("Next $t")
        }

        override fun onError(e: Throwable) {
            println("error Occurred ${e.message}")
        }

        override fun onComplete() {
            println("All Completed")
        }

    }

    observable.subscribe(observer)
}