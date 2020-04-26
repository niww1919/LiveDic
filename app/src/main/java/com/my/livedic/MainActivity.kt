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
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.Fragment
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
    var resource1 = R.layout.item_word
    var resource2 = R.layout.item_word2
    var resource = R.layout.item_word
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
        val linearLayoutManager = LinearLayoutManager(this)

        findViewById<AppCompatTextView>(R.id.tv_fragment_word1).setOnClickListener {
            (it as AppCompatTextView).text = sheetsList1[size][0]
            (findViewById<AppCompatTextView>(R.id.tv_fragment_word2) as AppCompatTextView).text = sheetsList2[size][0]
            (findViewById<AppCompatTextView>(R.id.tv_fragment_word2) as AppCompatTextView).visibility = View.VISIBLE
            size--

        }

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

                    Toast.makeText(
                        this@MainActivity,
                        "${resource1}",
                        Toast.LENGTH_SHORT
                    )
                        .show()

                }
                if (direction == ItemTouchHelper.LEFT) {

                    Handler(Looper.getMainLooper()).post(Runnable {
                        res = resource2
                        rv.adapter = WordsItemAdapter(sheetsList1, sheetsList2, resource2)
                        (rv.adapter as WordsItemAdapter).notifyDataSetChanged()
                    })
                    if (res == resource2) {
                        Handler(Looper.getMainLooper()).post(Runnable {
                            res = resource1
                            rv.adapter = WordsItemAdapter(sheetsList1, sheetsList2, resource1)
                            (rv.adapter as WordsItemAdapter).notifyDataSetChanged()
                        })

                    }


                    Toast.makeText(
                        this@MainActivity,
                        "${resource2}",
                        Toast.LENGTH_SHORT
                    )
                        .show()

                }
            }

        })
        Handler(Looper.getMainLooper()).postDelayed(Runnable {


            rv.layoutManager = linearLayoutManager
            rv.adapter = WordsItemAdapter(sheetsList1, sheetsList2, resource)
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
                    val range = "Sheet1!C1:D139"//fixme change table range
                    val range1 = "Sheet1!C1:C139"//fixme change table range
                    val range2 = "Sheet1!D1:D139"//fixme change table range
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
                    size = sheetsList1.size -1

                } catch (e: Exception) {
                    Log.d("FALSE.", "rows retrived ");
//                                            Toast.makeText(this,"$e",Toast.LENGTH_SHORT).show()
                }

            }

        }.start()
    }


}

private operator fun Int.iterator(): Iterator<Int> {
    TODO("Not yet implemented")
}

