/**
 * Created by dqromney on 6/26/17.
 */
package KotlinSamples.Interfaces

interface ISomething {
    fun doThings()

    val name: String get() = "something"
    val age: Int
    var foo:String
}

class Concrete(override var foo: String) : ISomething {

//    override var foo: String
//        get() = throw UnsupportedOperationException()
//        set(value) {
//        }

    override val age: Int = 12

    override fun doThings() {
        // ..
    }
}

fun main(args: Array<String>) {
    var c: ISomething = Concrete("another string")
    c.doThings()

    println(c.name)
    println(c.age)
    println(c.foo)
}
