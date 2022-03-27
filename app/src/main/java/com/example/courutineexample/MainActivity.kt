package com.example.courutineexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private var btnAddMessage: FloatingActionButton? = null
    private var txtStatus:TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

    }


    private fun init() {
        btnAddMessage = findViewById(R.id.btnAddMessage)
        txtStatus = findViewById(R.id.txtStatus)


        btnAddMessage?.setOnClickListener {
            onStartClick(it)
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


}