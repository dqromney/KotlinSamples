/**
 * Created by dqromney on 6/28/17.
 */
package KotlinSamples.Reflection

import java.lang.reflect.Field
import java.lang.reflect.Method
import kotlin.reflect.*
import kotlin.reflect.jvm.javaField
import kotlin.reflect.jvm.javaGetter

fun main(args: Array<String>) {
    // classReferences()
    // functionReferences()
    // propertyReferences()
    // constructorReferences()
    boundReferences()
}

fun boundReferences() {
    var r = "\\d+".toRegex()

    var isNumber: KFunction1<@ParameterName(name = "input") CharSequence, Boolean> = r::matches

    var general: KFunction2<Regex, @ParameterName(name = "input") CharSequence, Boolean> = Regex::matches

    println(isNumber("321"))
    println(general(r, "321"))

    val strings = listOf("foo", "123", "1")
    println(strings.filter(isNumber))

    val lengthOfABC: KProperty0<Int> = "ABC"::length
    println(lengthOfABC.get())
}

class Person3

fun constructorReferences() {
    // Passing in the constructor
    makeAndPrint(::Person3)
}

fun <T> makeAndPrint(generator: () -> T) {
    val x: T = generator()
    println(x.toString())
}

var x = 1
val y = 22

class Person2(var age: Int)

val String.lastChar: Char
    get() = this[length - 1]

fun propertyReferences() {
    val a: KMutableProperty0<Int> = ::x
    val b: KProperty0<Int> = ::y

    println(a.get())
    a.set(321)
    // b.set(123)

    val strings = "this is fun".split(' ')
    println(strings.map(String::length))

    var person = Person2(33)
    println(person.toString())
    var age = Person2::age
    println("age = $age")
    println("age = ${age.get(person)}")


    var ls = String::lastChar
    println(ls.get("hello"))

    // Java reflection
    var javaGetter: Method? = Person2::age.javaGetter
    println("javaGetter : $javaGetter")
    var field: Field? = Person2::age.javaField
    println("field : $field");


}

fun functionReferences() {
    println(isOdd(3))

    var numbers = generateSequence(1, { it + 1 }).take(5)
    println(numbers.filter(::isOdd).toList())

    val predicate: (String) -> Boolean = ::isOdd

    // Functional composition
    // f(g(x))
    // let z = f >> g

    fun <A, B, C> compose(f: (B) -> C, g: (A) -> B): (A) -> C {
        return { x -> f(g(x)) }
    }

    var strings = "this is a fun experiment".split(' ')
    var oddLength = compose(::isOdd, String::length)
    println(message = strings.filter(predicate = oddLength))
}

fun isOdd(x: Int) = x % 2 != 0
fun isOdd(s: String) = s == "weird" || s == "strange" || s == "peculiar"

data class Person(var name: String, var age: Int)

open class Base(x: Int)
class Derived(x: Int) : Base(x)

fun classReferences() {
    // Kotlin reflection
    val c: KClass<Person> = Person::class
    println(c.qualifiedName)
    println(c.members.map { it.name })
    println("Is it a companion? ${c.isCompanion}")

    // Java reflection
    var j: Class<Person> = c.java
    println(j.simpleName)

    // Inheritance
    var z: Base = Derived(123)
    process(z)
}

fun process(b: Base) {
    if (b is Derived) { // This checks b as Derived, so it is 'Derived'
        println(b::class.qualifiedName)
    }
}