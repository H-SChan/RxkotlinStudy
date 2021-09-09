package part2

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.kotlin.subscribeBy

fun main(args: Array<String>) {
    val maybeValue: Maybe<Int> = Maybe.just(14)
    maybeValue.subscribeBy(
        onComplete = { println("Completed Empty") },
        onError = { println("Error $it") },
        onSuccess = { println("Completed with value $it") }
    )
    val maybeEmpty: Maybe<Int> = Maybe.empty()
    maybeEmpty.subscribeBy(
        onSuccess = { println("Completed with value $it") },
        onError = { println("Error $it") },
        onComplete = { println("Completed Empty") }
    )
}