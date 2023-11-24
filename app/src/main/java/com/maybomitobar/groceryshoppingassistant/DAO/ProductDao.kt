package com.maybomitobar.groceryshoppingassistant.DAO

import com.maybomitobar.groceryshoppingassistant.Classes.Product

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProductDao {
    @Query("SELECT * FROM product")
    fun getAll() : List<Product>

    @Query("SELECT * FROM product WHERE id IN (:productIds)")
    fun loadAllByIds(productIds: IntArray) : List<Product>

    @Query("SELECT * FROM product WHERE name LIKE :name LIMIT 1")
    fun findByName(name: String) : Product

    @Insert
    fun insertAll(vararg products: Product)

    @Delete
    fun delete(product: Product)
}