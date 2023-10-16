package com.maybomitobar.groceryshoppingassistant.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import com.maybomitobar.groceryshoppingassistant.Classes.Product
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.maybomitobar.groceryshoppingassistant.R

class GroceryShoppingListAdapter
(
    context : Context,
    resource : Int,
    products : List<Product>
) : ArrayAdapter<Product>(context, resource, products)
{
    override fun getView(pos : Int, convertView : View?, parent : ViewGroup) : View
    {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val listItemView = convertView ?: inflater.inflate(R.layout.activity_product_in_gslactivity, null)

        val product = getItem(pos)


        val idTextView = listItemView.findViewById<TextView>(R.id.textViewIdPIGSL)
        val nameTextView = listItemView.findViewById<TextView>(R.id.textViewNamePIGSL)
        val priceTextView = listItemView.findViewById<TextView>(R.id.textViewPricePIGSL)
        val amountTextView = listItemView.findViewById<TextView>(R.id.textViewAmountPIGSL)
        val descriptionTextView = listItemView.findViewById<TextView>(R.id.textViewDescriptionPIGSL)
        val categoryTextView = listItemView.findViewById<TextView>(R.id.textViewCategoryPIGSL)

        idTextView.text = R.string.productId.toString() + " " + product?.id.toString()
        nameTextView.text = product?.name
        priceTextView.text = R.string.productPrice.toString() + " " + product?.price.toString()
        amountTextView.text = R.string.productAmount.toString() + " " + product?.amount.toString()
        descriptionTextView.text = R.string.productDescription.toString() + " " + product?.description
        categoryTextView.text = R.string.productCategory.toString() + " " + product?.category

        return listItemView
    }
}