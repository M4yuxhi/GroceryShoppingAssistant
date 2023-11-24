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
import com.maybomitobar.groceryshoppingassistant.Databases.GSADatabase

class AddProductToPIActivity : AppCompatActivity()
{
    private lateinit var products : MutableList<Product>
    private lateinit var nameTE : EditText
    private lateinit var priceTE : EditText
    private lateinit var categoryTE : EditText
    private lateinit var amountTE : EditText
    private lateinit var descriptionTE : EditText
    private var productExists : Boolean = false
    private lateinit var productExistentName : String

    private lateinit var db : GSADatabase

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product_to_piactivity)

        nameTE = findViewById<EditText>(R.id.editTextNameAPTPI)
        priceTE = findViewById<EditText>(R.id.editTextPriceAPTPI)
        categoryTE = findViewById<EditText>(R.id.editTextCategoryAPTPI)
        amountTE = findViewById<EditText>(R.id.editTextAmountAPTPI)
        descriptionTE = findViewById<EditText>(R.id.editTextDescriptionAPTPI)

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
                for(i in products.indices)
                {
                    if(products[i].name.toString() == nameTE.editableText.toString())
                    {
                        categoryTE.setText(products[i].category.toString())
                        descriptionTE.setText(products[i].description.toString())

                        productExists = true
                        productExistentName = products[i].name.toString()
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
                Toast.makeText(this, R.string.categoryET_APTGSL, Toast.LENGTH_SHORT).show()

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
            if(productExists)
            {
                for(i in products.indices)
                {
                    val amount = products[i].amount
                    if(amount > 0 && products[i].name == productExistentName) products[i].amount ==  amount + amountTE.text.toString().toInt()
                    //else helperFunctions.makeToast()
                    finish()
                }
            }

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