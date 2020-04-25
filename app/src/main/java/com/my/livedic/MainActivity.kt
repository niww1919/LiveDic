package com.my.livedic

import android.app.Activity
import android.media.audiofx.DynamicsProcessing
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
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

    val KEY = "-woZztYWrc"  //fixme
    val LINK =
        "https://docs.google.com/spreadsheets/d/1xEJ6tdsL758B-n1axU1vCxcfiOl8Aml1AiOzx_WWg28/edit#gid=86818389"

    private var sheetsList: MutableList<MutableList<WordsItem>> =
        mutableListOf(mutableListOf(WordsItem("1", "1")))
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

                if (direction == ItemTouchHelper.LEFT) {

                    (rv.adapter as WordsItemAdapter).notifyDataSetChanged()

                    Toast.makeText(
                        this@MainActivity,
                        "${sheetsList[sheetsList.size - 1]}",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }

        })
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            rv.layoutManager = linearLayoutManager
            rv.adapter = WordsItemAdapter(sheetsList)
            itemTouchHelper.attachToRecyclerView(rv)
        }, 2000)


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
                    val range = "Sheet1!C1:D50"
                    val result =
                        sheetsService.spreadsheets().values().get(spreadSheetsId, range)
                            .setKey(KEY)
                            .execute()

                    val numRows = result.getValues().size
                    Log.i("SUCCESSGOOD", "rows retrived " + numRows);

//                    int = numRows
                    sheetsList = result.getValues() as MutableList<MutableList<WordsItem>>

                } catch (e: Exception) {
                    Log.d("FALSE.", "rows retrived ");
//                                            Toast.makeText(this,"$e",Toast.LENGTH_SHORT).show()
                }

            }

        }.start()
    }

    private fun loadHandler() {
        val transport = AndroidHttp.newCompatibleTransport()
        val factory = JacksonFactory.getDefaultInstance()
        val sheetsService = Sheets.Builder(transport, factory, null)
            .setApplicationName("LiveDic")
            .build()

        val spreadSheetsId = "1xEJ6tdsL758B-n1axU1vCxcfiOl8Aml1AiOzx_WWg28"

        Handler(Looper.getMainLooper()).post {
            try {
                val range = "Sheet1!C1:D50"
                val result =
                    sheetsService.spreadsheets().values().get(spreadSheetsId, range)
                        .setKey(KEY)
                        .execute()

                val numRows = result.getValues().size
                Log.i("SUCCESSGOOD", "rows retrived " + numRows);

//                    int = numRows
                sheetsList = result.getValues() as MutableList<MutableList<WordsItem>>

            } catch (e: Exception) {
                Log.d("FALSE.", "rows retrived ");
//                                            Toast.makeText(this,"$e",Toast.LENGTH_SHORT).show()
            }

        }

    }


}

