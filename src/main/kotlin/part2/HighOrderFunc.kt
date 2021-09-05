package part2

fun Int.isEven(): Boolean = this % 2 == 0

// inline 키워드는 고차함수에 끼워넣을수 있으며 함수 자체와 전달된 람다 모두에 영향을 미친다. 성능 향상의 효과가 있다
inline fun highOrderFunc(a: Int, validityCheckFunc: (a: Int) -> Boolean) {
    if (validityCheckFunc(a)) {
        println("a $a is Valid")
    } else {
        println("a $a is Invalid")
    }
}

fun main(args: Array<String>) {
    highOrderFunc(12) { a: Int -> a.isEven() }
    highOrderFunc(19) { a: Int -> a.isEven() }
}
