package com.maybomitobar.groceryshoppingassistant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.maybomitobar.groceryshoppingassistant.Classes.PantryInventory
import com.maybomitobar.groceryshoppingassistant.Classes.Product

class AddProductToPIActivity : AppCompatActivity()
{
    private lateinit var productsInPI : ArrayList<Product>

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product_to_piactivity)

        val productInventory = intent.getParcelableExtra<PantryInventory>("pI")!!

        productsInPI = productInventory?.productsList!!

        val nameTE = findViewById<EditText>(R.id.editTextNameAPTPI)
        val categoryTE = findViewById<EditText>(R.id.editTextCategoryAPTPI)
        val amountTE = findViewById<EditText>(R.id.editTextAmountAPTPI)
        val descriptionTE = findViewById<EditText>(R.id.editTextDescriptionAPTPI)

        val addProductButton = findViewById<Button>(R.id.buttonAddProductAPTPI)

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
                for(i in productsInPI.indices)
                {
                    if(productsInPI[i].name.toString() == nameTE.editableText.toString())
                    {
                        categoryTE.setText(productsInPI[i].category.toString())
                        descriptionTE.setText(productsInPI[i].description.toString())
                        amountTE.setText(productsInPI[i].amount.toString())
                    }
                }
            }
        }
        )

        addProductButton.setOnClickListener()
        {

            try
            {

                val name = nameTE.text.toString()
                val category = categoryTE.text.toString()
                val amount = amountTE.text.toString()
                val description = descriptionTE.text.toString()

                if(CheckProductFields(name, category, amount, description))
                {
                    val product = Product(productsInPI.size + 1, name, 0, amount.toInt(), description, category)
                    val newIntent = Intent()
                    newIntent.putExtra("new", product)
                    setResult(RESULT_OK, newIntent)
                    finish()
                }

            } catch (e: Exception)
            {
                // Manejar excepción o imprimir el error en Logcat
                e.printStackTrace()
            }


        }


    }

    private fun CheckProductFields(name : String?, category : String?, amount : String?, description : String?) : Boolean
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
}