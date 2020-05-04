package com.zignyl.gymondoapp.Fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zignyl.gymondoapp.API.ClientRequestAPI
import com.zignyl.gymondoapp.Adapters.ExercisesAdapter
import com.zignyl.gymondoapp.Models.Exercise
import com.zignyl.gymondoapp.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_exercises.*
import java.lang.RuntimeException

class DetailedExerciseFragment : Fragment() {

    private lateinit var fragmentActions: FragmentActions
    private lateinit var exercise : Exercise
    private var mCompositeDisposable: CompositeDisposable? = null
    private var mAdapter: ExercisesAdapter? = null

    companion object {
        val TAG = DetailedExerciseFragment::class.java.simpleName
        const val ARG_EXERCISEID = "exerciseId"
        fun newInstance(bundle: Bundle?): DetailedExerciseFragment {
            val fragment = DetailedExerciseFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_detailed_exercise, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentActions) {
            fragmentActions = context
        } else {
            throw RuntimeException(context.toString() + "implement FragmentActions")
        }
    }

    fun getExercise (id : Int)
    {
        mCompositeDisposable?.add(
            ClientRequestAPI.getExercises(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { result ->

                        exercise = result.results[0]


                    },
                    { ERROR ->

                        Log.e(TAG, "Error : ", ERROR)
                    }
                ))

    }

}