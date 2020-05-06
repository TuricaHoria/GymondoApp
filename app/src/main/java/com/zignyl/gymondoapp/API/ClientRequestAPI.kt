package com.zignyl.gymondoapp.API

import android.content.ContentValues
import android.util.Log
import com.zignyl.gymondoapp.Models.*
import io.reactivex.Observable
import org.json.JSONObject

object ClientRequestAPI {

    val service = RetrofitClientInstance.retrofitInstance.create(APIServices::class.java)

    fun getExercises(page: Int): Observable<Result> {
        Log.d(ContentValues.TAG, "Get the page of exercises")
        return service.getAllExercises(page)
    }

    fun getSpecificExercise(id: Int): Observable<Exercise> {
        Log.d(ContentValues.TAG, "Getting the exercise with the id $id")
        return service.getSpecificExercise(id)
    }

    fun getMuscles(): Observable<Muscles> {
        Log.d(ContentValues.TAG, "Getting the muscles list")
        return service.getMuscle()
    }

    fun getEquipment(): Observable<Equipment> {
        Log.d(ContentValues.TAG, "Getting the equipment list")
        return service.getEquipment()
    }

    fun getExerciseImage(exerciseId : Int): Observable<ExerciseImage> {
        Log.d(ContentValues.TAG, "Getting the exercise images")
        return service.getImageForExercise(exerciseId)
    }
}