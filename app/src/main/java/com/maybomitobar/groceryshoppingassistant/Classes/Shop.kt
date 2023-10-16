package com.maybomitobar.groceryshoppingassistant.Classes

import android.os.Parcel
import android.os.Parcelable

data class Shop
(

    val name : String?,
    val latitude : String?,
    val direction : String?,
    val description : String?

) : Parcelable
{

    constructor(parcel: Parcel) : this
    (
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int)
    {
        parcel.writeString(name)
        parcel.writeString(latitude)
        parcel.writeString(direction)
        parcel.writeString(description)
    }

    override fun describeContents(): Int
    {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Shop>
    {
        override fun createFromParcel(parcel : Parcel): Shop
        {
            return Shop(parcel)
        }

        override fun newArray(size : Int): Array<Shop?>
        {
            return arrayOfNulls(size)
        }
    }
}