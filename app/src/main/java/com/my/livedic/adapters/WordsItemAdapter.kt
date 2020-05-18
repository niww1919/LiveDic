package com.my.livedic.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class WordsItemAdapter(val data: String,val data2: String,val resoursesLayout:Int,val pos: Int) : RecyclerView.Adapter<WordsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordsViewHolder {
        return WordsViewHolder(
            LayoutInflater.from(parent.context).inflate(resoursesLayout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: WordsViewHolder, position: Int) {

        //todo show word 2
        return holder.bind(data,data2, pos)

    }
}