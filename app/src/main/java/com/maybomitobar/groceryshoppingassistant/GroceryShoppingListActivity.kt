package com.maybomitobar.groceryshoppingassistant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import com.maybomitobar.groceryshoppingassistant.Classes.User
import com.maybomitobar.groceryshoppingassistant.Classes.Product
import android.widget.ListView
import android.widget.TextView
import com.maybomitobar.groceryshoppingassistant.Adapters.GroceryShoppingListAdapter
import org.w3c.dom.Text

class GroceryShoppingListActivity : AppCompatActivity()
{
    private lateinit var user : User
    private lateinit var adapter : ArrayAdapter<Product>
    private lateinit var adapterElements : GroceryShoppingListAdapter
    private lateinit var GroceryShoppingListLV : ListView
    private var listOption : Boolean = true
    private lateinit var totalPriceTV : TextView

    companion object
    {
        const val REQUEST_REGISTER = 1
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grocery_shopping_list)

        user = intent.getParcelableExtra<User>("user")!!

        GroceryShoppingListLV = findViewById(R.id.listViewGroceryShopping)

        if(user.id == 1)
        {
            var productList = arrayListOf<Product>()

            productList.add(
                Product(
                    1,
                    "Fanta",
                    1000,
                    2,
                    "Bebida de 591 ml de la marca Coca Cola con sabor a naranja.",
                    "Bebestibles"
                )
            )

            productList.add(
                Product(
                    2,
                    "Yogurt de Frambuesa",
                    200,
                    4,
                    "Yogurt de Frambuesa de marca danone.",
                    "Lacteos"
                )
            )

            productList.add(
                Product(
                    3,
                    "Paquete de vienesas",
                    3300,
                    1,
                    "Paquete de 20 vienesas de cerdo marca la española.",
                    "Embutidos"
                )
            )

            var i = 0

            while (i < 3)
            {
                user.groceryList!!.addElementToProductToBuyList(productList[i])
                i++
            }
        }

        totalPriceTV = findViewById(R.id.textViewTotalPriceGSL)
        totalPriceTV.text = user.groceryList!!.totalPrice.toString()

        adapter = ArrayAdapter<Product>(this, android.R.layout.simple_list_item_1, user.groceryList!!.productsToBuy!!)
        GroceryShoppingListLV.adapter = adapter

        changeAdapter()

        val addProductB = findViewById<Button>(R.id.buttonAddProductGSL)

        addProductB.setOnClickListener()
        {
            val intent = Intent(this, AddProductToGSLActivity::class.java)
            intent.putExtra("gSL", user.groceryList)
            startActivityForResult(intent, REQUEST_REGISTER)
        }
    }

    private fun changeAdapter() {
        if (listOption) {
            adapterElements = GroceryShoppingListAdapter(
                this,
                R.layout.activity_product_in_gslactivity,
                user.groceryList!!.productsToBuy!!
            )
            GroceryShoppingListLV.adapter = adapterElements
        } else if (listOption == false) {
            adapter = ArrayAdapter<Product>(
                this,
                android.R.layout.simple_list_item_1,
                user.groceryList!!.productsToBuy!!
            )
            GroceryShoppingListLV.adapter = adapter
        }

        listOption = !listOption //Invierte el valor del boolean.
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == LogInActivity.REQUEST_REGISTER && resultCode == RESULT_OK)
        {
            val productToAdd = data?.getParcelableExtra<Product>("new")

            if(productToAdd != null)
            {
                user.groceryList!!.addElementToProductToBuyList(productToAdd)
                adapterElements.notifyDataSetChanged()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        totalPriceTV.text = user.groceryList!!.totalPrice.toString()
    }
}