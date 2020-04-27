package com.zignyl.gymondoapp.Models


data class Exercise(
    val id: Int,
    val license_author : String,
    val status : Int,
    val description: String,
    val name: String,
    val name_original : String,
    val creation_date : String,
    val uuid : String,
    val license : Int,
    val category : Int,
    val language : Int ,
    val muscles: MutableList<Int>,
    val muscles_secondary : MutableList<Int>,
    val equipment: MutableList<Int>
)