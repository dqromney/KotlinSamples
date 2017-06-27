/**
 * Created by dqromney on 6/12/17.
 */
package KotlinSamples.Lambdas

class Average {
    public operator fun invoke(vararg values: Double): Double {
        var sum = 0.0
        for (x in values)
            sum += x
        return sum / values.size
    }
}

fun main(args: Array<String>) {
    //val product = { x: Int, y: Int -> x * y }  // or
    val product: (Int, Int) -> Int = { x, y -> x * y }
    println("2*3 = ${product(2, 3)}")

    val numbers = listOf(7, 1, 3, 4)
    // val n = numbers.count({x -> x > 3}) // or
    // val n = numbers.count{x -> x > 3} // or
    val n = numbers.count { it > 3 }  // for single parameters, Kotlin calls this 'it'
    println(n)

    var sum = 0
    numbers.forEach { sum += it }
    println("the sum of $numbers is $sum")

    // Higher order functions
    var maxvalue = max(numbers, { x, y -> x < y })
    println(maxvalue)

    // Example of receivers
    val increaseBy = fun Int.(value: Int) = this + value
    val x = 1
    println("$x + 3 = ${x.increaseBy(3)}")

    // Default function for a class or "Functore"
    val a = Average()
    val avg = a(1.0, 2.0, 3.0)
    println(avg)

    operator fun String.invoke():String {
        return "(" + this + ")"
    }
    val two = "two"
    println("I have 2 ${two()} apples")
}

fun <T> max(collection: Collection<T>, less: (T, T) -> Boolean): T? {
    var max: T? = null
    for (x in collection)
        if (max == null || less(max, x))
            max = x
    return max
}


