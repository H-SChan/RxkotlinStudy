package part1

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.kotlin.toObservable

// observable 사용
fun main(args: Array<String>) {
    var list: List<Any> = listOf("One", 2, "Three", 4.0, "Five", 6.0f)
    var observable: Observable<Any> = list.toObservable()

    observable.subscribeBy( // 람다로 된 구독자로 이름 있는 인자를 사용
        onNext = { println(it) },
        onError = { it.printStackTrace() },
        onComplete = { println("Done!") }
    )
}

/* // iterator 사용
fun main(args: Array<String>) {
    var list: List<Any> = listOf("One", 2, "Three", 4.0, "Five", 6.0f)
    var iterator = list.iterator()

    while (iterator.hasNext()) {
        println(iterator.next())
    }
}*/
