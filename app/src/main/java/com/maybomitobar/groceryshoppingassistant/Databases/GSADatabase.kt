package com.maybomitobar.groceryshoppingassistant.Databases

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.maybomitobar.groceryshoppingassistant.Classes.MovementInfo
import com.maybomitobar.groceryshoppingassistant.DAO.ProductDao
import com.maybomitobar.groceryshoppingassistant.Classes.Product
import com.maybomitobar.groceryshoppingassistant.DAO.MovementInfoDao

@Database(entities = [Product::class, MovementInfo::class], version = 1)
abstract class GSADatabase : RoomDatabase()
{
    /*
    companion object
    {
        val migration1to2 = object : Migration(1, 2)
        {
            override fun migrate(database: SupportSQLiteDatabase)
            {
                // Lógica de migración de la versión 1 a la versión 2
            }
        }

    }*/

    abstract fun productDao() : ProductDao
    abstract fun movementInfoDao() : MovementInfoDao
}