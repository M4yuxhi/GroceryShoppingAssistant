package com.maybomitobar.groceryshoppingassistant.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
//import com.maybomitobar.groceryshoppingassistant.Classes.MovementInfo
/*
@Dao
interface MovementInfoDao {
    @Query("SELECT * FROM movementInfo")
    fun getAll() : List<MovementInfo>

    @Query("SELECT * FROM movementInfo WHERE id IN (:movementInfoIds)")
    fun loadAllByIds(movementInfoIds: IntArray) : List<MovementInfo>

    @Query("SELECT * FROM movementInfo WHERE `action` LIKE :action LIMIT 1")
    fun findByAction(action: String) : MovementInfo

    @Insert
    fun insertAll(vararg movementInfos : MovementInfo)

    @Delete
    fun delete(movementInfo : MovementInfo)
}*/