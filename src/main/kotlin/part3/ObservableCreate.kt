package part3

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

fun main() {
    val observer: Observer<String> = object : Observer<String> {
        override fun onComplete() {
            println("All Completed")
        }

        override fun onSubscribe(d: Disposable) {
            println("New Subscription")
        }

        override fun onNext(t: String) {
            println("Next $t")
        }

        override fun onError(e: Throwable) {
            println("Error Occurred ${e.message}")
        }
    }

    val observable: Observable<String> = Observable.create {
        it.onNext("Emit 1")
        it.onNext("Emit 2")
        it.onNext("Emit 3")
        it.onNext("Emit 4")
        it.onComplete()
    }

    observable.subscribe(observer)

    val observable2: Observable<String> = Observable.create {
        it.onNext("Emit 1")
        it.onNext("Emit 2")
        it.onNext("Emit 3")
        it.onNext("Emit 4")
        it.onError(Exception("My Custom Exception"))
    }

    observable2.subscribe(observer)
}