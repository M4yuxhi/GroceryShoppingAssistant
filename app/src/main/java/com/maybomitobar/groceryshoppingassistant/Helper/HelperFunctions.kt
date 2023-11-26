package com.maybomitobar.groceryshoppingassistant.Helper

import android.content.Context
import android.app.AlertDialog

class HelperFunctions
{
    companion object
    {

        fun makeDialog
        (
            context : Context?,
            title : String,
            message : String
        )
        {
            if (context == null) return

            val builder = createDialogBuilder(context, title, message)

            builder.setTitle(title)
            builder.setMessage(message)

            builder.setPositiveButton("Ok")
            {
                dialog, which -> dialog.dismiss()
            }

            val alertDialog = builder.create()
            alertDialog.show()
        }

        fun makeDialog
        (
            context : Context?,
            title : String,
            message : String,
            positiveLabel : String,
            negativeLabel : String,
            positiveAction: () -> Unit,
            negativeAction: () -> Unit
        )
        {
            if (context == null) return

            val builder = createDialogBuilder(context, title, message)

            builder.setTitle(title)
            builder.setMessage(message)

            builder.setPositiveButton(positiveLabel)
            {
                dialog, which ->
                positiveAction.invoke()
                dialog.dismiss()
            }
            builder.setNegativeButton(negativeLabel)
            {
                dialog, which ->
                negativeAction.invoke()
                dialog.dismiss()
            }

            val alertDialog = builder.create()
            alertDialog.show()
        }

        private fun createDialogBuilder
        (
            context: Context,
            title: String,
            message: String
        ): AlertDialog.Builder {
            return AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
        }
    }
}