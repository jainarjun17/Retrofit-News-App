package com.example.cheezyretrofitnews

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MyAdapter(val context: Context, val newsList: List<Article>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_view, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text = newsList[position].title
        holder.description.text = newsList[position].description
        Glide.with(context).load(newsList[position].urlToImage).into(holder.image)

        holder.itemView.setOnClickListener{
            Toast.makeText(context,newsList[position].title,Toast.LENGTH_LONG).show()
        }
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById<TextView>(R.id.titleText)
        var description: TextView = itemView.findViewById<TextView>(R.id.descriptionText)
        var image = itemView.findViewById<ImageView>(R.id.newsImage)
    }
}