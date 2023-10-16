package com.maybomitobar.groceryshoppingassistant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import com.maybomitobar.groceryshoppingassistant.Classes.GroceryShoppingList
import android.widget.EditText
import android.widget.Toast
import com.maybomitobar.groceryshoppingassistant.Classes.Product

class AddProductToGSLActivity : AppCompatActivity()
{
    private lateinit var productsInTheGSL : ArrayList<Product>

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product_to_gslactivity)

        var groceryShoppingList = intent.getParcelableExtra<GroceryShoppingList>("gSL")

        var nameET = findViewById<EditText>(R.id.editTextNameAPTGSL)
        var priceET = findViewById<EditText>(R.id.editTextPriceAPTGSL)
        var amountET = findViewById<EditText>(R.id.editTextAmountAPTGSL)
        var descriptionET = findViewById<EditText>(R.id.editTextDescriptionAPTGSL)
        var categoryET = findViewById<EditText>(R.id.editTextCategoryAPTGSL)

        val AddProductButton = findViewById<Button>(R.id.buttonAddProductToGSL)

        productsInTheGSL = groceryShoppingList?.productsToBuy!!

        nameET.addTextChangedListener(object : TextWatcher
        {

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int)
            {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int)
            {

            }

            override fun afterTextChanged(editable: Editable?)
            {

                for (i in productsInTheGSL.indices)
                {
                    if(productsInTheGSL[i].name.toString() == nameET.editableText.toString())
                    {
                        priceET.setText(productsInTheGSL[i].price.toString())
                        amountET.setText(productsInTheGSL[i].amount.toString())
                        descriptionET.setText(productsInTheGSL[i].description.toString())
                        categoryET.setText(productsInTheGSL[i].category.toString())
                    }
                }

            }

        }
        )

        AddProductButton.setOnClickListener()
        {
            if(CheckProductFields(nameET.text.toString(), priceET.text.toString(), amountET.text.toString(), descriptionET.text.toString(), categoryET.text.toString()))
            {
                val product = Product(productsInTheGSL.size + 1, nameET.text.toString(),
                    priceET.text.toString().toInt(), amountET.text.toString().toInt(),
                    descriptionET.text.toString(), categoryET.text.toString())

                val newIntent = Intent()
                newIntent.putExtra("new", product)
                setResult(RESULT_OK, newIntent)
                finish()
            }
        }
    }

    private fun CheckProductFields(name : String?, price : String?, amount : String?, description : String?, category : String?) : Boolean
    {
        var isValid : Boolean = true

        if(name == "" || price == "" || amount == "" || description == "" ||
            category == "" || price!!.toInt() < 1 || amount!!.toInt() < 1)
        {
            if(name == "")
                Toast.makeText(this, R.string.nameIsNullAPTGSLA, Toast.LENGTH_SHORT).show()

            if(price == "")
                Toast.makeText(this, R.string.priceIsNullAPTGSLA, Toast.LENGTH_SHORT).show()

            if(amount == "")
                Toast.makeText(this, R.string.amountIsNullAPTGSLA, Toast.LENGTH_SHORT).show()

            if(description == "")
                Toast.makeText(this, R.string.descriptionIsNullAPTGSLA, Toast.LENGTH_SHORT).show()

            if(category == "")
                Toast.makeText(this, R.string.categoryET_APTGSL, Toast.LENGTH_SHORT).show()

            if(price!!.toInt() < 1)
                Toast.makeText(this, R.string.priceIsUnder1, Toast.LENGTH_SHORT).show()

            if(amount!!.toInt() < 1)
                Toast.makeText(this, R.string.amountIsUnder1, Toast.LENGTH_SHORT).show()

            isValid = false
        }


        return isValid
    }
}