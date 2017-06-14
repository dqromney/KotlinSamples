/**
 * Created by dqromney on 6/10/17.
 */
fun main(args: Array<String>) {
    // nullability()
    // if_statement()
    // flow_based_typing()
    // for_loop()
    when_expression()

}

@Suppress("UNUSED_VALUE", "ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE")
fun when_expression() {
    // Similar to switch in other languages; a bit more powerful
    val code = 33

    when (code) {
        get_france_dialing_code() -> println("France")
        44 -> println("UK")
        46 -> println("Sweden")
        39,379 -> println("Vatican")
        // !in 1..999 -> println("Unknown country code") // Valid also
        in 1..999 -> println("Unknown country code")
        else -> {
            println("Invalid country code")
        }
    }

    val z:Any = get_france_dialing_code()

    when (z) {
        is Int -> println("We got an int!")
        is String -> println("Text is " + z)
        else -> println("Don't know how to handle")
    }

    when {
        code > 0 -> println("Code positive")
        code < 0 -> println("Code negative")
        else -> println("Zero")
    }

    val output = when {
        code > 0 -> "Code positive"
        code < 0 -> "Code negative"
        else -> "Zero"
    }
    println("Output: $output")
    
}

fun get_france_dialing_code() = 33

@Suppress("UNUSED_VALUE", "ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE")
fun for_loop() {
    for (a in (10 downTo 1)) print("$a\t")
    println()

    val ints = intArrayOf(4,3,2,1)
    for((index,value) in ints.withIndex())
        println("$index:\t$value")

    val capitals = mapOf("Paris" to "France", "London" to "UK")
    for((capital,country) in capitals) {
        println("$capital:\t$country")
    }
}

@Suppress("UNUSED_VALUE", "ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE")
fun flow_based_typing() {
    var s:String? = "s;lajsdf"

    // println(s.length); // illegal
    if (s != null) { // Kotlin sees that this is in a null check block and allows, i.e. flow based typing
        println(s.length)
    }

    var x: Cloneable = intArrayOf(1,2,3)
    // println(x.size) // Illegal
    if (x is IntArray) {
        println(x.size)
    }

}

@Suppress("UNUSED_VALUE", "ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE")
fun if_statement() {
    // ? :  ~ none of this

    // With explicit assignement or ...
    var temp:Int = 20

    val feel:String
    if(temp < 10)
        feel = "cold"
    else if (temp > 20)
        feel = "warm"
    else feel = "OK"

    println("it feels $feel outside")

    // Or with intrinsic assignment
    if(temp < 10)
        "cold"
    else if (temp > 20)
        "warm"
    else "OK"

    println("it feels $feel outside")

    println("it feels ${if (temp > 20) "warm" else "okay"} today")
}

@Suppress("UNUSED_VALUE", "ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE")
fun nullability() {
    // var x:String = null // Not legal, Strings are not nullable, unless ...

    // You can't assign a null to a variable unless it has a ? after the type

    var x: String? = null // Can be null
    val len_x: Int? = x?.length // Elvis operator
    println(len_x)

    var y: String? = "Hello" // Can be null
    val len_y: Int? = y?.length // Elvis operator
    println(len_y)

    // But I need a value of length whether it is null or not
    var z: String? = null // Can be null
    val len_z: Int = z?.length ?: -1  // Elvis operator
    println(len_z)

    // What if you are ready for a null reference exception?
    var s:String? = "foo"
    // println(s.length);  // Not safe, due to it's nullability
    println(s!!.length);   // !! tells Kotlin to suppress the nullability checking
}