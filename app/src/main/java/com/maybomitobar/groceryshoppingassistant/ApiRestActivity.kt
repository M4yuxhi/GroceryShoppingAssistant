package com.maybomitobar.groceryshoppingassistant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import com.maybomitobar.groceryshoppingassistant.ParallelTasks.ApiCallback
import com.maybomitobar.groceryshoppingassistant.ParallelTasks.ApiTask
import org.json.JSONException
import org.json.JSONObject

class ApiRestActivity : AppCompatActivity(), ApiCallback
{
    private lateinit var listDataJson : ListView
    private lateinit var adapter : ArrayAdapter<String>
    private var URL : String = "https://reqres.in/api/users"
    private lateinit var listData : MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_api_rest)

        listDataJson = findViewById(R.id.apiElementsLV)

        listData = mutableListOf(
            getString(R.string.api_instruction)
        )

        adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            listData
        )

        listDataJson.adapter = adapter

        setOnClickListeners()
    }

    private fun setOnClickListeners()
    {
        var getButton : Button = findViewById(R.id.buttonGetRequest)

        getButton.setOnClickListener()
        {
            Log.i("a", "a")
            val apiRequestTask = ApiTask(this)
            apiRequestTask.execute(URL)
        }
    }

    override fun onRequestComplete(result: String)
    {
        listData = processingData(result)
        adapter.clear()
        adapter.addAll(listData)
        adapter.notifyDataSetChanged()
    }

    private fun processingData(result : String) : MutableList<String>
    {
        var list : MutableList<String> = mutableListOf()

        try
        {
            val jsonObject = JSONObject(result)

            val dataArray = jsonObject.getJSONArray("data")

            for(i in 0 until dataArray.length())
            {
                val dataObject = dataArray.getJSONObject(i)

                val firstName = dataObject.getString("first_name")
                Log.i("ApiRestActivity", firstName.toString())
                list.add(firstName)
            }
        }
        catch (e : JSONException)
        {
            e.printStackTrace()
            list.add(getString(R.string.apiError))
        }
        return list.toMutableList()
    }
}