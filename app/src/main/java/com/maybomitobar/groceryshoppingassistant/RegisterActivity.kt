package com.maybomitobar.groceryshoppingassistant

import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.os.Bundle
import android.widget.EditText
import android.content.Intent
import android.widget.Toast
import com.maybomitobar.groceryshoppingassistant.Classes.GroceryShoppingList
import com.maybomitobar.groceryshoppingassistant.Classes.PantryInventory
import com.maybomitobar.groceryshoppingassistant.Classes.Product
import com.maybomitobar.groceryshoppingassistant.Classes.User
import com.maybomitobar.groceryshoppingassistant.Classes.Shop

class RegisterActivity : AppCompatActivity()
{
    private var usersList = arrayListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        usersList = intent.getParcelableArrayListExtra("usersList")!!

        var userNameET: EditText = findViewById(R.id.editTextUsernameR)
        var passwordET: EditText = findViewById(R.id.editTextPasswordR)
        var rePasswordET: EditText = findViewById(R.id.editTextRePasswordR)
        var isUserNameInUse: Boolean = false

        var registerB = findViewById<Button>(R.id.buttonRegisterR)

        registerB.setOnClickListener()
        {
            val userName = userNameET.text.toString()
            val passwords = arrayOf(
                passwordET.text.toString(),
                rePasswordET.text.toString()
            )

            if(CheckRegisterFields(userName, passwords[0], passwords[1]))
            {
                for(i in usersList.indices)
                {
                    if(usersList[i].name == userName)
                    {
                        Toast.makeText(this, R.string.usernameExistR, Toast.LENGTH_SHORT).show()
                        isUserNameInUse = true
                        break
                    }
                }
            }

            if(!isUserNameInUse)
            {
                val user = User(usersList.size + 1, userName, passwords[0], PantryInventory(usersList.size + 1, arrayListOf<Product>()),
                    GroceryShoppingList(usersList.size + 1, arrayListOf<Product>(), 0, 0, arrayListOf<Shop>()))
                val newIntent = Intent()
                newIntent.putExtra("new", user)
                setResult(RESULT_OK, newIntent)
                finish()
            }
        }

    }

    private fun CheckRegisterFields(username: String?, password1: String?, password2: String?) : Boolean
    {
        var isValid: Boolean = true

        if(username == "" || password1 == "" || password2 == "" || password1 != password2)
        {
            if(username == "")
                Toast.makeText(this, R.string.userNameIsNull, Toast.LENGTH_SHORT).show()

            if(password1 == "")
                Toast.makeText(this, R.string.passwordIsNull, Toast.LENGTH_SHORT).show()

            if(password2 == "")
                Toast.makeText(this, R.string.rePasswordIsNull, Toast.LENGTH_SHORT).show()

            if(password1 != password2)
                Toast.makeText(this, R.string.passwordsArentEqual, Toast.LENGTH_SHORT).show()

            isValid = false
        }

        return isValid
    }
}