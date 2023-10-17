package com.maybomitobar.groceryshoppingassistant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.maybomitobar.groceryshoppingassistant.Classes.Shop

class AddShoppingPlacesActivity : AppCompatActivity()
{
    private lateinit var placesInSPL : ArrayList<Shop>

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_shopping_places)

        placesInSPL = intent.getParcelableArrayListExtra<Shop>("sPL")!!

        val nameTE = findViewById<EditText>(R.id.editTextNameASPA)
        val latitudeTE = findViewById<EditText>(R.id.editTextLatitudeASPA)
        val directionTE = findViewById<EditText>(R.id.editTextDirectionASPA)
        val descriptionTE = findViewById<EditText>(R.id.editTextDescriptionASPA)

        val addPlaceButton = findViewById<Button>(R.id.buttonAddShoppingPlaceASPA)

        addPlaceButton.setOnClickListener()
        {
            val name = nameTE.text.toString()
            val latitude = latitudeTE.text.toString()
            val direction = directionTE.text.toString()
            val description = descriptionTE.text.toString()

            if(CheckPlaceFields(name, latitude, direction, description))
            {
                val shop = Shop(name, latitude, direction, description)
                val newIntent = Intent()
                newIntent.putExtra("new", shop)
                setResult(RESULT_OK, newIntent)
                finish()
            }
        }

    }

    private fun CheckPlaceFields(name : String?, latitude : String?, direction: String?, description : String?) : Boolean
    {
        var isValid = true

        if(name == "" || latitude == "" || direction == "" || description == "")
        {
            if(name == "")
                Toast.makeText(this, R.string.nameIsNullAPTGSLA, Toast.LENGTH_SHORT).show()

            if(latitude == "")
                Toast.makeText(this, R.string.latitudeIsNull, Toast.LENGTH_SHORT).show()

            if(direction == "")
                Toast.makeText(this, R.string.directionIsNull, Toast.LENGTH_SHORT).show()

            if(description == "")
                Toast.makeText(this, R.string.descriptionIsNullAPTGSLA, Toast.LENGTH_SHORT).show()

            isValid = false
        }

        return isValid
    }
}