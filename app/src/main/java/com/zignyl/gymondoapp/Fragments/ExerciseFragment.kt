package com.zignyl.gymondoapp.Fragments


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zignyl.gymondoapp.API.ClientRequestAPI
import com.zignyl.gymondoapp.Adapters.ExercisesAdapter
import com.zignyl.gymondoapp.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_exercises.*


class ExerciseFragment : Fragment() {

    private var mAdapter: ExercisesAdapter? = null
    private var currentPage = 1
    private var isLoading = false
    private var fragmentActions: FragmentActions? = null
    private var mCompositeDisposable: CompositeDisposable? = null
    var searchView : androidx.appcompat.widget.SearchView ?=null



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
        val linearLayoutManager = LinearLayoutManager(context)
        rv_exercices.layoutManager = linearLayoutManager
        getExercises(currentPage)

        rv_exercices.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    val visibleItemCount = linearLayoutManager.childCount
                    val pastVisibleItem =
                        linearLayoutManager.findFirstCompletelyVisibleItemPosition()
                    val total = mAdapter!!.itemCount

                    if (!isLoading) {

                        if (visibleItemCount + pastVisibleItem >= total) {
                            currentPage++
                            getExercises(currentPage)
                        }
                    }
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })

    }

    override fun onStart() {
        mCompositeDisposable = CompositeDisposable()
        super.onStart()
    }

    override fun onStop() {
        mCompositeDisposable?.dispose()
        super.onStop()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentActions) {
            fragmentActions = context
        } else {
            throw RuntimeException(context.toString() + "implement FragmentActions")
        }
    }

    fun getExercises(page: Int) {
        isLoading = true
        progress_bar.visibility = View.VISIBLE
        mCompositeDisposable = CompositeDisposable()
        mCompositeDisposable?.add(ClientRequestAPI.getExercises(page)
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
                    setUpRecyclerView(mAdapter!!)
                    isLoading = false
                    progress_bar.visibility = View.GONE

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


