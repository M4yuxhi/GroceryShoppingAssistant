package com.maybomitobar.groceryshoppingassistant.DAO

import com.maybomitobar.groceryshoppingassistant.Classes.Product

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ProductDao {
    @Query("SELECT * FROM product")
    fun getAll() : List<Product>

    @Query("SELECT * FROM product WHERE id IN (:productIds)")
    fun loadAllByIds(productIds: IntArray): List<Product>

    @Query("SELECT * FROM product WHERE id = :productId LIMIT 1")
    fun findById(productId: Int): Product?

    @Query("SELECT * FROM product WHERE name LIKE :name LIMIT 1")
    fun findByName(name: String) : Product

    @Query("SELECT * FROM product WHERE price = :price")
    fun findByPrice(price: Int): List<Product>

    @Query("SELECT * FROM product WHERE amount = :amount")
    fun findByAmount(amount: Int): List<Product>

    @Query("SELECT * FROM product WHERE description LIKE :description")
    fun findByDescription(description: String): List<Product>

    @Query("SELECT * FROM product WHERE category LIKE :category")
    fun findByCategory(category: String): List<Product>

    @Insert
    fun insertAll(vararg products: Product)

    @Update
    fun update(product: Product)

    @Delete
    fun delete(product: Product)
}