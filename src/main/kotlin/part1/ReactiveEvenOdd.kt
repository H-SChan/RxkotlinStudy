package part1

import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.Subject

fun main(args: Array<String>) {
    var subject: Subject<Int> = PublishSubject.create()
//    subject.map { isEven(it) }.subscribe { println("The number is ${(if (it) "Even" else "Odd")}") }
    subject.map { isEven(it) }.subscribe { println("The number is ${if (it) "Even" else "Odd"}") }

    subject.onNext(4)
    subject.onNext(9)
}

fun isEven(n: Int): Boolean = ((n % 2) == 0)