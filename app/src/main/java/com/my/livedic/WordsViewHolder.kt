package com.my.livedic

import android.util.Log
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView

class WordsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(data: MutableList<String>) {

        val word1 = itemView.findViewById<AppCompatTextView>(R.id.tv_word1)
        val word2 = itemView.findViewById<AppCompatTextView>(R.id.tv_word2)

        word1.text = data[0]
        if (data.size > 1) {
            Log.d("Size", "Position + ${data.size}");

            word2.text = data[1]
        }

//        itemView.setOnClickListener {
//            Log.d("Position.", "Position + $data");
//
////            if (word2.visibility == View.INVISIBLE) {
////                word2.visibility = View.VISIBLE
////            }
//        }


    }


}