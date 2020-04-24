package com.my.livedic

import android.media.audiofx.DynamicsProcessing
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Config
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.api.client.extensions.android.http.AndroidHttp
import com.google.api.client.http.HttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.sheets.v4.Sheets
import com.google.api.services.sheets.v4.model.Sheet
import com.google.api.services.sheets.v4.model.ValueRange
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    val KEY = "AIzaSyA6HVaf1oX3PDynG8p5y8FQhP-OqJQcgnM"
    val LINK =
        "https://docs.google.com/spreadsheets/d/1xEJ6tdsL758B-n1axU1vCxcfiOl8Aml1AiOzx_WWg28/edit#gid=86818389"

    private var sheetsList = mutableListOf(mutableListOf(WordsItem("", "")))
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

                if (direction == ItemTouchHelper.LEFT) {
                    loadSheets()


                    Toast.makeText(parent.baseContext, "${sheetsList.size}", Toast.LENGTH_SHORT)
                        .show()
                }
            }

        })
        itemTouchHelper.attachToRecyclerView(rv)


    }

    private fun loadSheets() {
        val transport = AndroidHttp.newCompatibleTransport()
        val factory = JacksonFactory.getDefaultInstance()
        val sheetsService = Sheets.Builder(transport, factory, null)
            .setApplicationName("LiveDic")
            .build()

        val spreadSheetsId = "1xEJ6tdsL758B-n1axU1vCxcfiOl8Aml1AiOzx_WWg28"

        Thread {
            Runnable{
                try {
                    val range = "Sheet1!C1:D50"
                    val result =
                        sheetsService.spreadsheets().values().get(spreadSheetsId, range)
                            .setKey(KEY)
                            .execute()

                    val numRows = result.getValues().size
                    Log.d("SUCCESS.", "rows retrived " + numRows);

//                    sheetsList = result.getValues() as MutableList<MutableList<WordsItem>>

                } catch (e: Exception) {
                    //                        Toast.makeText(this,"$e",Toast.LENGTH_SHORT).show()
                }

            }

        }.start()


    }
}
