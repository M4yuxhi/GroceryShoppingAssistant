package com.maybomitobar.groceryshoppingassistant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.maybomitobar.groceryshoppingassistant.Adapters.ShoppingPlaceAdapter
import com.maybomitobar.groceryshoppingassistant.Classes.Shop
import com.maybomitobar.groceryshoppingassistant.Classes.User

class ShoppingPlacesActivity : AppCompatActivity()
{
    private lateinit var user : User
    private lateinit var adapter : ArrayAdapter<Shop>
    private lateinit var adapterElements : ShoppingPlaceAdapter
    private var listOption : Boolean = true
    private lateinit var shoppingPlacesList : ArrayList<Shop>
    private lateinit var ShoppingPlacesListLV : ListView

    companion object
    {
        const val REQUEST_REGISTER = 1
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_places)

        user = intent.getParcelableExtra("user")!!
        ShoppingPlacesListLV = findViewById(R.id.listViewShoppingPlaces)
        shoppingPlacesList = ArrayList()

        adapter = ArrayAdapter<Shop>(this, android.R.layout.simple_list_item_1)
        ShoppingPlacesListLV.adapter = adapter

        changeAdapter()

        val addShoppingPlaceB : Button = findViewById(R.id.buttonAddShoppingPlaceAS)

        addShoppingPlaceB.setOnClickListener()
        {
            val intent = Intent(this, AddShoppingPlacesActivity::class.java)
            intent.putExtra("sPL", shoppingPlacesList)
            startActivityForResult(intent, REQUEST_REGISTER)
        }
    }

    private fun changeAdapter()
    {
        if(listOption)
        {
            adapterElements = ShoppingPlaceAdapter(
                this,
                R.layout.activity_place_to_spactivity,
                shoppingPlacesList
            )

            ShoppingPlacesListLV.adapter = adapterElements
        }
        else if(listOption == false)
        {
            adapter = ArrayAdapter<Shop>(
                    this,
                android.R.layout.simple_list_item_1,
                shoppingPlacesList
            )

            ShoppingPlacesListLV.adapter = adapter
        }

        listOption = !listOption
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_REGISTER && resultCode == RESULT_OK)
        {
            val shopToAdd = data?.getParcelableExtra<Shop>("new")!!

            if(shopToAdd != null)
            {
                shoppingPlacesList.add(shopToAdd)
                adapterElements.notifyDataSetChanged()
            }
        }
    }
}