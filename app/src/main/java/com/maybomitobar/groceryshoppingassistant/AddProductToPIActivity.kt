package com.maybomitobar.groceryshoppingassistant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.room.Room
import com.maybomitobar.groceryshoppingassistant.Classes.Product
import com.maybomitobar.groceryshoppingassistant.Classes.ProductAction
import com.maybomitobar.groceryshoppingassistant.Databases.GSADatabase
import com.maybomitobar.groceryshoppingassistant.Helper.HelperFunctions

class AddProductToPIActivity : AppCompatActivity()
{
    private lateinit var products : MutableList<Product>
    private lateinit var nameTE : EditText
    private lateinit var priceTE : EditText
    private lateinit var categoryTE : EditText
    private lateinit var amountTE : EditText
    private lateinit var descriptionTE : EditText

    private lateinit var db : GSADatabase

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product_to_piactivity)

        nameTE = findViewById(R.id.editTextNameAPTPI)
        priceTE = findViewById(R.id.editTextPriceAPTPI)
        categoryTE = findViewById(R.id.editTextCategoryAPTPI)
        amountTE = findViewById(R.id.editTextAmountAPTPI)
        descriptionTE = findViewById(R.id.editTextDescriptionAPTPI)

        db = Room.databaseBuilder(
            applicationContext,
            GSADatabase::class.java, "gsa_database"
        ).allowMainThreadQueries().build()

        val list = db.productDao().getAll()
        products = list.toMutableList()

        nameTE.addTextChangedListener(object : TextWatcher
        {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int)
            {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int)
            {

            }

            override fun afterTextChanged(editable: Editable?)
            {
                for(i in products?.indices!!)
                {
                    if(products[i].name.toString() == nameTE.editableText.toString())
                    {
                        HelperFunctions.makeDialog(applicationContext, "Error", applicationContext.getString(R.string.productExists))
                        break
                    }
                }
            }
        }
        )

        setOnClickListeners()
    }

    private fun checkProductFields(name : String?, category : String?, amount : String?, description : String?) : Boolean
    {
        var isValid = true

        if(name == "" || category == "" || amount == "" || description == ""
            || amount!!.toInt() < 1)
        {
            if(name == "")
                Toast.makeText(this, R.string.nameIsNullAPTGSLA, Toast.LENGTH_SHORT).show()

            if(category == "")
                Toast.makeText(this, R.string.categoryIsNullAPTGSLA, Toast.LENGTH_SHORT).show()

            if(description == "")
                Toast.makeText(this, R.string.descriptionIsNullAPTGSLA, Toast.LENGTH_SHORT).show()

            if(amount == "")
                Toast.makeText(this, R.string.amountIsNullAPTGSLA, Toast.LENGTH_SHORT).show()

            if(amount!!.toInt() < 1)
                Toast.makeText(this, R.string.amountIsUnder1, Toast.LENGTH_SHORT).show()

            isValid = false
        }

        return isValid
    }

    private fun setOnClickListeners()
    {
        val addProductButton = findViewById<Button>(R.id.buttonAddProductAPTPI)

        addProductButton.setOnClickListener()
        {
            try
            {
                val name = nameTE.text.toString()
                val category = categoryTE.text.toString()
                val amount = amountTE.text.toString()
                val description = descriptionTE.text.toString()

                if(checkProductFields(name, category, amount, description))
                {
                    val product = Product(products.size + 1, name, 0, amount.toInt(), description, category)
                    db.productDao().insertAll(product)
                    finish()
                }
            }
            catch (e: Exception)
            {
                // Manejar excepción o imprimir el error en Logcat
                e.printStackTrace()
            }
        }
    }
}