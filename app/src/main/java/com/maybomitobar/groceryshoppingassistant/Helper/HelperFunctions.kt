package com.maybomitobar.groceryshoppingassistant.Helper

import android.content.Context
import android.app.AlertDialog

class HelperFunctions
{
    companion object
    {
        fun makeDialog(context : Context, title : String, message : String)
        {
            val builder = AlertDialog.Builder(context)

            builder.setTitle(title)
            builder.setMessage(message)

            builder.setPositiveButton("Ok")
            {
                dialog, which -> dialog.dismiss()
            }
        }
    }
}