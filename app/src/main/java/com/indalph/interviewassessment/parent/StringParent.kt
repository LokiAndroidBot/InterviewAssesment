package com.indalph.interviewassessment.parent

import com.indalph.interviewassessment.println

fun doStringReverse() {

    //Default method to reverse a string
    val input = "India"
    input.reversed()
    println(input.reversed())


    //Default method to reverse a string using list
    println(input.toList().asReversed())

    //Reverse a string using
    for (i in input.length - 1 downTo 0) {
        //Log.e(printHeader, input[i])
    }

    //Reverse a string using downTo()
    for (i in input.toList().size - 1 downTo 0) {
        //Log.e(printHeader, input[i])
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
    println(recursionString(input))
    println(reverseSentenceRecursively(input))

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
                    println("The repeated occurence of the char - $firstOccurence is $counter")
                    counter = 1
                }
                firstOccurence = toCheck
            }
        }
    }
}
