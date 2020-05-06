package com.zignyl.gymondoapp.Fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zignyl.gymondoapp.API.ClientRequestAPI
import com.zignyl.gymondoapp.Adapters.ExercisesAdapter
import com.zignyl.gymondoapp.Adapters.ImagesAdapter
import com.zignyl.gymondoapp.Models.Exercise
import com.zignyl.gymondoapp.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_detailed_exercise.*
import kotlinx.android.synthetic.main.fragment_exercises.*
import java.lang.RuntimeException
import kotlinx.android.synthetic.main.fragment_detailed_exercise.progress_bar as progress_bar1

class DetailedExerciseFragment : Fragment() {

    private lateinit var fragmentActions: FragmentActions
    private lateinit var exercise: Exercise
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mCompositeDisposable = CompositeDisposable()
        getExercise()

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

    private fun getExercise() {
        progress_bar.visibility = View.VISIBLE
        val id = arguments?.getInt(ARG_EXERCISEID) ?: return
        mCompositeDisposable = CompositeDisposable()
        mCompositeDisposable?.add(
            ClientRequestAPI.getSpecificExercise(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { result ->
                        exercise = result
                        setupFragment(exercise)
                        progress_bar.visibility = View.GONE

                    },
                    { ERROR ->
                        Log.e(TAG, "Error : ", ERROR)
                    }
                ))
    }

    private fun setupFragment(exercise: Exercise) {

        val title = tv_exercise_title
        title.text = exercise.name

        val category = tv_category_value
        category.text = exercise.category.toString()

        val muscles = tv_muscles_value
        muscles.text = exercise.muscles.toString()

        val equipment = tv_equipment_value
        equipment.text = exercise.equipment.toString()

        val description = tv_description_value
        description.text = exercise.description

    }
}