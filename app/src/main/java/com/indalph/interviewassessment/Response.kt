package com.indalph.interviewassessment

import com.google.gson.annotations.SerializedName

data class Response(
    @field:SerializedName("PARENT")
    val parent: ArrayList<String> = arrayListOf(),

    @field:SerializedName("STRING")
    val string: List<Item> = emptyList(),

    @field:SerializedName("VARIABLES")
    val variables: List<Item> = emptyList(),

    @field:SerializedName("CLASS")
    val clas: List<Item> = emptyList(),

    @field:SerializedName("FUNCTION")
    val function: List<Item> = emptyList(),

    @field:SerializedName("BASIC")
    val basic: List<Item> = emptyList(),
)

data class Item(

    @field:SerializedName("refList")
    val refList: List<String> = emptyList(),

    @field:SerializedName("message")
    val message: String = "",
)
