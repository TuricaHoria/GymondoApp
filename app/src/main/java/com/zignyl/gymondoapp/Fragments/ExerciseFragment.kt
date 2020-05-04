package com.zignyl.gymondoapp.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zignyl.gymondoapp.API.APIServices
import com.zignyl.gymondoapp.API.ClientRequestAPI
import com.zignyl.gymondoapp.API.RetrofitClientInstance
import com.zignyl.gymondoapp.Adapters.ExercisesAdapter
import com.zignyl.gymondoapp.InfiniteScrollListener
import com.zignyl.gymondoapp.MainActivity
import com.zignyl.gymondoapp.Models.Exercise
import com.zignyl.gymondoapp.Models.Result
import com.zignyl.gymondoapp.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_exercises.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.nio.charset.MalformedInputException

class ExerciseFragment : Fragment() {

    private var mAdapter: ExercisesAdapter? = null
    private var page = 1
    private var fragmentActions: FragmentActions? = null
    private var mCompositeDisposable: CompositeDisposable? = null


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
        getExercises(1)
    }

    fun getExercises(currentPage: Int) {

        mCompositeDisposable?.add(
            ClientRequestAPI.getExercises(currentPage)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { result ->

                        mAdapter = ExercisesAdapter(result.results) { exerciseId ->
                            val bundle = Bundle()
                            bundle.putInt(ARG_EXERCISEID, exerciseId)
                            fragmentActions?.replaceFragment(
                                bundle,
                                DetailedExerciseFragment::class.java.name
                            )
                        }
                        rv_exercices.adapter = mAdapter
                        rv_exercices.layoutManager =
                            LinearLayoutManager(context, RecyclerView.VERTICAL, false)

                    },
                    { ERROR ->

                        Log.e(TAG, "Error : ", ERROR)
                    }
                ))

    }

    private fun setUpRecyclerView(adapter: ExercisesAdapter) {

        rv_exercices.adapter = adapter
        rv_exercices.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }

}