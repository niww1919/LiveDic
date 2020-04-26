package com.my.livedic

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView

class WordsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(data: MutableList<WordsItem>, pos:Int) {

        itemView.findViewById<AppCompatTextView>(R.id.tv_word1).text = data.toString().substringBefore(",").substringAfter("[")
        itemView.findViewById<AppCompatTextView>(R.id.tv_word2).text = data.toString().substringAfter(',').substringBefore("]")

        itemView.setOnClickListener {
//        itemView.findViewById<AppCompatTextView>(R.id.tv_word2).text = data.toString()
            itemView.findViewById<AppCompatTextView>(R.id.tv_word1).visibility = View.VISIBLE
        }


    }


}