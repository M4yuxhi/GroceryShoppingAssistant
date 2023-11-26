package com.maybomitobar.groceryshoppingassistant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.room.Room
import com.maybomitobar.groceryshoppingassistant.Classes.MovementInfo
import com.maybomitobar.groceryshoppingassistant.Classes.Product
import com.maybomitobar.groceryshoppingassistant.Databases.GSADatabase

class SeeProductDetailsActivity : AppCompatActivity()
{
    private lateinit var product : Product
    private lateinit var db : GSADatabase
    private lateinit var movementsInfo : MutableList<MovementInfo>
    private lateinit var priceET : EditText

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_see_product_details)

        product = intent.getParcelableExtra("product")!!

        db = Room.databaseBuilder(
            applicationContext,
            GSADatabase::class.java, "gsa_database"
        ).allowMainThreadQueries().build()

        try
        {
            val list = db.movementInfoDao().getAll()
            movementsInfo = list.toMutableList()
        }
        catch (e : Exception)
        {

        }

        setTextViews()
        setOnClickListeners()
    }

    private fun setOnClickListeners()
    {
        val addAmountButton : Button = findViewById(R.id.buttonAddSPD)
        val subtractAmountButton : Button = findViewById(R.id.buttonSubstractSPD)
        val saveChangesButton : Button = findViewById(R.id.buttonSaveChangesSPD)

        addAmountButton.setOnClickListener()
        {
            product.amount++
            val amountTV = findViewById<TextView>(R.id.textViewAmountSPD)
            amountTV.text = applicationContext.getString(R.string.productAmount) + " " + product.amount.toString()
        }
        subtractAmountButton.setOnClickListener()
        {
            product.amount--
            val amountTV = findViewById<TextView>(R.id.textViewAmountSPD)
            amountTV.text = applicationContext.getString(R.string.productAmount) + " " + product.amount.toString()
        }
        saveChangesButton.setOnClickListener()
        {
            product.price = priceET.text.toString().toInt()
            db.productDao().update(product)

            val movementInfo = MovementInfo(movementsInfo.size + 1, "Update", product.id)

            db.movementInfoDao().insertAll(movementInfo)
            finish()
        }
    }

    private fun setTextViews()
    {
        val nameTV = findViewById<TextView>(R.id.textViewNameSPD)
        val priceTV = findViewById<TextView>(R.id.textViewPriceSPD)
        priceET = findViewById(R.id.priceETSPD)
        val amountTV = findViewById<TextView>(R.id.textViewAmountSPD)
        val descriptionTV = findViewById<TextView>(R.id.textViewDescriptionSPD)
        val categoryTV = findViewById<TextView>(R.id.textViewCategorySPD)

        nameTV.text = applicationContext.getString(R.string.productName) + ": " + product.name
        priceTV.text = applicationContext.getString(R.string.productPrice) + ": "
        priceET.setText(product.price.toString())
        amountTV.text = applicationContext.getString(R.string.productAmount) + ": " + product.amount.toString()
        descriptionTV.text = applicationContext.getString(R.string.productDescription) + ": " + product.description
        categoryTV.text = applicationContext.getString(R.string.productCategory) + ": " + product.category
    }
}