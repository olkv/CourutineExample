package com.example.courutineexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.*
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var btnAddMessage: FloatingActionButton? = null
    private var btnSetCheck: FloatingActionButton? = null
    private var btnSendEmail: FloatingActionButton? = null
    private var btnPost: FloatingActionButton? = null

    private val scoupe = MainScope()

    private var jobSend:Job? = null

    private var txtStatus:TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

    }


    private fun init() {
        btnAddMessage = findViewById(R.id.btnAddMessage)
        btnSetCheck = findViewById(R.id.btnSetCheck)
        btnSendEmail = findViewById(R.id.btnSendEmail)
        btnPost = findViewById(R.id.btnPost)

        txtStatus = findViewById(R.id.txtStatus)

        btnAddMessage?.setOnClickListener {
            onStartClick(it)
        }

        btnSetCheck?.setOnClickListener {
            onSetCheckClick(it)
        }

        btnSendEmail?.setOnClickListener {
            onSendEmailClick(it)
        }

        btnPost?.setOnClickListener {
            onGetPostClick(it)
        }

    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun onStartClick(view:View) {
        Toast.makeText(view.context, "Click on Button Start",Toast.LENGTH_SHORT).show()
        txtStatus?.text = "10"

        GlobalScope.launch(Dispatchers.Main) {

            for (i in 10 downTo 1) {
                txtStatus?.text = i.toString()
                delay(500)
            }

            txtStatus?.text = "Done!"

        }

    }

    private fun onSetCheckClick(view: View) {
        CoroutineScope(Dispatchers.Main).launch {
            for (i in 1..10) {
                txtStatus?.text = i.toString()
                delay(500)
            }
            txtStatus?.text = "Done!"
        }

    }


    private fun onSendEmailClick(view: View) {
        var res:String=""

        txtStatus?.text = "Send EMail to olkv@wellit.pro"

        jobSend = CoroutineScope(Dispatchers.Main).launch {
            res = SendEmail("olkv@wellit.pro")
            delay(3000)
            txtStatus?.text = res
        }



    }

    private fun SendEmail(address: String):String {
        return "OK"
    }

    fun onGetPostClick(view: View) {

        readData()
        //запуск при блокировке основного потока
        /*
        runBlocking {
            launch {
                getPost()
            }
        }
        */
    }

    suspend fun getPost() {
        delay(5000)
        Log.w("MyLOG", "get Post request.")
    }


    //Паралеьное выполнение без блокировки
    fun readData() = scoupe.launch {
        showIOData()
    }

    suspend fun showIOData() {
        val data = withContext(Dispatchers.IO) {
            //расчет данных в фонофом процессе
            for (i in 1..10) {
                Log.w("MyLOG","Read $i")
                delay(1000)
            }
        }

    }

}