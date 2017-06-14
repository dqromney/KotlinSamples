package KotlinSamples.Hello

import KotlinSamples.Functions.*

/**
 * Hello World.
 *
 * Created by dqromney on 6/10/17.
 */
fun main(args: Array<String>) {
    println("Hello, Kotlin!")
    say_hello();
    println("4*3 = ${triple(4)}")
    println("pay ${calculate_wages(160, 100.0)}")
    println("pay ${calculate_wages(hours_worked= 140)}")
    println("pay ${calculate_wages()}")
    println("pay ${calculate_wages(80, 51.02)}")
    println("pay ${calculate_wages(hour_rate = 13.02)}")

    val stuff = intArrayOf(1,2,3)
    println("sum_up ${sum_up(9, 4 , *stuff, 7 ,12)}")

    val (x1, x2) = solve_quadratic_equation(1.0, 10.0, 16.0);
    println("quadraic x1 = $x1, x2 = $x2")

    val a = 3.0
    val b = 10.0
    println("The average of $a and $b is ${a averagedWith b}")
    
}

