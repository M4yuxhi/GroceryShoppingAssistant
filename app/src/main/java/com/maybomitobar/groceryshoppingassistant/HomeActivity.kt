package com.maybomitobar.groceryshoppingassistant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        setOnClickListeners()
    }

    private fun setOnClickListeners()
    {
        var buttonPantry: Button = findViewById(R.id.buttonPantryInventory)

        buttonPantry.setOnClickListener()
        {
            val intentPantryActivity = Intent(this, PantryInventoryActivity::class.java)
            startActivity(intentPantryActivity)
        }
    }
}