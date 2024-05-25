package com.indalph.interviewassessment.parent

import com.indalph.interviewassessment.println
import kotlin.random.Random

/**
 * Returns a string containing the sizes, minimum, and maximum values of various data types in Kotlin.
 * Each line in the output contains the size, minimum, and maximum values of a data type.
 */
fun dataTypeSize(): String {
    val dataTypes = listOf(
        Triple(Byte.SIZE_BITS, Byte.MIN_VALUE, Byte.MAX_VALUE),
        Triple(Short.SIZE_BITS, Short.MIN_VALUE, Short.MAX_VALUE),
        Triple(Int.SIZE_BITS, Int.MIN_VALUE, Int.MAX_VALUE),
        Triple(Long.SIZE_BITS, Long.MIN_VALUE, Long.MAX_VALUE),
        Triple(Float.SIZE_BITS, Float.MIN_VALUE, Float.MAX_VALUE),
        Triple(Double.SIZE_BITS, Double.MIN_VALUE, Double.MAX_VALUE),
        Triple(Char.SIZE_BITS, Char.MIN_VALUE, Char.MAX_VALUE)
    )

    val stringBuilder = StringBuilder()

    dataTypes.forEach { (size, min, max) ->
        stringBuilder.appendLine("Size is $size Min is $min Max is $max")
        stringBuilder.appendLine("")
    }
    println(stringBuilder)
    return "https://pl.kotl.in/DoLacWeEp"
}

/**
 * Demonstrates the usage of different variable types in Kotlin.
 * - [name] is a mutable variable that can be reassigned.
 * - [birthYear] is an immutable variable (val) that cannot be reassigned.
 * - [playerName] is a mutable variable that is initially "Virat" and then reassigned to "Kohli".
 * - [lastName] is an immutable variable (val) that cannot be reassigned.
 */
fun doVariableType(): String {
    var name = "John"
    val birthYear = 1975

    println("Name: $name")          // Print the value of name
    println("Birth Year: $birthYear")     // Print the value of birthYear

    var playerName = "Virat"
    playerName = "Kohli"   // Variable values can be changed

    println("Player Name: $playerName")

    val lastName = "Virat"
    //lastName = "Kohli"   // Error: Val cannot be reassigned

    println("Last Name: $lastName")
    return "https://pl.kotl.in/HjOVR7TQc"
}

/**
 * Demonstrates the usage of lazy initialization in Kotlin.
 * - [myLazyProperty] is a lazy-initialized property that is initialized with "Hello, World!".
 * - The lazy block is only executed once, the first time the property is accessed.
 */
fun doLazy(): String {
    val myLazyProperty: String by lazy {
        println("Initializing the variable")
        "Hello, World!"
    }

    println(myLazyProperty) // Prints "Initializing..." and "Hello, World!"
    println(myLazyProperty) // Prints "Hello, World!" (already initialized)

    return "https://pl.kotl.in/vBna5cz0z"
}

lateinit var myVariable: String

fun doLateInit(): String {
    // Check if myVariable is initialized
    println("Is myVariable initialized? ${::myVariable.isInitialized}")

    // Initialize myVariable
    myVariable = "GFG"

    // Check if myVariable is initialized
    println("Is myVariable initialized? ${::myVariable.isInitialized}")

    return "https://pl.kotl.in/cHOQyzMVC"
}

/**
 * Demonstrates the usage of nullable types and the safe call operator (?.) in Kotlin.
 * - [name] is a nullable String initialized to null.
 * - [length] is assigned the length of [name] using the safe call operator (?.).
 * - Prints the length of [name] or null if [name] is null.
 */
fun doNullable(): String {
    val name: String? = null
    val length = name?.length
    println(length)
    return "https://pl.kotl.in/bWwWiaQas"
}

fun doSafeCall(): String {
    var name: String? = null
    println(name?.length)
    return "https://pl.kotl.in/Ed9oa9xv-"
}

fun doElvis(): String {
    val name: String? = null
    val length = name?.length ?: 5
    println(length)
    return "https://pl.kotl.in/QF95peBO1"
}

fun doDoubleBang(): String {
    val name: String? = null
    println(name)
    val length = name!!.length
    println(length)
    return "https://pl.kotl.in/4Xkm6gcgl"
}

fun doBy(): String {
    val numbers = listOf(1, 2, 3, 4)
    val evenNumbers = numbers.filter { it % 2 == 0 }
    println(evenNumbers) // prints [2, 4]

    val test: (Int) -> Int = { it * 7 }

    val numbersNew = listOf(1, 2, 3, 4)
    numbersNew.forEach { println(it) }

    println(test(5))
    println(test(6))
    return "https://pl.kotl.in/d6y6pwqLD"
}

/**
 * Demonstrates the use of lambda expressions and higher-order functions in Kotlin.
 * - [lambdaExpression] is a lambda function that adds two integers.
 * - [listInt] is a list of integers.
 * - [mappedList] is the result of mapping each element in [listInt] to its value plus one.
 * - [result] is the result of invoking [lambdaExpression] with arguments 1 and 2.
 */
fun doIt(): String {
    val lambdaExpression = { a: Int, b: Int -> a + b }
    val listInt = listOf(1, 2, 3)
    val mappedList = listInt.map { it + 1 }
    val result = lambdaExpression(1, 2)

    println("Mapped list: $mappedList")
    println("Result of lambda expression: $result")

    return "https://pl.kotl.in/4Xkm6gcgl"
}

/**
 * Demonstrates the use of mutable and immutable variables in Kotlin.
 * Shows how mutable variables can be reassigned at runtime, while immutable
 * variables (declared with 'val') cannot.
 */
fun doMutableAndImmutable(): String {
    var name = "Virat"
    name = "Kohli"
    println(name) // Output: Kohli

    val lastName = "Virat"
    println(lastName) // Output: Virat
    return "https://pl.kotl.in/Dnh21sRdR"
}

/**
 * Demonstrates the difference between mutable and immutable lists in Kotlin.
 * - [l1] is a mutable list that allows adding elements after initialization.
 * - [l2] is an immutable list that cannot be modified after initialization.
 */
fun doMutableImmutableList(): String {
    // Mutable list: can be modified after initialization
    val l1 = mutableListOf(1, 2, 3)
    println("Mutable list: $l1")
    l1.add(4)
    println("After adding 4 to mutable list: $l1")

    // Immutable list: cannot be modified after initialization
    val l2 = listOf(4, 5, 6)
    println("Immutable list: $l2")
    // Adding element to an immutable list is not allowed
    // l2.add(4) // This would cause a compilation error
    println("Immutable list remains unchanged: $l2")
    return "https://pl.kotl.in/RcLOyTYO1"
}

/**
 * Function to demonstrate returning different types and random behavior.
 *
 * @return A sample URL as a String.
 */
fun doAnyType(): String {
    val a: String = "Loki"
    val b: Int = 1993

    // Print values based on iteration
    println(a)
    println(b.toString())

    // Randomly determine the return type message
    val result = if (Random.nextInt(1, 10) / 2 == 0) {
        println("The return type is string -> Loki")
        a
    } else {
        println("The return type is Int -> 1993")
        b
    }

    println(result)
    return "https://pl.kotl.in/n__PJUt4q"
}
