package com.indalph.interviewassessment

import android.util.Log
import com.indalph.interviewassessment.Logger.PRINT_HEADER
import kotlin.random.Random

fun dataTypeSize(): String {
    Log.e(
        PRINT_HEADER,
        "Byte Size is ${Byte.SIZE_BITS} Min is ${Byte.MIN_VALUE}  Max is ${Byte.MAX_VALUE}"
    )
    Log.e(
        "", "Short Size is ${Short.SIZE_BITS} Min is ${Short.MIN_VALUE}  Max is ${Short.MAX_VALUE}"
    )
    Log.e(
        PRINT_HEADER,
        "Int Size is ${Int.SIZE_BITS} Min is ${Int.MIN_VALUE}  Max is ${Int.MAX_VALUE}"
    )
    Log.e(
        PRINT_HEADER,
        "Long Size is ${Long.SIZE_BITS} Min is ${Long.MIN_VALUE}  Max is ${Long.MAX_VALUE}"
    )
    Log.e(
        "", "Float Size is ${Float.SIZE_BITS} Min is ${Float.MIN_VALUE}  Max is ${Float.MAX_VALUE}"
    )
    Log.e(
        "",
        "Double Size is ${Double.SIZE_BITS} Min is ${Double.MIN_VALUE}  Max is ${Double.MAX_VALUE}"
    )
    Log.e(
        PRINT_HEADER,
        "Char Size is ${Char.SIZE_BITS} Min is ${Char.MIN_VALUE}  Max is ${Char.MAX_VALUE}"
    )
    return "https://pl.kotl.in/6broB_6Bn"
}


fun doLazy(): String {
    val myLazyProperty: String by lazy {
        Log.e(PRINT_HEADER, "Initializing the variable")
        "Hello, World!"
    }

    Log.e(PRINT_HEADER, myLazyProperty) // "Initializing..." and "Hello, World!"
    Log.e(PRINT_HEADER, myLazyProperty) // "Hello, World!" (already initialized)
    return "https://pl.kotl.in/6broB_6Bn"
}

fun doLateInit() {
    lateinit var myVariable: String

    myVariable = ""
    when (myVariable.isBlank()) {
        true -> Log.e(PRINT_HEADER, true.toString())
        false -> Log.e(PRINT_HEADER, false.toString())

    }
    // Check using isInitialized method
    Log.e(PRINT_HEADER, "Is myVariable initialized? " + myVariable.isBlank())

    // initializing myVariable
    myVariable = "GFG"

    // Check using isInitialized method
    Log.e(PRINT_HEADER, "Is myVariable initialized? " + myVariable.isBlank())
}

fun doDoubleBang() {
    val name: String? = null
    Log.e(PRINT_HEADER, name.toString())
    val length = name!!.length
}

fun doMutableImmutable() {
    var name = "Virat"
    name = "Kohli"
    Log.e(PRINT_HEADER, name)
    //The above example is used by mutable variable. We can change the value of a variable in RunTime.


    val lastName = "Virat"
    //lastName = "Kohli"
    Log.e(PRINT_HEADER, lastName)

    //Val cannot be reassigned

}

fun doMutableImmutableList() {

    // The collection should remain unchanged after initialization.
    //You only need to read the elements and don't require modification operations.
    //Immutability is preferred for functional programming style or data integrity.
    val l1 = mutableListOf(1, 2, 3)
    Log.e(PRINT_HEADER, l1.toString())
    l1.add(4)
    Log.e(PRINT_HEADER, l1.toString())

    //The collection needs to be modified dynamically.
    //You want to add, remove, or update elements during the course of your program.
    //Flexibility and mutability are essential for your use case.
    val l2 = listOf(4, 5, 6)
    Log.e(PRINT_HEADER, l2.toString())
    //l2.add(4)
    Log.e(PRINT_HEADER, l2.toString())
}


fun doNullable() {
    val name: String? = null
    val length = name?.length
    Log.e(PRINT_HEADER, length.toString())
}

fun doElvis() {
    val name: String? = null
    val length = name?.length ?: 5
    Log.e(PRINT_HEADER, length.toString())
}

fun doAnyType(): Any {
    var a: Any
    var b: Any
    for (i in 1..2) {
        when (i) {
            1 -> {
                a = "Loki"
                Log.e(PRINT_HEADER, a)
            }

            2 -> {
                b = 1993.2020
                Log.e(PRINT_HEADER, b.toString())
            }
        }
    }
    when (Random.nextInt(1, 10) / 2) {
        0 -> {
            Log.e(PRINT_HEADER, "The return type is string -> Loki")
            return "Loki"
        }

        else -> {
            Log.e(PRINT_HEADER, "The return type is Int -> 1993")
            return 1993
        }
    }
}
