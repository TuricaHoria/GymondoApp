package com.zignyl.gymondoapp.Adapters

import android.content.ContentValues
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.zignyl.gymondoapp.Adapters.ImagesAdapter.ViewHolder
import com.zignyl.gymondoapp.Models.ExerciseImage
import com.zignyl.gymondoapp.R

class ImagesAdapter(private val images: MutableList<ExerciseImage>) :
    RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_exercise_iamge, parent, false)
        Log.d(ContentValues.TAG, "The value of the view is $v")

        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val image: ExerciseImage = images[position]
        holder.bind(image)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageView = itemView.findViewById(R.id.iv_exercise_image) as ImageView

        fun bind(image: ExerciseImage) {
            Picasso.with(itemView.context).load(image.image).into(imageView)
        }
    }


}