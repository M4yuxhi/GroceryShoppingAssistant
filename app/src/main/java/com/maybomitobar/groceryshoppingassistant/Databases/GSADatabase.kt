package com.maybomitobar.groceryshoppingassistant.Databases

import androidx.room.Database
import androidx.room.RoomDatabase
//import com.maybomitobar.groceryshoppingassistant.Classes.MovementInfo
import com.maybomitobar.groceryshoppingassistant.DAO.ProductDao
import com.maybomitobar.groceryshoppingassistant.Classes.Product
//import com.maybomitobar.groceryshoppingassistant.DAO.MovementInfoDao

@Database(entities = [Product::class], /*[MovementInfo::class],*/ version = 1)
abstract class GSADatabase : RoomDatabase() {
    abstract fun productDao() : ProductDao
    //abstract fun movementInfoDao() : MovementInfoDao
}