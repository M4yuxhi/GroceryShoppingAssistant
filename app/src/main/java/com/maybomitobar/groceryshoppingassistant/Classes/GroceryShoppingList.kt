package com.maybomitobar.groceryshoppingassistant.Classes

import android.os.Parcel
import android.os.Parcelable
import java.util.ArrayList

data class GroceryShoppingList
(

    val id: Int,
    val productsToBuy: ArrayList<Product>?,
    var totalPrice: Int,
    val moneyAvalible: Int,
    val shoppingPlaces : ArrayList<Shop>?

) : Parcelable
{

    constructor(parcel: Parcel) : this
    (
        parcel.readInt(),
        parcel.createTypedArrayList(Product.CREATOR),
        parcel.readInt(),
        parcel.readInt(),
        parcel.createTypedArrayList(Shop.CREATOR)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int)
    {
        parcel.writeInt(id)
        parcel.writeTypedList(productsToBuy)
        parcel.writeInt(totalPrice)
        parcel.writeInt(moneyAvalible)
        parcel.writeTypedList(shoppingPlaces)
    }

    override fun describeContents() : Int
    {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GroceryShoppingList>
    {
        override fun createFromParcel(parcel: Parcel) : GroceryShoppingList
        {
            return GroceryShoppingList(parcel)
        }

        override fun newArray(size: Int) : Array<GroceryShoppingList?>
        {
            return arrayOfNulls(size)
        }
    }

    private fun obtainRemainingMoney() : Int
    {
        return moneyAvalible - totalPrice
    }

    fun addElementToProductToBuyList(product: Product)
    {
        productsToBuy!!.add(product)
        totalPrice += product.price * product.amount
    }
}