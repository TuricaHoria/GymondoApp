package com.zignyl.gymondoapp.Adapters

import android.content.ContentValues
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.zignyl.gymondoapp.Models.Exercise
import com.zignyl.gymondoapp.R

class ExercisesAdapter(
    private val exerciseList: List<Exercise>, val onExerciseSelected : (Int) -> Unit
) : RecyclerView.Adapter<ExercisesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_exercise_preview, parent, false)
        Log.d(ContentValues.TAG, "The value of the view is $v")
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return exerciseList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val exercise: Exercise = exerciseList[position]
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


}