package com.example.android_gettinggoogleservertime_example

import java.net.URL
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*
import javax.net.ssl.HttpsURLConnection

object Utils {

    @Throws(Exception::class)
    fun getServerDateTime(): LocalDateTime {
        val gmtStr: String = (URL("https://www.google.com").openConnection() as HttpsURLConnection).getHeaderField("Date")

        val simpleDateFormat = SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.ENGLISH).apply {
            timeZone = TimeZone.getTimeZone("GMT")
        }

        return simpleDateFormat.parse(gmtStr)!!.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
    }

}