package com.zignyl.gymondoapp.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zignyl.gymondoapp.API.APIServices
import com.zignyl.gymondoapp.API.RetrofitClientInstance
import com.zignyl.gymondoapp.Adapters.ExercisesAdapter
import com.zignyl.gymondoapp.InfiniteScrollListener
import com.zignyl.gymondoapp.MainActivity
import com.zignyl.gymondoapp.Models.Exercise
import com.zignyl.gymondoapp.Models.Result
import com.zignyl.gymondoapp.R
import kotlinx.android.synthetic.main.fragment_exercises.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.nio.charset.MalformedInputException

class ExerciseFragment : Fragment() {

    private var mAdapter: ExercisesAdapter? = null
    private lateinit var exerciese: MutableList<Exercise>
    private var page = 1
    private var fragmentActions: FragmentActions? = null

    companion object {
        const val ARG_EXERCISEID = "exerciseId"
        const val TAG = "ExerciseFragment"
        fun newInstance(bundle: Bundle?): ExerciseFragment {
            val fragment = ExerciseFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_exercises, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        rv_exercices.apply {
            setHasFixedSize(true)
            val linearLayout = LinearLayoutManager(context)
            layoutManager = linearLayout
            clearOnScrollListeners()
            addOnScrollListener(InfiniteScrollListener({ getExercises(++page) }, linearLayout))
        }


        if(savedInstanceState == null)
        {
            getExercises(page)
        }
    }

    fun getExercises(currentPage: Int) {
        val request = RetrofitClientInstance.buildService(APIServices::class.java)

        val call = request.getAllExercises(currentPage)
        call.enqueue(object : Callback<Result> {
            override fun onFailure(call: Call<Result>, t: Throwable) {
                Toast.makeText(
                    context,
                    "Couldn't fetch the exercises",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onResponse(call: Call<Result>, response: Response<Result>) {

                val body = response.body()
                exerciese = body!!.results
                mAdapter = ExercisesAdapter(exerciese) { id ->
                    val bundle = Bundle()
                    bundle.putInt(ARG_EXERCISEID, id)
                    fragmentActions?.replaceFragment(bundle ,  DetailedExerciseFragment.TAG)
                }
                setUpRecyclerView(mAdapter!!)
                page++
            }
        })

    }

    private fun setUpRecyclerView(adapter: ExercisesAdapter) {

        rv_exercices.adapter = adapter
        rv_exercices.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }

}