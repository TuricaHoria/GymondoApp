package com.zignyl.gymondoapp.Fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zignyl.gymondoapp.R
import java.lang.RuntimeException

class DetailedExerciseFragment : Fragment() {

    private lateinit var fragmentActions: FragmentActions

    companion object {
        val TAG = DetailedExerciseFragment::class.java.simpleName
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
}