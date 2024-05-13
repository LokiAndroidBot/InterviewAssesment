package com.indalph.interviewassessment

import android.util.Log
import com.indalph.interviewassessment.Logger.PRINT_HEADER

fun doStringReverse() {

    //Default method to reverse a string
    val input = "India"
    input.reversed()
    Log.e(PRINT_HEADER, input.reversed())


    //Default method to reverse a string using list
    Log.e(PRINT_HEADER, input.toList().asReversed().toString())

    //Reverse a string using
    for (i in input.length - 1 downTo 0) {
        //Log.e(printHeader, input[i].toString())
    }

    //Reverse a string using downTo()
    for (i in input.toList().size - 1 downTo 0) {
        //Log.e(printHeader, input[i].toString())
    }

    //Recursion Method with When
    fun recursionString(s1: String): String {
        return when (s1.isEmpty()) {
            true -> {
                ""
            }

            false -> {
                recursionString(s1.substring(1)) + s1[0]
            }
        }
    }

    //Recursion Method with If
    fun reverseSentenceRecursively(sentence: String): String {
        return if (sentence.isEmpty()) {
            ""
        } else {
            reverseSentenceRecursively(sentence.substring(1)) + sentence[0]
        }
    }
    Log.e(PRINT_HEADER, recursionString(input))
    Log.e(PRINT_HEADER, reverseSentenceRecursively(input))

}

fun doFindRepeatedCharacterOccurrence() {
    val a = "abcdef gjh aaa ndsdsds bbbbbb asdnksad 99999  00"
    val b = a.toList()
    var firstOccurence = 'b'
    var counter = 1
    for (toCheck in b) {
        //println(toCheck)
        when (firstOccurence) {
            toCheck -> {
                firstOccurence = toCheck
                counter++
            }

            else -> {
                if (counter > 2) {
                    Log.e(
                        PRINT_HEADER,
                        "The repeated occurence of the char - $firstOccurence is $counter"
                    )
                    counter = 1
                }
                firstOccurence = toCheck
            }
        }
    }
}
