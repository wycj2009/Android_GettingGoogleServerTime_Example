package com.example.android_gettinggoogleservertime_example

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainTextviewGoogleServerTime: TextView
    private lateinit var activityMainButtonGetGoogleServerTime: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activityMainTextviewGoogleServerTime = findViewById(R.id.activity_main_textview_google_server_time)
        activityMainButtonGetGoogleServerTime = findViewById(R.id.activity_main_button_get_google_server_time)

        activityMainButtonGetGoogleServerTime.setOnClickListener {
            getGoogleServerTime()
        }
    }

    private fun getGoogleServerTime() {
        CoroutineScope(Dispatchers.Main).launch {
            activityMainTextviewGoogleServerTime.text = "loading..."
            
            try {
                var serverDateTime: LocalDateTime? = null

                CoroutineScope(Dispatchers.IO).async {
                    serverDateTime = Utils.getServerDateTime()
                }.await()

                activityMainTextviewGoogleServerTime.text = serverDateTime.toString()
            } catch (e: Exception) {
                e.printStackTrace()

                activityMainTextviewGoogleServerTime.text = "통신 실패"
            }
        }
    }

}