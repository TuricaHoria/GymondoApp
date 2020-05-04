package com.zignyl.gymondoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zignyl.gymondoapp.API.APIServices
import com.zignyl.gymondoapp.API.RetrofitClientInstance
import com.zignyl.gymondoapp.Adapters.ExercisesAdapter
import com.zignyl.gymondoapp.Fragments.DetailedExerciseFragment
import com.zignyl.gymondoapp.Fragments.ExerciseFragment
import com.zignyl.gymondoapp.Fragments.FragmentActions
import com.zignyl.gymondoapp.Models.Exercise
import com.zignyl.gymondoapp.Models.Result
import kotlinx.android.synthetic.main.fragment_exercises.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(),FragmentActions {

    val manager = supportFragmentManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       replaceFragment(  TAG = ExerciseFragment::class.java.name)
    }

        override fun replaceFragment(bundle: Bundle?, TAG: String) {
            val transaction = manager.beginTransaction()

            val fragment = when (TAG) {
                ExerciseFragment::class.java.name ->
                    ExerciseFragment.newInstance(bundle)

                DetailedExerciseFragment::class.java.name ->
                    DetailedExerciseFragment.newInstance(bundle)



                else -> return
            }
            transaction.replace(R.id.frame, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        override fun addFragment(bundle: Bundle?, TAG: String) {
            val transaction = manager.beginTransaction()

            val fragment = when (TAG) {
                ExerciseFragment::class.java.name ->
                    ExerciseFragment.newInstance(bundle)

                DetailedExerciseFragment::class.java.name ->
                    DetailedExerciseFragment.newInstance(bundle)


                else -> return
            }

            transaction.add(R.id.frame, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        override fun removeFragment(TAG: String) {
        }

    fun replaceFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment, tag).addToBackStack("").commit()
    }




}
