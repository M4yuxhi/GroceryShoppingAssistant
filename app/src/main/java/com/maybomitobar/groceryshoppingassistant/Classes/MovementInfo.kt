package com.maybomitobar.groceryshoppingassistant.Classes

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
/*
@Entity
data class MovementInfo(
    @PrimaryKey val id : Int,
    @ColumnInfo(name = "action") val action : String?,
    @ColumnInfo(name = "productId") val productId : Int
) : Parcelable {

    constructor(parcel : Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel : Parcel, flags : Int)
    {
        parcel.writeInt(id)
        parcel.writeString(action)
        parcel.writeInt(productId)
    }

    override fun describeContents(): Int
    {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovementInfo>
    {
        override fun createFromParcel(parcel : Parcel): MovementInfo
        {
            return MovementInfo(parcel)
        }

        override fun newArray(size : Int) : Array<MovementInfo?>
        {
            return arrayOfNulls(size)
        }
    }

}
 */