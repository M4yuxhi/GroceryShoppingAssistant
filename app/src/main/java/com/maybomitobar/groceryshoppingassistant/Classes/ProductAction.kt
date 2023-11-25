package com.maybomitobar.groceryshoppingassistant.Classes

import android.os.Parcel
import android.os.Parcelable

data class ProductAction
(
    val product: Product?,
    val action: String?
) : Parcelable
{
    constructor(parcel: Parcel) : this
    (
        parcel.readParcelable(Product::class.java.classLoader),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int)
    {
        parcel.writeParcelable(product, flags)
        parcel.writeString(action)
    }

    override fun describeContents(): Int
    {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductAction> {
        override fun createFromParcel(parcel: Parcel): ProductAction {
            return ProductAction(parcel)
        }

        override fun newArray(size: Int): Array<ProductAction?> {
            return arrayOfNulls(size)
        }
    }

}

