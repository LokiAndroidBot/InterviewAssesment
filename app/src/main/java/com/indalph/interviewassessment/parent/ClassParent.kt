package com.indalph.interviewassessment.parent

import android.annotation.SuppressLint
import com.indalph.interviewassessment.CardType
import com.indalph.interviewassessment.CardTypeNew
import com.indalph.interviewassessment.CardTypeNew1
import com.indalph.interviewassessment.Outer
import com.indalph.interviewassessment.Outer2
import com.indalph.interviewassessment.Person
import com.indalph.interviewassessment.PersonV2
import com.indalph.interviewassessment.SINGLETON
import com.indalph.interviewassessment.Student
import com.indalph.interviewassessment.Student1
import com.indalph.interviewassessment.println
import java.time.LocalDate
import kotlin.math.pow
import kotlin.system.measureNanoTime

fun doInitBlock() {
    val person = Person("Loki")
}

@SuppressLint("NewApi")
fun doDataClass(): String {
    val p1 = PersonV2("Amanda", "Smith").also { it.dateOfBirth = LocalDate.of(1992, 8, 8) }
    val p2 = PersonV2("Amanda", "Smith").also { it.dateOfBirth = LocalDate.of(1976, 11, 18) }
    println((p1 == p2))
    return "https://pl.kotl.in/Ep4TafbaZ"
}

fun doEnumClass(): String {
    println(CardType.SILVER)
    println(CardTypeNew.SILVER.color)
    println(CardTypeNew1.SILVER.calculateCashbackPercent())
    return "https://pl.kotl.in/H5iBqvJ1F"
}

fun doNestedClass(): String {
    println(Outer.NestedClass().printSomething())
    return "https://pl.kotl.in/6Ec2atR1a"
}

fun doInnerClass(): String {
    println(Outer2().InnerClass().printSomething().toString())
    return "https://pl.kotl.in/qzizsXcCL"
}

fun doSingletonClass() {
    println(SINGLETON.printName)
}

fun <T> T.doGenericExtension(s: T): String {
    val returnData = this.toString() + s.toString()
    println("The value is: $returnData")
    return returnData
}

fun doGeneric() {
    fun <T> printValue(value: T) {
        println("The value is: $value")
    }

    fun printValueWithAny(value: Any) {
        println("The value is: $value")
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
            is String -> println("String: $element")
            is Int -> println("Int: $element")
            is Boolean -> println("Boolean: $element")
            else -> println("Unknown type")
        }
    }
}

fun doEqualsOperator() {
    val s1 = Student("Ramesh")
    val s2 = Student("Ramesh")

    println((s1 == s2))    //false
    println((s1 === s2))   //false

    println(s1)   //Student@36baf30c
    println(s2)   //Student@7a81197d

    val ss1 = Student1("Ramesh")
    val ss2 = Student1("Ramesh")
    println((ss1 == ss2))    //true
    println((ss1 === ss2))   //false

    val sss1 = Student1("Ramesh")
    val sss2: Student1 = sss1
    println((sss1 == sss2))    //true
    println((sss1 === sss2))   //true
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
    println((GFG() square 2))
    println(5 toPowerOf 2)
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
    println("Result of regular higher-order function: $regularResult")
    println("Result of inline higher-order function: $inlineResult")
    println(inlineResult1) // prints "true"
}
