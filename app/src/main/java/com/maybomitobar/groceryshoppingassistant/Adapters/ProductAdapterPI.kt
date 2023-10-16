package com.maybomitobar.groceryshoppingassistant.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import com.maybomitobar.groceryshoppingassistant.Classes.Product
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.maybomitobar.groceryshoppingassistant.R

class ProductInventoryAdapter
    (
    context : Context,
    resource : Int,
    products : List<Product>
) : ArrayAdapter<Product>(context, resource, products)
{

    override fun getView(pos : Int, convertView : View?, parent : ViewGroup) : View
    {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val listItemView = convertView ?: inflater.inflate(R.layout.activity_pantry_inventory, null)

        val product = getItem(pos)

        val nameTextView = listItemView.findViewById<TextView>(R.id.textViewNamePI)
        val categoryTextView = listItemView.findViewById<TextView>(R.id.textViewCategoryPI)
        val descriptionTextView = listItemView.findViewById<TextView>(R.id.textViewDescriptionPI)
        val amountTextView = listItemView.findViewById<TextView>(R.id.textViewAmountPI)

        nameTextView.text = product?.name
        categoryTextView.text = R.string.productCategory.toString() + " " + product?.category
        descriptionTextView.text = R.string.productDescription.toString() + " " + product?.description
        amountTextView.text = R.string.productAmount.toString() + " " + product?.amount.toString()

        return listItemView
    }

}