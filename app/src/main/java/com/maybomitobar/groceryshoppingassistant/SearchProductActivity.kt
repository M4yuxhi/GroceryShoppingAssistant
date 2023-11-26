package com.maybomitobar.groceryshoppingassistant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.room.Room
import com.maybomitobar.groceryshoppingassistant.Adapters.ProductInventoryAdapter
import com.maybomitobar.groceryshoppingassistant.Classes.Product
import com.maybomitobar.groceryshoppingassistant.Databases.GSADatabase
import com.maybomitobar.groceryshoppingassistant.Helper.HelperFunctions

class SearchProductActivity : AppCompatActivity()
{
    private var searchMode = 0
    private lateinit var searchET : EditText
    private lateinit var db : GSADatabase
    private lateinit var products : MutableList<Product>
    private lateinit var searchLV : ListView
    private lateinit var adapterElements : ProductInventoryAdapter

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_product)

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

        if(db.productDao().getAll() == null || db.productDao().getAll().isEmpty())
        {
            HelperFunctions.makeDialog(this@SearchProductActivity, "Error", applicationContext.getString(R.string.searchWhenProductsAreEmpty))
            finish()
        }

        searchET = findViewById(R.id.editTextSearch)
        searchLV = findViewById(R.id.listViewProductSearch)

        setOnClickListeners()
    }

    override fun onCreateOptionsMenu(menu : Menu?) : Boolean
    {
        menuInflater.inflate(R.menu.product_search_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        when(item.itemId)
        {
            R.id.searchById ->
            {
                searchMode = 0
                return true
            }
            R.id.searchByNameItem ->
            {
                searchMode = 1
                return true
            }
            R.id.searchByPrice ->
            {
                searchMode = 2
                return true
            }
            R.id.searchByAmount ->
            {
                searchMode = 3
                return true
            }
            R.id.searchByDescription ->
            {
                searchMode = 4
                return true
            }
            R.id.searchByCategory ->
            {
                searchMode = 5
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun setAdapter()
    {
        searchLV.invalidate()
        adapterElements = ProductInventoryAdapter(this, R.layout.activity_product_in_piactivity, products)
        searchLV.adapter = adapterElements
    }

    private fun setOnClickListeners()
    {
        val searchB = findViewById<Button>(R.id.buttonSearchSPA)

        searchB.setOnClickListener()
        {
            if(searchET.text.toString() != "")
            {
                val search = searchET.text.toString()

                when(searchMode)
                {
                    0 ->
                    {
                        val product = db.productDao().findById(search.toInt())
                        products = arrayListOf()
                        products.add(product!!)
                    }
                    1 ->
                    {
                        val product = db.productDao().findByName(search)
                        products = arrayListOf()
                        products.add(product!!)
                    }
                    2 ->
                    {
                        val list = db.productDao().findByPrice(search.toInt())
                        products = list.toMutableList()
                    }
                    3 ->
                    {
                        val list = db.productDao().findByAmount(search.toInt())
                        products = list.toMutableList()
                    }
                    4 ->
                    {
                        val list = db.productDao().findByDescription(search)
                        products = list.toMutableList()
                    }
                    5 ->
                    {
                        val list = db.productDao().findByCategory(search)
                        products = list.toMutableList()
                    }
                }

                setAdapter()
            }
        }
    }
}