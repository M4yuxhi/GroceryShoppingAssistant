package com.maybomitobar.groceryshoppingassistant.ParallelTasks

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.nio.Buffer

class ApiTask(private val callback: ApiCallback)
{
    fun execute(urlString : String)
    {
        GlobalScope.launch(Dispatchers.Main)
        {
            val result = withContext(Dispatchers.IO)
            {
                performApiRequest(urlString)
            }
            callback.onRequestComplete(result)
        }
    }

    private suspend fun performApiRequest(urlString: String) : String
    {
        try
        {
            val url = URL(urlString)
            val urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.requestMethod = "GET" //Se define el método a utilizar.

            return try
            {
                val responseCode = urlConnection.responseCode
                Log.i("ApiTask", "Response Code: $responseCode")

                val reader = BufferedReader(InputStreamReader(urlConnection.inputStream))
                val response = StringBuilder()
                var line : String?

                while(reader.readLine().also { line = it } != null)
                {
                    response.append(line)
                }

                response.toString()
            }
            finally
            {
                urlConnection.disconnect()
            }
        }
        catch(e : Exception)
        {
            e.printStackTrace()
            return ""
        }
    }
}