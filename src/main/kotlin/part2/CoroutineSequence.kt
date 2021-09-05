package part2

/*
// 코틀린식 피보나치
fun main(args: Array<String>) {
    var a = 0
    var b = 1
    print("$a, ")
    print("$b, ")

    for (i in 2..9) {
        val c = a + b
        print("$c, ")
        a = b
        b = c
    }
}*/

fun main(args: Array<String>) {
    val fibonacciSeries = sequence {
        var a = 0
        var b = 1

        yield(a)
        yield(b)

        while (true) {
            val c = a + b
            yield(c)
            a = b
            b = c
        }
    }

    fibonacciSeries.take(10).forEach {
        print("$it, ")
    }
}
