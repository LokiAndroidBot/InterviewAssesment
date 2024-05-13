package com.indalph.interviewassessment

import android.util.Log
import com.indalph.interviewassessment.Logger.PRINT_HEADER
import java.time.LocalDate


class Person(val _name: String) {

    // Member Variables
    var name: String

    // Initializer Blocks
    init {
        Log.e(PRINT_HEADER, "This is first init block")
    }

    init {
        Log.e(PRINT_HEADER, "This is second init block")
    }

    init {
        Log.e(PRINT_HEADER, "This is third init block")
    }

    init {
        this.name = _name
        Log.e(PRINT_HEADER, "Name = $name")
    }
}


data class PersonV2(val firstname: String, val lastname: String) {
    lateinit var dateOfBirth: LocalDate

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PersonV2) return false

        if (firstname != other.firstname) return false
        if (lastname != other.lastname) return false
        if (dateOfBirth != other.dateOfBirth) return false

        return true
    }

    override fun hashCode(): Int {
        var result = firstname.hashCode()
        result = 31 * result + lastname.hashCode()
        result = 31 * result + dateOfBirth.hashCode()
        return result
    }

}


enum class CardType {
    SILVER, GOLD, PLATINUM
}

enum class CardTypeNew(val color: String) {
    SILVER("gray"), GOLD("yellow"), PLATINUM("black")
}

enum class CardTypeNew1 {
    SILVER {
        override fun calculateCashbackPercent() = 0.25f
    },
    GOLD {
        override fun calculateCashbackPercent() = 0.5f
    },
    PLATINUM {
        override fun calculateCashbackPercent() = 0.75f
    };

    abstract fun calculateCashbackPercent(): Float
}


data class Student1(val name: String)
class Student(name: String)

class Outer {
    var outer_public: Int = 10
    private val outer_private = 30

    fun printOuter() {
        this.javaClass.enclosingMethod?.let {
            Log.e(
                PRINT_HEADER, it.name
            )
        }
    }

    class NestedClass {
        //No Need to create a object for Outer class  to access inner class variable and methods
        fun printSomething(): String {
            return "From Nested Class"
        }

        fun accessOuterClassVariables() {

            // can not access the outer class property outer_public & outer_private
            //Log.e(printHeader, "outer_public = ${outer_public}")
            // can also access non-static member of outer class
            //Log.e(printHeader, "outer_y = $outer_private")
            // can also access a private member of the outer class
            //Log.e(printHeader, "outer_private = " + outer_private)

            //Log.e(printHeader, "Unable to call parent outer method = " + printOuter())
        }
    }

}

class Outer2 {

    val companyName: String = "Huawei"
    private val companySecret: String = "Secret"

    fun getSomething(): String = companyName

    inner class InnerClass {
        fun printSomething() {
            Log.e(PRINT_HEADER, companySecret)
        }
    }
}

// SINGLETON CLASS  is the singleton class here
object SINGLETON {
    var printName = "Loki"
}
