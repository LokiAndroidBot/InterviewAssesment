package com.indalph.interviewassessment

import android.util.Log
import com.indalph.interviewassessment.Logger.PRINT_HEADER
import java.time.LocalDate
import kotlin.math.pow
import kotlin.system.measureNanoTime

class ClassExtensionParent

fun doInitBlock() {
    val person = Person("Loki")
}

fun doDataClass() {
    val p1 = PersonV2("Amanda", "Smith").also { it.dateOfBirth = LocalDate.of(1992, 8, 8) }
    val p2 = PersonV2("Amanda", "Smith").also { it.dateOfBirth = LocalDate.of(1976, 11, 18) }
    Log.e(PRINT_HEADER, (p1 == p2).toString())
}

fun doEnumClass() {
    Log.e(PRINT_HEADER, CardType.SILVER.toString())
    Log.e(PRINT_HEADER, CardTypeNew.SILVER.color)
    Log.e(PRINT_HEADER, CardTypeNew1.SILVER.calculateCashbackPercent().toString())
}

fun doNestedClass() {
    Log.e(PRINT_HEADER, Outer.NestedClass().printSomething())
}

fun doInnerClass() {
    Log.e(PRINT_HEADER, Outer2().InnerClass().printSomething().toString())
}

fun doSingletonClass() {
    Log.e(PRINT_HEADER, SINGLETON.printName)
}

fun <T> T.doGenericExtension(s: T): String {
    val returnData = this.toString() + s.toString()
    Log.e(PRINT_HEADER, "The value is: $returnData")
    return returnData
}

fun doGeneric() {
    fun <T> printValue(value: T) {
        Log.e(PRINT_HEADER, "The value is: $value")
    }

    fun printValueWithAny(value: Any) {
        Log.e(PRINT_HEADER, "The value is: $value")
    }

    val stringValue: String = "softAai Apps!"
    val intValue: Int = 42

    printValue(stringValue) // The value is: softAai Apps!
    printValue(intValue)    // The value is: 42

    printValueWithAny(stringValue)
    printValueWithAny(intValue)

    val heterogeneousList: List<Any> = listOf("softAai", 42, true)

    for (element in heterogeneousList) {
        when (element) {
            is String -> Log.e(PRINT_HEADER, "String: $element")
            is Int -> Log.e(PRINT_HEADER, "Int: $element")
            is Boolean -> Log.e(PRINT_HEADER, "Boolean: $element")
            else -> Log.e(PRINT_HEADER, "Unknown type")
        }
    }
}

fun doEqualsOperator() {
    val s1 = Student("Ramesh")
    val s2 = Student("Ramesh")

    Log.e(PRINT_HEADER, (s1 == s2).toString())    //false
    Log.e(PRINT_HEADER, (s1 === s2).toString())   //false

    Log.e(PRINT_HEADER, s1.toString())   //Student@36baf30c
    Log.e(PRINT_HEADER, s2.toString())   //Student@7a81197d

    val ss1 = Student1("Ramesh")
    val ss2 = Student1("Ramesh")
    Log.e(PRINT_HEADER, (ss1 == ss2).toString())    //true
    Log.e(PRINT_HEADER, (ss1 === ss2).toString())   //false

    val sss1 = Student1("Ramesh")
    val sss2: Student1 = sss1
    Log.e(PRINT_HEADER, (sss1 == sss2).toString())    //true
    Log.e(PRINT_HEADER, (sss1 === sss2).toString())   //true
}

fun doInfix() {
    class GFG {
        infix fun square(x: Int): Int {
            return x * x
        }
    }

    infix fun Number.toPowerOf(s: Number): String {
        return this.toDouble().pow(s.toDouble()).toString()
    }
    Log.e(PRINT_HEADER, (GFG() square 2).toString())
    Log.e(PRINT_HEADER, 5 toPowerOf 2)
}

/**
 * Inline Function - Sample
 */
inline fun Int.isAdult(): Boolean {
    return this >= 18
}

fun higherOrderFunction(lambda: (Int) -> Int): Int {
    return lambda.invoke(5) // Invoking the lambda with a value of 5
}

// Inline function that takes a lambda as a parameter
inline fun inlineHigherOrderFunction(lambda: (Int) -> Int): Int {
    return lambda(5) // Directly invoking the lambda with a value of 5 (no function call overhead)
}

fun doInline() {
    val regularResult = measureNanoTime {
        higherOrderFunction { value -> value * 2 }
    }
    val inlineResult = measureNanoTime {
        inlineHigherOrderFunction { value -> value * 2 }
    }
    val inlineResult1 = measureNanoTime {
        5.isAdult()
    }
    Log.e(PRINT_HEADER, "Result of regular higher-order function: $regularResult")
    Log.e(PRINT_HEADER, "Result of inline higher-order function: $inlineResult")
    Log.e(PRINT_HEADER, inlineResult1.toString()) // prints "true"
}
