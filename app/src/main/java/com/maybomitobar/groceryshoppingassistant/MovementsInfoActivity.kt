package com.maybomitobar.groceryshoppingassistant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import androidx.room.Room
import com.maybomitobar.groceryshoppingassistant.Adapters.MovementInfoAdapterMI
import com.maybomitobar.groceryshoppingassistant.Classes.MovementInfo
import com.maybomitobar.groceryshoppingassistant.Databases.GSADatabase

class MovementsInfoActivity : AppCompatActivity()
{
    private lateinit var movementsInfoLV : ListView
    private lateinit var movementsInfo : MutableList<MovementInfo>
    private lateinit var db : GSADatabase
    private lateinit var adapterElements : MovementInfoAdapterMI

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movements_info)

        movementsInfoLV = findViewById(R.id.moveInfoLV)

        try
        {
            db = Room.databaseBuilder(
                applicationContext,
                GSADatabase::class.java, "gsa_database"
            ).allowMainThreadQueries().build()
        }
        catch (e: Exception)
        {
            e.printStackTrace()
            // Puedes registrar la excepción o mostrar un mensaje de error
        }

        refreshDatabase()
    }

    private fun refreshDatabase()
    {
        try
        {
            val listMovementsInfo = db.movementInfoDao().getAll()
            movementsInfo = listMovementsInfo.toMutableList()
            setAdapter()
        }
        catch (e : Exception)
        {

        }
    }

    private fun setAdapter()
    {
        movementsInfoLV.invalidate()
        adapterElements = MovementInfoAdapterMI(this, R.layout.activity_movement_info_in_mvactivity, movementsInfo)
        movementsInfoLV.adapter = adapterElements
    }
}