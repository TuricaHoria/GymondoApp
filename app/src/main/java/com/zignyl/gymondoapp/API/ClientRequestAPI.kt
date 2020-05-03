package com.zignyl.gymondoapp.API

import android.content.ContentValues
import android.util.Log
import com.zignyl.gymondoapp.Models.Exercise
import com.zignyl.gymondoapp.Models.Result
import io.reactivex.Observable
import org.json.JSONObject

object ClientRequestAPI {

    val service = RetrofitClientInstance.retrofitInstance.create(APIServices::class.java)

    fun getExercises(page : Int): Observable<Result> {
        Log.d(ContentValues.TAG, "Get the page of exercises")
        return service.getAllExercises(page)
    }
}