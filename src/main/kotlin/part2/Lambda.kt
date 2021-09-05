package part2

import java.util.*

fun main(args: Array<String>) {
    val sum = { x: Int, y: Int -> x + y}
    println("Sum ${sum(12, 14)}")
    // 15 까지 제한된 난수를 전달된 x와 곱한 후 결과를 반환
    val anonymousMult = {x: Int -> (Random().nextInt(15) + 1) * x}

    println("random output ${anonymousMult(2)}")
}