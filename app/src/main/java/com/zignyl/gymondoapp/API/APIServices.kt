package com.zignyl.gymondoapp.API

import com.zignyl.gymondoapp.Models.*
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface APIServices {

    @GET("exercise")
    fun getAllExercises(
        @Query("page") page: Int
    ): Observable<Result>

    @GET("exercise/{id}")
    fun getSpecificExercise(
        @Path("id") id: Int
    ): Observable<Exercise>

    @GET("equipment")
    fun getEquipment(): Observable<Equipment>

    @GET("muscle")
    fun getMuscle(): Observable<Muscles>

    @GET("exerciseimage")
    fun getImageForExercise( @Query("exercise") id: Int) : Observable<ExerciseImage>



}

