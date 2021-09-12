package part3

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import java.util.concurrent.Callable
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit

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

    val list = listOf("String 1", "String 2", "String 3", "String 4")
    val observableFromIterable: Observable<String> = Observable.fromIterable(list)
    observableFromIterable.subscribe(observer)

    val callable = Callable { "From Callable" }
    val observableFromCallable: Observable<String> = Observable.fromCallable(callable)
    observableFromCallable.subscribe(observer)

    val future: Future<String> = object : Future<String> {
        override fun cancel(mayInterruptIfRunning: Boolean): Boolean = false

        override fun isCancelled(): Boolean = false

        override fun isDone(): Boolean = true

        override fun get(): String = "Hello From Future"

        override fun get(timeout: Long, unit: TimeUnit): String = "Hello From Future"
    }
    val observableFromFuture: Observable<String> = Observable.fromFuture(future)
    observableFromFuture.subscribe(observer)
}