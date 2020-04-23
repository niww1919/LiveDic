package com.my.livedic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    val wordsList: MutableList<WordsItem> =
        mutableListOf(
            WordsItem("Кто", "O que"),
            WordsItem("Что", "Quem"),
            WordsItem("Где", "Onde"),
            WordsItem("Когда", "Quando"),
            WordsItem("Сколько", "Quanto"),
            WordsItem("Как", "Como"),
            WordsItem("Почему", "Porque"),
            WordsItem("какой", "qual"),
            WordsItem("Я являюсь", "Eu sou")
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rv = findViewById<RecyclerView>(R.id.rv_words)
        val linearLayoutManager = LinearLayoutManager(this)
        rv.layoutManager = linearLayoutManager
        rv.adapter = WordsItemAdapter(wordsList)



        val itemTouchHelper = ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    (rv.adapter as WordsItemAdapter).notifyDataSetChanged()
//                if (direction == ItemTouchHelper.LEFT) {
//                    viewHolder.itemView.findViewById<AppCompatTextView>(R.id.tv_word2).visibility =
//                        View.VISIBLE
//                }
            }

        })
        itemTouchHelper.attachToRecyclerView(rv)


    }
}
