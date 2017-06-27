/**
 * Created by dqromney on 6/19/17.
 */
package KotlinSamples.Delegation

import kotlin.properties.Delegates
import kotlin.reflect.KProperty

// Why? Use this if you common functionality for your getters and setters, lazy initialization
class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name} in $thisRef.'")
    }
}

class Person {
    var name: String by Delegate()

    val lazyValue: String by lazy {
        println("lazyValue initialized!")
        "test"
    }

    var otherName: String by Delegates.observable("John") {
        prop, old, new ->
        println("$old -> $new")
    }
}

fun main(args: Array<String>) {
    // lazy()
    multifunctionDevice()
}


fun lazy() {
    var p = Person()
    p.name = "David"
    val s = p.name
    println(s)

    println(p.lazyValue)
    println(p.lazyValue)
    println(p.lazyValue)

    p.otherName = "Jack"
    p.otherName = "Jill"
}

// Interface Segregation Pattern
interface IPrinter {
    fun print()
}

interface IScanner {
    fun scan()
}

class Printer : IPrinter {
    override fun print() {
        println("Printing a document")
    }
}

class Scanner : IScanner {
    override fun scan() {
        println("Scanning a document")
    }
}

// Classical way, but Kotlin has a better way
class MultiFunctionDevice1 : IPrinter, IScanner {
    val printer = Printer()
    val scanner = Scanner()

    override fun print() {
        printer.print()
    }

    override fun scan() {
        scanner.scan()
    }
}

// Delegation, 'by' provided
class MultiFunctionDevice2(printer: IPrinter = Printer(), scanner: IScanner = Scanner())
    : IPrinter by printer, IScanner by scanner {}

fun multifunctionDevice() {
    val mfd1 = MultiFunctionDevice1()
    mfd1.print()
    mfd1.scan()

    val mfd2 = MultiFunctionDevice2()
    mfd2.print()
    mfd2.scan()

}

