package com.maybomitobar.groceryshoppingassistant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.room.Room
import com.maybomitobar.groceryshoppingassistant.Classes.MovementInfo
import com.maybomitobar.groceryshoppingassistant.Classes.Product
import com.maybomitobar.groceryshoppingassistant.Databases.GSADatabase
import com.maybomitobar.groceryshoppingassistant.Helper.HelperFunctions

class AddProductToPIActivity : AppCompatActivity()
{
    private lateinit var products : MutableList<Product>
    private lateinit var movementsInfo : MutableList<MovementInfo>
    private lateinit var nameTE : EditText
    private lateinit var priceTE : EditText
    private lateinit var categoryTE : EditText
    private lateinit var amountTE : EditText
    private lateinit var descriptionTE : EditText
    private var productExists : Boolean = false
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

        try
        {
            val listProducts = db.productDao().getAll()
            products = listProducts.toMutableList()
            val listMovementsInfo = db.movementInfoDao().getAll()
            movementsInfo = listMovementsInfo.toMutableList()
        }
        catch (e: Exception)
        {

        }

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
                if (::products.isInitialized)
                {
                    productExists = false

                    for(i in products?.indices!!)
                    {
                        if(products[i].name.toString() == nameTE.editableText.toString())
                        {
                            HelperFunctions.makeDialog(this@AddProductToPIActivity, "Error", applicationContext.getString(R.string.productExists))
                            productExists = true
                            break
                        }
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
                val amount = amountTE.text.toString()
                val price = priceTE.text.toString()
                val description = descriptionTE.text.toString()
                val category = categoryTE.text.toString()

                if(checkProductFields(name, category, amount, description) && !productExists)
                {
                    var product : Product

                    if (::products.isInitialized)
                        product = Product(products.size + 1, name, price.toInt(), amount.toInt(), description, category)

                    else
                        product = Product(1, name, price.toInt(), amount.toInt(), description, category)

                    var movementInfo : MovementInfo

                    if(::movementsInfo.isInitialized)
                        movementInfo = MovementInfo(movementsInfo.size + 1, "Add", product.id)

                    else
                        movementInfo = MovementInfo(1, "Add", product.id)

                    db.productDao().insertAll(product)
                    db.movementInfoDao().insertAll(movementInfo)
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