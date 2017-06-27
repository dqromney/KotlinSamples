/**
 * Created by dqromney on 6/26/17.
 */
package KotlinSamples.CollectionDemo

import java.lang.Math.pow
import java.util.*


fun main(args: Array<String>) {
    // generation()
    // quantifiers()
    // projection()
    // flatMap()
    // aggregation()
    // filtering()
    // partitoning()
    // grouping()
    // sorting()
    elementOperations()
}

fun elementOperations() {
    var numbers = listOf(1, 2, 3)
    println("Set of numbers:  $numbers")
    println("First element is ${numbers.first()}")
    println("First element > 2? ${numbers.first { it > 2 }}")
    // Exception in thread "main" java.util.NoSuchElementException: Collection contains no element matching the predicate.
    // println("First element > 10? ${numbers.first { it > 10 }}")
    println("First element > 10? ${numbers.firstOrNull { it > 10 }}")

    println("Last number: ${numbers.last()}")
    println("Last number less than 3: ${numbers.last{it<3}}")

    // Single
    val x = listOf(1)
    println("Single element : ${x.single()}")
    // Exception in thread "main" java.lang.IllegalArgumentException: List has more than one element.
    // println("Single element : ${numbers.single()}")

    println("three : ${numbers.single{it == 3}}")
    println("Single or null in numbers : ${numbers.singleOrNull()}")

    println("element at position 1 : ${numbers.elementAt(1)}")
    // Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: 4
    //println("element at position 1 : ${numbers.elementAt(4)}")
    println("element at position 4 : ${numbers.elementAtOrNull(4)}")

    println("item at position 100 : ${numbers.elementAtOrElse(100, {-it})}")
}

data class Person(var name: String, var age: Int)

fun sorting() {
    val rand = Random()
    val randomValues = generateSequence { rand.nextInt(10) - 5 }.take(10).toList()

    println("Set of random numbers:  $randomValues")

    println("Sorted random numbers (asc) : ${randomValues.sorted()}")
    println("Sorted random numbers (desc) : ${randomValues.sortedDescending()}")

    var people = listOf(
            Person("Adam", 36),
            Person("Boris", 18),
            Person("Claire", 36),
            Person("Adam", 20),
            Person("Jack", 20)
    )

    println("Set of people : $people")
    println("Sorted people (asc) : ${people.sortedBy { it.name }}")
    println("Sorted people (desc) : ${people.sortedByDescending { it.name }}")

    println("Sorted people with age, name : ${people.sortedWith(compareBy(Person::age, Person::name))}")

    println("Sorted people with name, age: ${people.sortedWith(compareBy({ it.name }, { it.age }))}")

    println("Sorted people with age and then by desc name : ${people.sortedWith(compareBy<Person> { it.age }.thenByDescending { it.name })}")

}


fun grouping() {
    val people = listOf(
            Person("Adam", 36),
            Person("Boris", 18),
            Person("Claire", 36),
            Person("Adam", 20),
            Person("Jack", 20)
    )

    val byName: Map<String, List<Person>> = people.groupBy(Person::name)
    println("\nList grouped by name: $byName")

    // Using a selector
    val byAgeNames = people.groupBy({ it.age }, { it.name })
    for (i in byAgeNames) {
        println("These people are ${i.key} years old")
        for (name in i.value) {
            println(" - $name")
        }
    }

}

fun partitoning() {
    var seq = arrayOf(-2, -1, 0, 1, 2)
    val (neg, others) = seq.partition { it < 0 }
    println(seq.toList())
    println("Negative numbers: $neg")
    println("Other numbers: $others")

    var numbers = arrayOf(3, 3, 2, 2, 1, 1, 2, 2, 3, 3)
    println("\nNumber set: ${numbers.toList()}")
    println("Drop first two, take only 6: ${numbers.drop(2).take(6)}")

    println("Take two from an empty array: ${arrayOf<Any>().take(2)}")

    println("Take all numbers > 1 : ${numbers.takeWhile { it > 1 }}")
    println("Drop while number == 3 : ${numbers.dropWhile { it == 3 }}")

    println("Drop last 4 numbers: ${numbers.dropLast(4)}")
}

fun filtering() {
    val seq = generateSequence(1, { it + 1 })
    val numbers = seq.take(10).toList()
    println(numbers)

    val evenNumbers = numbers.filter { it % 2 == 0 }
    println("Even numbers: $evenNumbers")

    val oddNumbers = numbers.filterNot { it % 2 == 0 }
    println("Odd numbers: $oddNumbers")

    val notDivBy3 = numbers.filterNot { it % 3 == 0 }
    println("Not divisible by 3: $notDivBy3")

    // Combine filtering and projection
    val oddSquares = numbers.map { it * it }.filterNot { it % 2 == 0 }
    println("Odd Squares: $oddSquares")

    val values = arrayOf<Any>(1, 2.5, 3, 4.56)
    val wholeNumbers = values.filter { it is Int }
    println("Whole numbers: $wholeNumbers")

    val moreValues = arrayOf<Int?>(1, 2, 3, null, 4, 5)
    val nonNullValues = moreValues.filterNotNull()
    println("Non null values: $nonNullValues")

}

