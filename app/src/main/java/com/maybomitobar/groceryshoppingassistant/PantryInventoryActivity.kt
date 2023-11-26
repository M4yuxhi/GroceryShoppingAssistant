package com.maybomitobar.groceryshoppingassistant

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.PopupMenu
import androidx.room.Room
import com.maybomitobar.groceryshoppingassistant.Adapters.ProductInventoryAdapter
import com.maybomitobar.groceryshoppingassistant.Classes.MovementInfo
import com.maybomitobar.groceryshoppingassistant.Classes.Product
import com.maybomitobar.groceryshoppingassistant.Databases.GSADatabase
import com.maybomitobar.groceryshoppingassistant.Helper.HelperFunctions

class PantryInventoryActivity : AppCompatActivity()
{
    private lateinit var adapter : ArrayAdapter<Product>
    private lateinit var productsLV : ListView
    private lateinit var adapterElements : ProductInventoryAdapter
    private lateinit var products : MutableList<Product>
    private lateinit var movementsInfo : MutableList<MovementInfo>
    private lateinit var db : GSADatabase
    private lateinit var sharedPref : SharedPreferences
    private lateinit var orderIndicator : String
    private lateinit var listener : SharedPreferences.OnSharedPreferenceChangeListener

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantry_inventory)
        setSupportActionBar(findViewById(R.id.toolbarPI))

        sharedPref = PreferenceManager.getDefaultSharedPreferences(this)
        val stringValue = sharedPref.getString("list_order_by", applicationContext.getString(R.string.order_by_add))
        orderIndicator = stringValue.toString()

        configureProductsLV()

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

        refreshDatabase()

        listener = SharedPreferences.OnSharedPreferenceChangeListener()
        {
            sharedPreferences, key ->

            if(key == "list_order_by")
            {
                val stringValue = sharedPref.getString("list_order_by", applicationContext.getString(R.string.order_by_add))
                orderIndicator = stringValue.toString()
                setOrderOnProducts()
            }
        }

        sharedPref.registerOnSharedPreferenceChangeListener(listener)
    }

    override fun onDestroy()
    {
        super.onDestroy()
        sharedPref.unregisterOnSharedPreferenceChangeListener(listener)

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
            R.id.searchProductItem ->
            {
                val intent = Intent(this, SearchProductActivity::class.java)
                startActivity(intent)
                return true
            }
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
            R.id.movementInfoItem ->
            {
                val intent = Intent(this, MovementsInfoActivity::class.java)
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

    private fun configureProductsLV()
    {
        productsLV = findViewById(R.id.listViewInventory)

        adapter = ArrayAdapter<Product>(this, android.R.layout.simple_list_item_1)
        productsLV.adapter = adapter

        productsLV.setOnItemLongClickListener()
        { _, view, position, _ ->

            val popup = PopupMenu(this@PantryInventoryActivity, view)
            val inflater : MenuInflater = popup.menuInflater
            inflater.inflate(R.menu.product_menu, popup.menu)

            popup.setOnMenuItemClickListener { menuItem ->

                when(menuItem.itemId)
                {
                    R.id.seeDetailsItem ->
                    {
                        // Lógica para ver detalles
                        goToSeeDetails(position)
                        true
                    }
                    R.id.deleteItem ->
                    {
                        // Lógica para eliminar

                        HelperFunctions.makeDialog(this@PantryInventoryActivity, applicationContext.getString(R.string.deleteDTitle),
                            applicationContext.getString(R.string.deleteDMessage), applicationContext.getString(R.string.yesDLabel),
                            applicationContext.getString(R.string.noDLabel), { deleteItem(position) }, { } )

                        true
                    }
                    else -> false
                }
            }

            popup.show()
            true
        }
    }

    private fun deleteItem(itemPosition : Int)
    {
        db.productDao().delete(products[itemPosition])

        val movementInfo = MovementInfo(movementsInfo.size + 1, "Delete", products[itemPosition].id)

        db.movementInfoDao().insertAll(movementInfo)
        refreshDatabase()
    }

    private fun goToSeeDetails(productIndex : Int)
    {
        val intent = Intent(this, SeeProductDetailsActivity::class.java)
        intent.putExtra("product", products[productIndex])
        startActivity(intent)
    }

    private fun refreshDatabase()
    {
        try
        {
            val listProducts = db.productDao().getAll()
            products = listProducts.toMutableList()

            setOrderOnProducts()

            val listMovementsInfo = db.movementInfoDao().getAll()
            movementsInfo = listMovementsInfo.toMutableList()
            setAdapter()
        }
        catch (e : Exception)
        {

        }
    }

    private fun setAdapter()
    {
        productsLV.invalidate()
        adapterElements = ProductInventoryAdapter(this, R.layout.activity_product_in_piactivity, products)
        productsLV.adapter = adapterElements
    }

    private fun setOrderOnProducts()
    {
        when(orderIndicator)
        {
            applicationContext.getString(R.string.order_by_add)->
            {
                products.sortBy { it.id }
            }
            applicationContext.getString(R.string.order_by_name)->
            {
                products.sortBy { it.name }
            }
            applicationContext.getString(R.string.order_by_price)->
            {
                products.sortBy { it.price }
            }
            applicationContext.getString(R.string.order_by_amount)->
            {
                products.sortBy { it.amount }
            }
            applicationContext.getString(R.string.order_by_description)->
            {
                products.sortBy { it.description }
            }
            applicationContext.getString(R.string.order_by_category)->
            {
                products.sortBy { it.category }
            }
        }
    }
}
