package com.maybomitobar.groceryshoppingassistant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import com.maybomitobar.groceryshoppingassistant.Classes.User

class HomeActivity : AppCompatActivity() {

    private lateinit var user : User

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        user = intent.getParcelableExtra("user")!!

        var buttonPantry: Button = findViewById(R.id.buttonPantryInventory)
        var buttonShoppingList: Button = findViewById(R.id.buttonGroceryList)

        buttonPantry.setOnClickListener()
        {
            val intentPantryActivity = Intent(this, PantryInventoryActivity::class.java)
            intentPantryActivity.putExtra("user", user)
            startActivity(intentPantryActivity)
        }

        buttonShoppingList.setOnClickListener()
        {
            val intentGroceryShoppingListActivity = Intent(this, GroceryShoppingListActivity::class.java)
            intentGroceryShoppingListActivity.putExtra("user", user)
            startActivity(intentGroceryShoppingListActivity)
        }
    }
}