fun aggregation() {
    val numbers = generateSequence(1, { it + 1 }).take(10).toList()
    println(numbers)

    println(numbers.joinToString("->"))

    // reduce x[0] + x[1]
    // reduceRight x[last] + x[last-1]
    // Reduce: Sum, Product, p=partial
    println("sum (reduce) = ${numbers.reduce { x, p -> x + p }}")
    println("\nproduct (reduce) = ${numbers.reduceRight {
        x, p ->
        print("($x * $p)")
        x * p
    }}")
    println("sum = ${numbers.sum()}, average = ${numbers.average()}")

    // Sum of squares
    println("sum of squares = ${numbers.sumBy { it * it }}")
    // Sum of roots
    println("sum of roots = ${numbers.sumByDouble { Math.sqrt(it.toDouble()) }}")

    // Fold: seed + x[0]
    println("sum (fold) = ${numbers.fold(0, { x, p -> x + p })}")
    println("product (fold) = ${numbers.fold(1, { x, p -> x * p })}")

    var three = numbers.take(3)

    // Polynomial: with x=3, coeff = a=1,b=2,c=3
    // ax^2 + bx + c = 1*3^2 + 2*3 + 3 = 18
    // seed = 0, i=index, p=partial result, c=current value that you currently processing
    println("poly = ${numbers.foldIndexed(0, { i, p, c ->
        var xp = pow(3.0, (2 - i).toDouble()).toInt()
        println("i = $i xp = $xp,  c = $c")
        c * xp + p
    })}")

    // Ordering of elements
    // 1,2,3 -> it^2
    // left to right sum of squares
    // ((1^2 + 2)^2 + 3)^2 = 144
    println("${three.fold(0, { p, x -> (p + x) * (p + x) })}")
    // right to left sum of squares
    // ((3^2 + 2)^2 + 1)^2 = 122^2 = 14884
    println("${three.foldRight(0, { p, x -> (p + x) * (p + x) })}")
}

fun flatMap() {
    val sequences = listOf("red, green, blue", "orange", "white,pink")
    val allWords = sequences.map { it.split(',') }
    println(allWords.toList())
    // [[red,  green,  blue], [orange], [white, pink]]

    val allWordsFlat = sequences.flatMap { it.split(',') }
    println(allWordsFlat.toList())
    // [red,  green,  blue, orange, white, pink]

    // Creating a cartesian product of both arrays
    val objects = arrayOf("house", "car", "bicycle")
    val colors = arrayOf("red", "green", "blue")

    var pairs: List<String> = objects.flatMap { o -> colors.map { c -> "$c $o" } }
    println(pairs)
    // [red house, green house, blue house, red car, green car, blue car, red bicycle, green bicycle, blue bicycle]

}

fun projection() {
    val seq: Sequence<Int> = generateSequence(1, { it + 1 })
    val numbers: Sequence<Int> = seq.take(4)

    val squares: Sequence<Int> = numbers.map { it * it }
    println(squares.toList())

    // Work with text
    val sentence = "This is a nice sentence"
    val wordLengths = sentence.split(' ').map { it.length }.asSequence()
    println(wordLengths.toList())

    // How could I keep both the word and the length?
    val wordsWithLength = sentence.split(' ').map {
        object {
            val length = it.length
            val word = it
        }
    }
    // One way, but there is another way...
    for (wl in wordsWithLength) {
        println("'${wl.word}' has length ${wl.length}")
    }

    // Using pairs or sequence of pairs
    val wordLengthPairs = sentence.split(' ').associate { it.to(it.length) }
    for (wl in wordLengthPairs) {
        println(wl)
    }
}

fun quantifiers() {
    var numbers: Sequence<Int> = arrayOf(1, 2, 3, 4, 5).asSequence()
    println("Are all numbers > 0? ${numbers.all { it > 0 }}")
    println("Are all numbers odd? ${numbers.all { it % 2 == 1 }}")
    println("Any numbers > 1? ${numbers.any { it > 1 }}")

    println(emptyArray<Int>().any())

    println("Contains 5? ${numbers.contains(5)}")
    println("Total number of elements: ${numbers.count()}")
    println("Total number of elements > 3 : ${numbers.count { it > 3 }}")

}

fun generation() {
    var gen = generateSequence(1, { it + 1 })
    var numbers = gen.take(10)
    println(numbers.toList())
}