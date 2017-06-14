/**
 * Created by dqromney on 6/10/17.
 */
fun main(args: Array<String>) {
    // var_declarations()
    // ranges()
    // arrays()
    // characters_and_strings()
}

@Suppress("UNUSED_VALUE", "ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE")
fun characters_and_strings() {
    val a:Char = '\u0041'
    // if (a == 65) // Doesn't work
    if (a.toInt() == 65) {
        println("match")
    }

    val b:String = "Hello"
    println(b)
    println(b[0])
    for(letter in b) println(letter)

    // World String
    var raw = """hello
"test" and 'deploy' the
world~~~~"""
    println(raw)

    // Interpolation
    val c = 123.0
    val d = "c = $c, pring = ${'$'}${c/10}"
    println(d)

    // Just for fun
    val e = "${(10 downTo 1).toList().map{it.toString().toList()}.joinToString()}";
    println(e)

}

@Suppress("UNUSED_VALUE", "ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE")
fun arrays() {
    var names:Array<String> = arrayOf("David", "Christine", "Jill")
    names[0] = "Jack"
    println(names.toList())

    // var ages = intArrayOf(44, 30 , 29)  // or
    var ages: IntArray = intArrayOf(44, 30 , 29)
    println(ages.toList())

    // var values = Array<Double>(10, {2.0}); // or
    var values = Array(10, {2.0})
    println(values.toList())

    var squares = Array(10, {(it*it).toString()}) // Lambda function
    println(squares.toList())
}

@Suppress("UNUSED_VALUE", "ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE")
fun ranges() {
    // val a: IntRange = 1..10 // Closed range: 1,2,3,4,5,6..,10
    // val a: IntRange = 1.rangeTo(10)
    val a: IntProgression = 1.rangeTo(10).reversed()
    for (x in a) println(x)
    println("sum of ints $a = ${a.sum()}")

    // var b: 10..1
    // var b = 10.downTo(1) // Works
    var b = 10 downTo 1
    for (y in b) println(y)

    val m = 8
    val n = -3
    // val c = IntRange(m, n) // Does not work
    val c = IntRange(Math.min(m, n), Math.max(m, n)) // Does work
    for (z in c) println(z)

    // val d = 100.downTo(1).step(3) // Works
    val d = 100 downTo 1 step 3
    for(x in d) print("$x\t")
    println()
}

@Suppress("UNUSED_VALUE", "ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE")
fun var_declarations() {
    val a: Int = 64
    val b: Long = 123
    val c: Float = 2.4f // F
    val d: Double = 12.3e5
    println("$a $b $c $d")

    val e: Int

    e = a;
    // e = 4;

    val f: StringBuffer = StringBuffer("test")
    f.replace(0, 1, "T")
    // f = StringBuffer("sdkjak")
    println(f)

    var g: Int = 123
    g = 45

}
