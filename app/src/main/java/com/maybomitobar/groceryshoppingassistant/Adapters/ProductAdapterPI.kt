package com.maybomitobar.groceryshoppingassistant.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.maybomitobar.groceryshoppingassistant.Classes.Product
import com.maybomitobar.groceryshoppingassistant.R

class ProductInventoryAdapter
    (
    context : Context,
    resource : Int,
    products : List<Product>
) : ArrayAdapter<Product>(context, resource, products)
{

    override fun getView(pos: Int, convertView: View?, parent: ViewGroup): View
    {
        var listItemView = convertView
        val holder: ViewHolder

        if (listItemView == null)
        {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            listItemView = inflater.inflate(R.layout.activity_product_in_piactivity, parent, false)

            holder = ViewHolder()
            holder.idTextView = listItemView.findViewById(R.id.textViewIDPI)
            holder.nameTextView = listItemView.findViewById(R.id.textViewNamePI)
            holder.priceTextView = listItemView.findViewById(R.id.textViewPricePI)
            holder.amountTextView = listItemView.findViewById(R.id.textViewAmountPI)
            holder.descriptionTextView = listItemView.findViewById(R.id.textViewDescriptionPI)
            holder.categoryTextView = listItemView.findViewById(R.id.textViewCategoryPI)

            listItemView.tag = holder
        }
        else
        {
            holder = listItemView.tag as ViewHolder
        }

        val product = getItem(pos)

        holder.idTextView.text = context.getString(R.string.productId) + ": " + product?.id.toString()
        holder.nameTextView.text = context.getString(R.string.productName) + ": " + product?.name
        holder.priceTextView.text = context.getString(R.string.productPrice) + ": " + product?.price.toString()
        holder.amountTextView.text = context.getString(R.string.productAmount) + ": " + product?.amount.toString()
        holder.categoryTextView.text = context.getString(R.string.productCategory) + ": " + product?.category
        holder.descriptionTextView.text = context.getString(R.string.productDescription) + ": " + product?.description
        return listItemView!!
    }

    private class ViewHolder
    {
        lateinit var idTextView: TextView
        lateinit var nameTextView: TextView
        lateinit var priceTextView: TextView
        lateinit var amountTextView: TextView
        lateinit var descriptionTextView: TextView
        lateinit var categoryTextView: TextView
    }
}