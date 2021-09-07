package part2

import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.Subject
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class CoroutineReactiveCalculator(a: Int, b: Int) {

    val subjectCalc: Subject<CoroutineReactiveCalculator> = PublishSubject.create()

    var nums: Pair<Int, Int> = Pair(0, 0)

    init {
        nums = Pair(a, b)

        subjectCalc.subscribe {
            with(it) {
                calculateAddition()
                calculateSubstraction()
                calculateMultiplication()
                calculateDivision()
            }
        }

        subjectCalc.onNext(this)
    }


    inline fun calculateAddition(): Int {
        val result = nums.first + nums.second
        println("Add = $result")
        return result
    }

    inline fun calculateSubstraction(): Int {
        val result = nums.first - nums.second
        println("Substract = $result")
        return result
    }

    inline fun calculateMultiplication(): Int {
        val result = nums.first * nums.second
        println("Multiplication = $result")
        return result
    }

    inline fun calculateDivision(): Int {
        val result = nums.first / nums.second
        println("Division = $result")
        return result
    }

    inline fun modifyNumbers(a: Int = nums.first, b: Int = nums.second) {
        nums = Pair(a, b)
        subjectCalc.onNext(this)

    }

    suspend fun handleInput(inputLine: String?) {
        if(!inputLine.equals("exit")) {
            val pattern: Pattern = Pattern.compile("([a|b])(?:\\s)?=(?:\\s)?(\\d*)");

            var a: Int? = null
            var b: Int? = null

            val matcher: Matcher = pattern.matcher(inputLine)

            if (matcher.matches() && matcher.group(1) != null && matcher.group(2) != null) {
                if(matcher.group(1).lowercase(Locale.getDefault()) == "a"){
                    a = matcher.group(2).toInt()
                } else if(matcher.group(1).lowercase(Locale.getDefault()) == "b"){
                    b = matcher.group(2).toInt()
                }
            }


            when {
                a != null && b != null -> modifyNumbers(a, b)
                a != null -> modifyNumbers(a = a)
                b != null -> modifyNumbers(b = b)
                else -> println("Invalid Input")

            }
        }
    }
}

fun main(args: Array<String>) {
    println("Initial Out put with a = 15, b = 10")

    val calculator: CoroutineReactiveCalculator = CoroutineReactiveCalculator(15, 10)
    println("Enter a = <number> or b = <number> in separate lines\nexit to exit the program")
    var line: String?
    do {
        line = readLine()
        runBlocking {
            async { calculator.handleInput(line) }
        }
    } while (line != null && !line.lowercase(Locale.getDefault()).contains("exit"))
}