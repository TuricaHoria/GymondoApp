package com.zignyl.gymondoapp.Models

data class Result(val count : Int , val next : String , val previous : String , val results : MutableList<Exercise>)