package com.my.livedic.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.api.client.extensions.android.http.AndroidHttp
import com.google.api.client.json.jackson2.JacksonFactory
//import com.google.api.services.sheets.v4.Sheets
import com.my.livedic.R
import com.my.livedic.adapters.WordsItemAdapter
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    val KEY = "-woZztYWrc"  //fixme
    val LINK =
        "https://docs.google.com/spreadsheets/d/1xEJ6tdsL758B-n1axU1vCxcfiOl8Aml1AiOzx_WWg28/edit#gid=86818389"
    var layoutRes = R.layout.item_word
    var res: Int = 0
    var wordPosition = 0
    val APP_COUNTS_WORDS = "countswords"


    private val sheetsList: MutableList<MutableList<String>> =
        mutableListOf(mutableListOf("0", "1", "2", "3"))


    override fun onCreate(savedInstanceState: Bundle?) {

        val mSettings = getSharedPreferences(APP_COUNTS_WORDS, Context.MODE_PRIVATE)
        val editor = mSettings.edit()

//        loadSheets(mSettings.getInt(APP_COUNTS_WORDS, 0))

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)


        val rv = findViewById<RecyclerView>(R.id.rv_words)
//        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val linearLayoutManager = LinearLayoutManager(this)


        val chip1 = findViewById<Chip>(R.id.chip1)
        val chip2 = findViewById<Chip>(R.id.chip2)
        val chip3 = findViewById<Chip>(R.id.chip3)
        val chip4 = findViewById<Chip>(R.id.chip4)


        /***
         *
         */

        val itemTouchHelper = ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            /***
             * swipe item
             * */
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                if (direction == ItemTouchHelper.RIGHT) {

                    if (chip2.isChecked) {
                        addAdapter(rv, sheetsList[wordPosition][3], sheetsList[wordPosition][2])
                    } else {
                        addAdapter(rv, sheetsList[wordPosition][2], sheetsList[wordPosition][3])
                    }

                    Log.d("wordPosition.", wordPosition.toString() + sheetsList[wordPosition][3])
                    if (wordPosition < sheetsList.size -1) {
                        wordPosition++

                    }

                }
                if (direction == ItemTouchHelper.LEFT) {

                    if (chip3.isChecked) {
                        while (sheetsList[wordPosition][2].contains(
                                " ",
                                false
                            ) && sheetsList[wordPosition][3].contains(" ", false)
                        ) {
                            wordPosition--
                        }
                    }
                    if (chip4.isChecked) {
                        while (" ".count {
                                sheetsList[wordPosition][2].contains(it)
                            } >= 2 &&
                            " ".count {
                                sheetsList[wordPosition][3].contains(it)
                            } >= 2) {

                            wordPosition--
                        }
                    }

                    if (chip2.isChecked) {
                        addAdapter(rv, sheetsList[wordPosition][3], sheetsList[wordPosition][2])
                    } else {
                        addAdapter(rv, sheetsList[wordPosition][2], sheetsList[wordPosition][3])
                    }
                    Log.d(
                        "wordPosition.",
                        wordPosition.toString() + sheetsList[wordPosition][3]
                    )
                    Log.d("countprobel.", sheetsList[wordPosition][3].contains(
                        " ",
                        true
                    ).toString())
                    Log.d("countprobel.", " ".count {
                        sheetsList[wordPosition][3].contains(it)
                    }.toString())
                    Log.d("countprobel.", " ".filter {
                        sheetsList[wordPosition][3].contains(it)
                    }.length.toString())
                    if (wordPosition > 1) {
                        wordPosition--
                    }

                }
                editor.putInt(APP_COUNTS_WORDS, wordPosition)
                editor.apply()
            }


        })

        itemTouchHelper.attachToRecyclerView(rv)

        chip1.setOnClickListener {
            chip2.isChecked = false
            Handler(Looper.getMainLooper()).post(Runnable {
                rv.layoutManager = linearLayoutManager
                addAdapter(rv, sheetsList[wordPosition][2], sheetsList[wordPosition][3])
            })
        }
        chip2.setOnClickListener {
            chip1.isChecked = false

            Handler(Looper.getMainLooper()).post(Runnable {
                rv.layoutManager = linearLayoutManager
                addAdapter(rv, sheetsList[wordPosition][3], sheetsList[wordPosition][2])
            })
        }
    }

    private fun addAdapter(rv: RecyclerView, list: String, list2: String) {
        rv.adapter =
            WordsItemAdapter(
                list,
                list2,
                layoutRes,
                wordPosition
            )
        (rv.adapter as WordsItemAdapter).notifyDataSetChanged()
    }

//    private fun loadSheets(pos: Int) {
//        val transport = AndroidHttp.newCompatibleTransport()
//        val factory = JacksonFactory.getDefaultInstance()
//        val sheetsService = Sheets.Builder(transport, factory, null)
//            .setApplicationName("LiveDic")
//            .build()
//
//        val spreadSheetsId = "1xEJ6tdsL758B-n1axU1vCxcfiOl8Aml1AiOzx_WWg28"
//
//        Thread {
//            run {
//                try {
//                    val range = "Sheet1!A1:D336"//fixme change table range
//                    val result =
//                        sheetsService.spreadsheets().values().get(spreadSheetsId, range)
//                            .setKey(KEY)
//                            .execute()
//
//                    val numRows = result.getValues().size
//                    Log.i("SUCCESSGOOD", "rows retrived " + numRows);
//
////                    int = numRows
//                    sheetsList.addAll(result?.getValues() as MutableList<MutableList<String>>)
//                    Log.d("SheetString", sheetsList[0].toString());
//                    Log.d("SheetString", sheetsList[1].size.toString());
//                    Log.d("SheetString", sheetsList[1][0]);
//                    Log.d("SheetString", sheetsList[1][1]);
//                    Log.d("SheetString", sheetsList[1][2]);
//                    Log.d("SheetString", sheetsList[1][3]);
//
//                    wordPosition = pos
//
//                } catch (e: Exception) {
//                    Log.d("FALSE.", "rows retrived ");
//                }
//
//            }
//
//        }.start()
//    }


}


