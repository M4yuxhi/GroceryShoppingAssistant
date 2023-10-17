package com.maybomitobar.groceryshoppingassistant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import com.maybomitobar.groceryshoppingassistant.Classes.PantryInventory
import android.widget.ListView
import com.maybomitobar.groceryshoppingassistant.Adapters.ProductInventoryAdapter
import com.maybomitobar.groceryshoppingassistant.Classes.Product
import com.maybomitobar.groceryshoppingassistant.Classes.User

class PantryInventoryActivity : AppCompatActivity()
{
    private lateinit var user : User
    private lateinit var adapter : ArrayAdapter<Product>
    private var listOption : Boolean = true
    private lateinit var InventoryLV : ListView
    private lateinit var adapterElements : ProductInventoryAdapter

    companion object
    {
        const val REQUEST_REGISTER = 1
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantry_inventory)

        user = intent.getParcelableExtra("user")!!

        InventoryLV = findViewById(R.id.listViewInventory)

        adapter = ArrayAdapter<Product>(this, android.R.layout.simple_list_item_1)
        InventoryLV.adapter = adapter

        changeAdapter()

        val AddProductButton = findViewById<Button>(R.id.buttonAddProductPI)

        AddProductButton.setOnClickListener()
        {
            val intent = Intent(this, AddProductToPIActivity::class.java)
            intent.putExtra("pI", user.ownPantry!!)
            startActivityForResult(intent, REQUEST_REGISTER)
        }
    }

    private fun changeAdapter()
    {
        if(listOption)
        {
            adapterElements = ProductInventoryAdapter(this, R.layout.activity_product_in_piactivity, user.ownPantry!!.productsList!!)
            InventoryLV.adapter = adapterElements
        }
        else if(!listOption)
        {
            adapter = ArrayAdapter<Product>(this, android.R.layout.simple_list_item_1)
            InventoryLV.adapter = adapter
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == LogInActivity.REQUEST_REGISTER && resultCode == RESULT_OK)
        {
            val productToAdd = data?.getParcelableExtra<Product>("new")

            if(productToAdd != null)
            {
                user.ownPantry!!.addElementToProductList(productToAdd)
                adapterElements.notifyDataSetChanged()
            }
        }

    }
}