package com.maybomitobar.groceryshoppingassistant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import android.widget.Button
import android.content.Intent

import com.maybomitobar.groceryshoppingassistant.Classes.User

class LogInActivity : AppCompatActivity() {

    private var usersList = arrayListOf<User>()
    private lateinit var userText: EditText
    private lateinit var passwordText: EditText
    private var userFounded : Boolean = false

    companion object
    {
        const val REQUEST_REGISTER = 1
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        userText = findViewById(R.id.editTextUsername)
        passwordText = findViewById(R.id.editTextPassword)

        usersList.add(
            User(1, "Scarlett", "31416", "scarlett@example.com", 1, 1),
        )

        usersList.add(
            User(2, "Mateo", "27183", "mateo@example.com", 2, 2),
        )

        val logInIns = findViewById<Button>(R.id.buttonLogIn)

        logInIns.setOnClickListener()
        {
            val userName = userText.text.toString()
            val password = passwordText.text.toString()

            if(checkUserFields(userName, password))
            {
                for(i in usersList.indices)
                {
                    if(usersList[i].name.toString() == userName && usersList[i].password.toString() == password)
                    {
                        userFounded = true
                        val intentHomeActivity = Intent(this, HomeActivity::class.java)
                        intentHomeActivity.putExtra("usersList", usersList[i])
                        startActivityForResult(intentHomeActivity, REQUEST_REGISTER)
                    }
                }
            }

            if(!userFounded)
            {
                Toast.makeText(this, "No se encontró el usuario.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkUserFields(userName: String?, password: String?) : Boolean
    {
        var isValid = true

        if(userName == "" || password == "")
        {
            if(userName == "")
                Toast.makeText(this, R.string.userNameIsNull, Toast.LENGTH_SHORT).show()

            if(password == "")
                Toast.makeText(this, R.string.passwordIsNull, Toast.LENGTH_SHORT).show()

            isValid = false
        }

        return isValid
    }
}