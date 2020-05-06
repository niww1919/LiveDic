package com.my.livedic

import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView

class WordsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(data: MutableList<String>,data2: MutableList<String>, pos: Int) {
        val list = mutableListOf<String>()
        val word1 = itemView.findViewById<AppCompatTextView>(R.id.tv_word1)
        val word2 = itemView.findViewById<AppCompatTextView>(R.id.tv_word2)
        val progressBar = itemView.findViewById<ProgressBar>(R.id.progress_bar)


        progressBar.max = 295
        progressBar.progress = pos

        word1.text =data[0].replaceFirst(
            data[0][0],
            data[0][0].toUpperCase())
        word2.text =data2[0].replaceFirst(
            data2[0][0],
            data2[0][0].toUpperCase())

        itemView.setOnClickListener {
            Log.d("Position.", "Position + $pos");
            Log.d("Position.", "Position + $data");

            if (word2.visibility == View.INVISIBLE) {
                word2.visibility = View.VISIBLE
            }
        }


    }


}