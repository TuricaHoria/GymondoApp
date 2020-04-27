package com.zignyl.gymondoapp.API

import com.zignyl.gymondoapp.Models.Result
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface APIServices {

    @GET("exercise")
    fun getAllExercises(
        @Query("page") page : Int
    ): Call<Result>

}