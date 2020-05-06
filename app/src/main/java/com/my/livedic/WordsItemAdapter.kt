package com.my.livedic

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class WordsItemAdapter(val data: MutableList<String>,val data2: MutableList<String>,val resoursesLayout:Int,val pos: Int) : RecyclerView.Adapter<WordsViewHolder>() {
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
//        holder.itemView.setOnLongClickListener {
//
////            holder.itemView.findViewById<AppCompatTextView>(R.id.tv_word2).visibility = View.VISIBLE
//        }
        return holder.bind(data,data2, pos)

    }
}