package com.maybomitobar.groceryshoppingassistant.Classes

import android.os.Parcel
import android.os.Parcelable

data class PantryInventory
(

    val id: Int,
    val productsList: ArrayList<Product>?

) : Parcelable
{
    constructor(parcel: Parcel) : this
    (
        parcel.readInt(),
        parcel.createTypedArrayList(Product.CREATOR)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int)
    {
        parcel.writeInt(id)
        parcel.writeTypedList(productsList)
    }

    override fun describeContents() : Int
    {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PantryInventory>
    {
        override fun createFromParcel(parcel: Parcel) : PantryInventory
        {
            return PantryInventory(parcel)
        }

        override fun newArray(size: Int) : Array<PantryInventory?>
        {
            return arrayOfNulls(size)
        }
    }

    fun addElementToProductList(product: Product)
    {
        productsList!!.add(product)
    }
}