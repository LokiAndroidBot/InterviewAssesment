package com.indalph.interviewassessment.parent

import com.indalph.interviewassessment.println

fun String.reverse(): String {
    return this.reversed()
}

class Initial {
    fun printMessage(e: String) {
        println(e)
    }
}

fun Initial.printNew(): String {
    return "Loki"
}

fun doExtensionString(): String {
    val s = "hello"
    val reversed = s.reverse() // "olleh"
    println(reversed)
    val obj = Initial().printNew()
    println(obj)
    return "https://pl.kotl.in/w5DVnGIXX"
}
