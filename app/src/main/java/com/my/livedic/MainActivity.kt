package com.my.livedic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val wordsList: MutableList<WordsItem> =
        mutableListOf(
            WordsItem("Кто", "O que"),
            WordsItem("Что", "Quem"),
            WordsItem("Где", "Onde"),
            WordsItem("Когда", "Quando"),
            WordsItem("Сколько", "Quanto"),
            WordsItem("Как", "Como"),
            WordsItem("Почему", "Porque"),
            WordsItem("какой", "qual"),
            WordsItem("Я являюсь", "Eu sou"),
            WordsItem("Я люблю", "Eu amo"),
            WordsItem("Доброе утро", "Bom dia"),
            WordsItem("добрый день", "boa tarde"),
            WordsItem("спокойной ночи", "boa noite"),
            WordsItem("До скорой встречи", "Ate logo"),
            WordsItem("до свидания", "tchau"),
            WordsItem("любить", "amar"),
            WordsItem("говорить", "Falar"),
            WordsItem("работа", "Trabalhar"),
            WordsItem("Купить", "Comprar"),
            WordsItem("спросить", "perguntar"),
            WordsItem("слушать", "escutar"),
            WordsItem("выглядит", "olha"),
            WordsItem("взгляд", "olhar"),
            WordsItem("пожалуйста", "Por favor"),
            WordsItem("спасибо", "Obrigado"),
            WordsItem("Пожалуйста", "De nada"),
            WordsItem("есть", "Comer"),
            WordsItem("пить", "Beber"),
            WordsItem("Как дела?", "Como vai?"),
            WordsItem("очень хорошо", "muito bem"),
            WordsItem("исследование", "Estudar"),
            WordsItem("помощь", "Ajudar"),
            WordsItem("Что?", "O que?"),
            WordsItem("кто", "Quem"),
            WordsItem("где", "Onde"),
            WordsItem("сколько", "quanto"),
            WordsItem("когда", "quando"),
            WordsItem("стоимость", "Custar"),
            WordsItem("почему", "Por que"),
            WordsItem("быть", "Ser"),
            WordsItem("любить", "gostar"),
            WordsItem("Мне нравится", "Eu gostar de"),
            WordsItem("делать", "fazer"),
            WordsItem("вчера", "Ontem"),
            WordsItem("сегодня", "Hoje"),
            WordsItem("завтра", "Amanha"),
            WordsItem("в настоящее время", "Agora"),
            WordsItem("время", "tempo"),
            WordsItem("Где?", "A onde?"),
            WordsItem("Откуда", "De onde")
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
