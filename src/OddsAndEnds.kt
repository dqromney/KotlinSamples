/**
 * Created by dqromney on 6/28/17.
 */
package KotlinSamples.OddsAndEnds

fun main(args: Array<String>) {
    // typealiases()
    // enumerations()
    // exceptions()
    operatorOverloads()
}

data class Vector(var x: Int, var y: Int) {
    operator fun plus(other: Vector): Vector {
        return Vector(x + other.x, y + other.y)
    }
}

fun operatorOverloads() {
    var v1 = Vector(3, 4)
    var v2 = Vector(11, 3)
    println(v1 + v2)
}

fun exceptions() {
    val v = arrayOf(1, 2, 3)

    try {
        println(v[10])
    } catch (e: ArrayIndexOutOfBoundsException) {
        println("we failed with ${e.toString()}")
    } finally {
        println("whatever")
    }

    val text = "123"

    val number: Int? = try {
        text.toInt()
    } catch(e: NumberFormatException) {
        null
    }
    println(number)
}

enum class Direction {North, South, East, West }

enum class Color(val rbg: Int) {
    Red(0xff0000) {
        override fun example(): String {
            return "blood"
        }
    },
    Green(0x00ff00) {
        override fun example(): String {
            return "grass"
        }
    },
    Blue(0x0000ff) {
        override fun example(): String {
            return "sky"
        }
    };

    abstract fun example(): String
}

fun enumerations() {
    val dir = Direction.East
    println(dir)

    var b = Color.Blue
    println("b has the name ${b.name} has value = ${b.rbg}, position = ${b.ordinal}")

    println("$b is the color of ${b.example()}")

    for (color in Color.values()) {
        println(color)
    }

    println("Value of red is ${Color.valueOf("Red").rbg}")
}

typealias FloatSet = Set<Float>

typealias MapWithStringKeys<T> = Map<String, T>

typealias Predicate<T> = (T) -> Boolean

typealias PropertyChangedHandler = (Object, String) -> Unit

class Bike {
    inner class Wheel {}
}

typealias BikeWheel = Bike.Wheel

fun typealiases() {
    // Provides shorter names, or consise names for long types
    fun typeAliases() {
        var f: FloatSet = setOf(1.2f, 2.3f)
        var m: MapWithStringKeys<Float> = mapOf("Hello".to(2.3f))
    }
}

fun <T> where(items: Sequence<T>, p: Predicate<T>): Sequence<T> {
    return items.filter { x -> p(x) }
}
