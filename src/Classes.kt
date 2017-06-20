/**
 * Created by dqromney on 6/10/17.
 */
package KotlinSamples.Classes

class Person {
    lateinit var name: String
    // var name:String = ""
    var age: Int = -1

    val canVote: Boolean
        get() = age >= 16

    var ssn = "000-00-0000"
        get() = field
        set(value) {
            println("$name's SSN changed")
            field = value
        }

}

// A Primary constructor
// class Human(name:String, age:Int) {
class Human(var name: String, val age: Int) {
    init {
        println("human is initialized with $name and $age")
    }

}

// A Data class
data class Human1(var name: String, val age: Int) {
    // What you get
    // equals/hashCode
    // toString()
    // destructuring
    // copy()

    override fun toString(): String {
        return "My name is $name and I am $age years old"
    }
}

// Creating a point factory everytime you need one is a bit expensive, so we make it an object (singleton pattern)
//class PointFactory {
object PointFactory {
    fun NewCarterianPoint(x: Double, y: Double): Point {
        return Point(x, y)
    }

    fun NewPolarPoint(rho: Double, theta: Double): Point {
        return Point(rho * Math.cos(theta), rho * Math.sin(theta))
    }
}

class Point(var x: Double, var y: Double)

fun main(args: Array<String>) {
    // doProperties()
    // doPrimaryConstructors()
    // doDataClasses()
    doFactoryClasses()

}

fun doFactoryClasses() {
//    var pf = PointFactory()
//    var point = pf.NewPolarPoint(3.0, Math.PI/2)
    var point = PointFactory.NewPolarPoint(3.0, Math.PI/2)
}

fun doDataClasses() {
    // Data classes
    var h = Human1("David", 123)
    println(h)

    var h2 = Human1("David", 123)
    println(h == h2)  // true

    var h3 = h.copy()
    println(h === h3)  // false

    val (name, age) = h
    println("$name, $age")

    println(h.toString())
}

fun doPrimaryConstructors() {
    var h = Human("David", 123)
    println("My name is ${h.name} and I am ${h.age} years old")
    h.name = "Dave"
    // h.age = 12 // Cannot do because it is a val
    println("My name is ${h.name} and I am ${h.age} years old")

}

fun doProperties() {
    val me = Person()
    // kotlin.UninitializedPropertyAccessException: lateinit property name has not been initialized
    // println(me.name)
    me.name = "David"
    me.age = 12

    println("${me.name} can${if (!me.canVote) "not" else ""} vote")

    me.ssn = "1234567"
}

