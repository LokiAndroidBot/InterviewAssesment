package com.indalph.interviewassessment.parent

import com.indalph.interviewassessment.println

//Public - Visibility Modifiers
// by default public
class A {
    var int = 10
}

// specified with public modifier
class B {
    var int2 = 20
    fun display() {
        println("Accessible everywhere")
    }
}

fun doPublicVisibility(): String {
    val b = B()
    val a = A()
    println(b.int2)
    b.display()
    return "https://pl.kotl.in/rnNGi_s1p"
}

private class A1 {
    private val tmp = 10
    private fun doSomething() {
        println(tmp)
    }

    fun display() {
        // we can access int in the same class
        println(tmp)
        println("Accessing int successful")
    }
}

fun doPrivateVisibility(): String {
    val a = A1()
    a.display()

    // Cannot access 'tmp': it is private in class A1
    // Log.e(PRINT_HEADER, a.tmp)

    // Cannot access 'doSomething': it is private in 'A1'
    // a.doSomething()
    return "https://pl.kotl.in/C-HXw_Rqd"
}

// base class
open class A2 {
    // protected variable
    protected val int = 10
}

// derived class
class B1 : A2() {
    fun getvalue(): Int {
        // accessed from the subclass
        return int
    }
}

fun doProtectedVisibility(): String {
    var a = B1()
    println("The value of integer is: " + a.getvalue())
    return "https://pl.kotl.in/pp94TlMkt"
}


fun doDiffEquality(): String {
    data class Student1(val name: String)
    class Student(name: String)

    val s1 = Student("Ramesh")
    val s2 = Student("Ramesh")

    println(s1 == s2)    //false
    println(s1 === s2)   //false

    println(s1)   //Student@36baf30c
    println(s2)   //Student@7a81197d

    val ss1 = Student1("Ramesh")
    val ss2 = Student1("Ramesh")
    println(ss1 == ss2)    //true
    println(ss1 === ss2)   //false

    val sss1 = Student1("Ramesh")
    val sss2: Student1 = sss1
    println(sss1 == sss2)    //true
    println(sss1 === sss2)   //true
    return "https://pl.kotl.in/TT_EkTXEv"
}

/**
 * Demonstrates the usage of initializer blocks and primary constructors in Kotlin classes.
 * - [Add] is a class with a primary constructor that calculates the sum of two numbers.
 * - [Person] is a class with initializer blocks that demonstrate different ways of initializing properties.
 */
fun doInit(): String {
    // Primary constructor
    class Add(a: Int, b: Int) {
        var c = a + b
    }

    class Person(val _name: String) {
        // Member Variable
        var name: String

        // Initializer Blocks
        init {
            println("This is first init block")
        }

        init {
            println("This is second init block")
        }

        init {
            println("This is third init block")
        }

        init {
            this.name = _name
            println("Name = $name")
        }
    }

    val add = Add(5, 6)
    println("The Sum of numbers 5 and 6 is: ${add.c}")

    val person = Person("Geeks")
    return "https://pl.kotl.in/nB6rHuFzk"
}
