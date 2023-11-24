package com.maybomitobar.groceryshoppingassistant.Classes

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey val id : Int,
    @ColumnInfo(name = "name") val name : String?,
    @ColumnInfo(name = "price") val price : Int,
    @ColumnInfo(name = "amount") val amount : Int,
    @ColumnInfo(name = "description") val description : String?,
    @ColumnInfo(name = "category") val category : String?
): Parcelable {

    constructor(parcel: Parcel) : this
        (
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel : Parcel, flags : Int)
    {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeInt(price)
        parcel.writeInt(amount)
        parcel.writeString(description)
        parcel.writeString(category)

    }

    override fun describeContents(): Int
    {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Product>
    {
        override fun createFromParcel(parcel: Parcel) : Product
        {
            return Product(parcel)
        }

        override fun newArray(size: Int) : Array<Product?>
        {
            return arrayOfNulls(size)
        }
    }
}