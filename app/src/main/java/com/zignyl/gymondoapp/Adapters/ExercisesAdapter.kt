package com.zignyl.gymondoapp.Adapters

import android.content.ContentValues
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.zignyl.gymondoapp.Fragments.ExerciseFragment.Companion.TAG
import com.zignyl.gymondoapp.Models.Exercise
import com.zignyl.gymondoapp.R
import java.util.*

class ExercisesAdapter(
    private val exerciseList: MutableList<Exercise>, val onExerciseSelected: (Int) -> Unit
) : RecyclerView.Adapter<ExercisesAdapter.ViewHolder>() , Filterable {

    var searchableList: MutableList<Exercise> = exerciseList
    private var onNothingFound: (() -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_exercise_preview, parent, false)
        Log.d(ContentValues.TAG, "The value of the view is $v")
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return searchableList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val exercise: Exercise = searchableList[position]
        holder.bind(exercise)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textViewName = itemView.findViewById(R.id.tv_name_value) as TextView
        val textViewCategory = itemView.findViewById(R.id.tv_category_value) as TextView
        val textViewMuscles = itemView.findViewById(R.id.tv_muscles_value) as TextView
        val textEquipment = itemView.findViewById(R.id.tv_equipment_value) as TextView
        val cardViewExercise = itemView.findViewById(R.id.cv_exercise) as CardView

        fun bind(exercise: Exercise) {

            textViewName.text = exercise.name

            textViewCategory.text = exercise.category.toString()

            textEquipment.text = exercise.equipment.toString()


            textViewMuscles.text = exercise.muscles.toString()


            cardViewExercise.setOnClickListener {
                onExerciseSelected.invoke(exercise.id)
                Log.d(ContentValues.TAG, "Invoked the exercise id ")
            }
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            private val filterResults = FilterResults()
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                searchableList.clear()
                if (constraint.isNullOrBlank()) {
                    searchableList = exerciseList
                } else {
                    val filterPattern = constraint.toString().toLowerCase(Locale.ROOT)
                    for (item in 0..exerciseList.size) {
                        Log.d(TAG , "Size of the result list is ${exerciseList.size}")
                        if (exerciseList[item].name.toLowerCase(Locale.ROOT).contains(filterPattern)) {
                            searchableList.add(exerciseList[item])
                        }
                    }
                }
                return filterResults.also {
                    it.values = searchableList
                }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                if (searchableList.isNullOrEmpty())
                    onNothingFound?.invoke()
                notifyDataSetChanged()


            }
        }
    }
}