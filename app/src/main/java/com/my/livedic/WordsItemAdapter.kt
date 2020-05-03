package com.my.livedic

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import java.util.zip.Inflater

class WordsItemAdapter(
    val data: MutableList<MutableList<String>>,
    val data2: MutableList<MutableList<String>>,
    val resourse: Int
) : RecyclerView.Adapter<WordsViewHolder>() {
    val listWords = mutableListOf<MutableList<String>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordsViewHolder {
        return WordsViewHolder(
            LayoutInflater.from(parent.context).inflate(resourse, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: WordsViewHolder, position: Int) {
        listWords.addAll(data)
        holder.itemView.setOnClickListener {
            if (listWords[position].size <= 1) {

                listWords[position].add(1, data2[position][0])
            }
            Log.d("listWords", "${listWords[position]}");
            Log.d("listWords", "${listWords[position].size}");
            Log.d("listWords", "$listWords");
        }

        return holder.bind(listWords[position])


    }
}