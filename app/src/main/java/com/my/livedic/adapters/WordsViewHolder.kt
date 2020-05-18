package com.my.livedic.adapters

import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.my.livedic.R

class WordsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(data: String,data2: String, pos: Int) {

        val word1 = itemView.findViewById<AppCompatTextView>(R.id.tv_word1)
        val word2 = itemView.findViewById<AppCompatTextView>(R.id.tv_word2)
        val progressBar = itemView.findViewById<ProgressBar>(R.id.progress_bar)

        var max = 336
        if (pos >= max) max = pos

        progressBar.max = max
        progressBar.progress = max - pos

//            word1.text = data
//            word2.text = data2
        word1.text = data.replaceFirst(
                data[0],
                data[0].toUpperCase()
            )
            word2.text = data2.replaceFirst(
                data2[0],
                data2[0].toUpperCase()
            )


        itemView.setOnClickListener {
            Log.d("Position.", "Position + $pos");
            Log.d("Position.", "Position + $data");

            if (word2.visibility == View.INVISIBLE) {
                word2.visibility = View.VISIBLE
            }
        }


    }


}