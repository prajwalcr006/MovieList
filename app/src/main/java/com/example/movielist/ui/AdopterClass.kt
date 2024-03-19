package com.example.movielist.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movielist.R
import com.example.movielist.domain.model.MovieList

class AdopterClass(private var dataList: List<MovieList>): RecyclerView.Adapter<AdopterClass.ViewHolderClass>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView =LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent)
        return ViewHolderClass(itemView)
    }

    override fun getItemCount(): Int {
       return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val currentItem = dataList[position]
        holder.rvTitle.text = currentItem.title
        holder.rvLanguage.text = currentItem.originalLanguage
    }

    fun update(list: List<MovieList>) {
        dataList = list
        notifyDataSetChanged()
    }

    class ViewHolderClass(private val itemView: View): RecyclerView.ViewHolder(itemView) {
        val rvTitle: TextView = itemView.findViewById(R.id.title)
        val rvLanguage: TextView = itemView.findViewById(R.id.original_language)
    }

}