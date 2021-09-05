package part2

fun square(n: Int): Int {
    return n * n
}

fun timeAndSpace(n: Int) = n * n * n * n

fun main(args: Array<String>) {
    println("named pure func square = ${square(3)}")
    val qube = {n: Int -> n * n * n}
    println("lambda pure func qube = ${qube(3)}")
    println("Inline pure func time-and-space = ${timeAndSpace(3)}")
}