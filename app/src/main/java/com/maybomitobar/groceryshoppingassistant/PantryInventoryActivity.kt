package com.maybomitobar.groceryshoppingassistant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.room.Room
import com.maybomitobar.groceryshoppingassistant.Adapters.ProductInventoryAdapter
import com.maybomitobar.groceryshoppingassistant.Classes.Product
import com.maybomitobar.groceryshoppingassistant.Classes.ProductAction
import com.maybomitobar.groceryshoppingassistant.Databases.GSADatabase
import com.maybomitobar.groceryshoppingassistant.Helper.HelperFunctions

class PantryInventoryActivity : AppCompatActivity()
{
    private lateinit var adapter : ArrayAdapter<Product>
    private lateinit var InventoryLV : ListView
    private lateinit var adapterElements : ProductInventoryAdapter
    private lateinit var products : MutableList<Product>
    private lateinit var db : GSADatabase

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantry_inventory)
        setSupportActionBar(findViewById(R.id.toolbarPI))

        InventoryLV = findViewById(R.id.listViewInventory)

        adapter = ArrayAdapter<Product>(this, android.R.layout.simple_list_item_1)
        InventoryLV.adapter = adapter

        try
        {
            db = Room.databaseBuilder(
                applicationContext,
                GSADatabase::class.java, "gsa_database"
            ).allowMainThreadQueries().build()
        }
        catch (e: Exception)
        {
            e.printStackTrace()
            // Puedes registrar la excepción o mostrar un mensaje de error
        }

        //refreshDatabase()
    }

    override fun onCreateOptionsMenu(menu : Menu?) : Boolean
    {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        when(item.itemId)
        {
            R.id.preferencesItem ->
            {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.addProductItem ->
            {
                val intent = Intent(this, AddProductToPIActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onResume()
    {
        super.onResume()

        refreshDatabase()
    }

    private fun addProduct(product : Product)
    {
        db.productDao().insertAll(product)
        refreshDatabase()
    }

    private fun refreshDatabase()
    {
        try
        {
            val list = db.productDao().getAll()
            products = list.toMutableList()
            setAdapter()
        }
        catch (e : Exception)
        {
            HelperFunctions.makeDialog(applicationContext, "Exception", e.toString())
        }
    }

    private fun setAdapter()
    {
        InventoryLV.invalidate()
        adapterElements = ProductInventoryAdapter(this, R.layout.activity_product_in_piactivity, products)
        InventoryLV.adapter = adapterElements
    }
}
