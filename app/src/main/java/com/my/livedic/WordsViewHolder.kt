package com.my.livedic

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView

class WordsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(data: MutableList<WordsItem>, pos:Int) {

        itemView.findViewById<AppCompatTextView>(R.id.tv_word1).text = data.toString().substringBefore(",")
        itemView.findViewById<AppCompatTextView>(R.id.tv_word2).text = data.toString().substringAfter(',')

        itemView.setOnClickListener {
            itemView.findViewById<AppCompatTextView>(R.id.tv_word2).visibility = View.VISIBLE
        }


    }


}