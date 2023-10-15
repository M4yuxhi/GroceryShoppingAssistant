package com.maybomitobar.groceryshoppingassistant.Classes

import android.os.Parcel
import android.os.Parcelable

data class User
(

    val id: Int,
    val name: String?,
    val password: String?,
    val mail: String?,
    val ownPantryId: Int,
    val groceryListId: Int

) : Parcelable {

    constructor(parcel: Parcel) : this
    (
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int)
    {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(password)
        parcel.writeString(mail)
        parcel.writeInt(ownPantryId)
        parcel.writeInt(groceryListId)
    }

    override fun describeContents(): Int
    {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User>
    {
        override fun createFromParcel(parcel: Parcel): User
        {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?>
        {
            return arrayOfNulls(size)
        }
    }
}