package com.maybomitobar.groceryshoppingassistant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.maybomitobar.groceryshoppingassistant.Classes.PantryInventory
import com.maybomitobar.groceryshoppingassistant.Classes.Product

class AddProductToPIActivity : AppCompatActivity()
{
    private lateinit var productsInPI : ArrayList<Product>
    private lateinit var nameTE : EditText
    private lateinit var priceTE : EditText
    private lateinit var categoryTE : EditText
    private lateinit var amountTE : EditText
    private lateinit var descriptionTE : EditText
    private var productExists : Boolean = false
    private lateinit var productExistentName : String

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product_to_piactivity)

        val productInventory = intent.getParcelableExtra<PantryInventory>("pI")!!

        productsInPI = productInventory?.productsList!!

        nameTE = findViewById<EditText>(R.id.editTextNameAPTPI)
        priceTE = findViewById<EditText>(R.id.editTextPriceAPTPI)
        categoryTE = findViewById<EditText>(R.id.editTextCategoryAPTPI)
        amountTE = findViewById<EditText>(R.id.editTextAmountAPTPI)
        descriptionTE = findViewById<EditText>(R.id.editTextDescriptionAPTPI)


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

                        productExists = true
                        productExistentName = productsInPI[i].name.toString()
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
                for(i in productsInPI.indices)
                {
                    val amount = productsInPI[i].amount
                    if(amount > 0 && productsInPI[i].name == productExistentName) productsInPI[i].amount ==  amount + amountTE.text.toString().toInt()
                    //else helperFunctions.makeToast()
                    finish()
                }            }

            try
            {
                val name = nameTE.text.toString()
                val category = categoryTE.text.toString()
                val amount = amountTE.text.toString()
                val description = descriptionTE.text.toString()

                if(checkProductFields(name, category, amount, description))
                {
                    val product = Product(productsInPI.size + 1, name, 0, amount.toInt(), description, category)
                    val newIntent = Intent()
                    newIntent.putExtra("new", product)
                    setResult(RESULT_OK, newIntent)
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