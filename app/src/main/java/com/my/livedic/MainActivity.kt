package com.my.livedic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.api.client.extensions.android.http.AndroidHttp
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.sheets.v4.Sheets
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    val KEY = "-woZztYWrc"  //fixme
    val LINK =
        "https://docs.google.com/spreadsheets/d/1xEJ6tdsL758B-n1axU1vCxcfiOl8Aml1AiOzx_WWg28/edit#gid=86818389"
    var layoutRes = R.layout.item_word
    var res: Int = 0
    var size = 0


    private var sheetsList: MutableList<MutableList<WordsItem>> =
        mutableListOf(mutableListOf(WordsItem("1", "1")))
    private var sheetsList1: MutableList<MutableList<String>> =
        mutableListOf(mutableListOf(""))
    private var sheetsList2: MutableList<MutableList<String>> =
        mutableListOf(mutableListOf(""))
    private val wordsList: MutableList<WordsItem> =
        mutableListOf(
            WordsItem("Кто", "O que"),
            WordsItem("Что", "Quem"),
            WordsItem("Где", "Onde")
        )


    override fun onCreate(savedInstanceState: Bundle?) {
        loadSheets()
//        loadHandler()

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)


        val rv = findViewById<RecyclerView>(R.id.rv_words)
//        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val linearLayoutManager = LinearLayoutManager(this)


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

                if (direction == ItemTouchHelper.RIGHT) {
                    (rv.adapter as WordsItemAdapter).notifyDataSetChanged()

                }
                if (direction == ItemTouchHelper.LEFT) {
                    (rv.adapter as WordsItemAdapter).notifyDataSetChanged()
                }
            }

        })


        findViewById<Chip>(R.id.chip1).setOnClickListener {
            findViewById<Chip>(R.id.chip2).isChecked = false
            Handler(Looper.getMainLooper()).post(Runnable {
                rv.layoutManager = linearLayoutManager
                rv.adapter = WordsItemAdapter(sheetsList1, sheetsList2, layoutRes)
                itemTouchHelper.attachToRecyclerView(rv)
                (rv.adapter as WordsItemAdapter).notifyDataSetChanged()
            })
        }
        findViewById<Chip>(R.id.chip2).setOnClickListener {
            findViewById<Chip>(R.id.chip1).isChecked = false

            Handler(Looper.getMainLooper()).post(Runnable {
                rv.layoutManager = linearLayoutManager
                rv.adapter = WordsItemAdapter(sheetsList2, sheetsList1, layoutRes)
                rv.smoothScrollToPosition(sheetsList1.size-1)

                itemTouchHelper.attachToRecyclerView(rv)
                (rv.adapter as WordsItemAdapter).notifyDataSetChanged()
            })
        }
        findViewById<Chip>(R.id.chip3).setOnClickListener {
            Handler(Looper.getMainLooper()).post(Runnable {
                val llm =LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                rv.layoutManager = llm

                (rv.adapter as WordsItemAdapter).notifyDataSetChanged()
            })
        }

    }

    private fun loadSheets() {
        val transport = AndroidHttp.newCompatibleTransport()
        val factory = JacksonFactory.getDefaultInstance()
        val sheetsService = Sheets.Builder(transport, factory, null)
            .setApplicationName("LiveDic")
            .build()

        val spreadSheetsId = "1xEJ6tdsL758B-n1axU1vCxcfiOl8Aml1AiOzx_WWg28"


        Thread {
            run {
                try {
                    val range = "Sheet1!C1:D182"//fixme change table range
                    val range1 = "Sheet1!C1:C182"//fixme change table range
                    val range2 = "Sheet1!D1:D182"//fixme change table range
                    val result =
                        sheetsService.spreadsheets().values().get(spreadSheetsId, range)
                            .setKey(KEY)
                            .execute()
                    val result1 =
                        sheetsService.spreadsheets().values().get(spreadSheetsId, range1)
                            .setKey(KEY)
                            .execute()
                    val result2 =
                        sheetsService.spreadsheets().values().get(spreadSheetsId, range2)
                            .setKey(KEY)
                            .execute()

                    val numRows = result.getValues().size
                    Log.i("SUCCESSGOOD", "rows retrived " + numRows);

//                    int = numRows
                    sheetsList = result.getValues() as MutableList<MutableList<WordsItem>>
                    sheetsList1 = result1.getValues() as MutableList<MutableList<String>>
                    sheetsList2 = result2.getValues() as MutableList<MutableList<String>>
                    size = sheetsList1.size - 1

                } catch (e: Exception) {
                    Log.d("FALSE.", "rows retrived ");
                }

            }

        }.start()
    }


}

private operator fun Int.iterator(): Iterator<Int> {
    TODO("Not yet implemented")
}

