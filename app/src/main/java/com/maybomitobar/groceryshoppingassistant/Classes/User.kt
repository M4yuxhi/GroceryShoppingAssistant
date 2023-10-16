package com.maybomitobar.groceryshoppingassistant.Classes

import android.os.Parcel
import android.os.Parcelable

data class User
(

    val id: Int,
    val name: String?,
    val password: String?,
    val ownPantry: PantryInventory?,
    val groceryList: GroceryShoppingList?

) : Parcelable {

    constructor(parcel: Parcel) : this
    (
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(PantryInventory::class.java.classLoader),
        parcel.readParcelable(GroceryShoppingList::class.java.classLoader)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int)
    {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(password)
        parcel.writeParcelable(ownPantry, flags)
        parcel.writeParcelable(groceryList, flags)
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