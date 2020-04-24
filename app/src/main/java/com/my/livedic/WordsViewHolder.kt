package com.my.livedic

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView

class WordsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(data: WordsItem) {

        if (data.word1[0].isLowerCase()) { //fixme
            data.word1[0].toUpperCase()
        }
        itemView.findViewById<AppCompatTextView>(R.id.tv_word1).text = data.word1
        itemView.findViewById<AppCompatTextView>(R.id.tv_word2).text = data.word2

        itemView.setOnClickListener {
            itemView.findViewById<AppCompatTextView>(R.id.tv_word2).visibility = View.VISIBLE
        }


    }


}