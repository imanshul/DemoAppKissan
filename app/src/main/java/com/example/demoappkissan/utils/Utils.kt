package com.example.demoappkissan.utils

import android.content.Context
import java.io.IOException
import java.nio.charset.Charset

object Utils{

    //Read JSON file From assets folder.
    fun getJsonFromAssets(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            val jsonFile = context.getAssets().open(fileName)

            val size = jsonFile.available()
            val buffer = ByteArray(size)
            jsonFile.read(buffer)
            jsonFile.close()

            jsonString = buffer.toString(Charset.defaultCharset())
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }

        return jsonString
    }
}