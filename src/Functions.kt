/**
 * Created by dqromney on 6/10/17.
 */
package KotlinSamples.Functions

// Everything lives in a package, unless specified it goes to the default package
// See Hello.kt for use
// Unit is Kotlin's equivalent of void, it is optional
fun say_hello(): Unit {
    println("Hello")
}

// This is the same...
//fun triple(x:Int): Int {
//    return x*3;
//}

// as this.
fun triple(x: Int) = x * 3;

// Without default values
//fun calculate_wages(hours_worked:Int, hour_rate:Double): Double {
//    return hours_worked * hour_rate
//}

// With default values
fun calculate_wages(hours_worked: Int = 160, hour_rate: Double = 100.0): Double {
    return hours_worked * hour_rate
}

fun sum_up(vararg values: Int): Int {
    var result = 0
    for (v in values)
        result += v
    return result
//    return  values.sum();
}

fun solve_quadratic_equation(a: Double, b: Double, c: Double): Pair<Double, Double> {
    //    fun calculate_discriminant(a:Double, b:Double, c:Double) : Double { // or
    // arguments taken from the outer arguments
    fun calculate_discriminant(): Double {
        return b * b - 4 * a * c;
    }
    // val root_disc = Math.sqrt(calculate_discriminant(a,b,c))
    val root_disc = Math.sqrt(calculate_discriminant())
    return Pair((-b + root_disc) / (2 * a), (-b - root_disc) / (2 * a))
}

fun infix_functions() {
    val x  = 'z' downTo 'a'

    val capitals = mapOf("Paris" to "France", "London" to "UK")
}

infix fun Double.averagedWith(other:Double):Double {
    return (this + other) / 2.0
}