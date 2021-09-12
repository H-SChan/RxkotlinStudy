package part3

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.toObservable

fun main() {
    val observer: Observer<String> = object : Observer<String> {
        override fun onSubscribe(d: Disposable) {
            println("New Subscription")
        }

        override fun onNext(t: String) {
            println("Next $t")
        }

        override fun onError(e: Throwable) {
            println("Error Occurred ${e.message}")
        }

        override fun onComplete() {
            println("All Completed")
        }

    }

    val list: List<String> = listOf("String 1", "String 2", "String 3", "String 4")

    val observable: Observable<String> = list.toObservable()

    observable.subscribe(observer)
}