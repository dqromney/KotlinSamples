/**
 * Created by dqromney on 6/10/17.
 */
package KotlinSamples.Classes

class Person {
    lateinit var name:String
    // var name:String = ""
    var age:Int = -1

    val canVote:Boolean
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
class Human(var name:String, val age:Int) {
    init {
        println("human is initialized with $name and $age")
    }
}

fun main(args: Array<String>) {
    // doProperties()
    doPrimaryConstructors()

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

