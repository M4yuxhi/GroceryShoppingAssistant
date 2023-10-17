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

    override fun getView(pos: Int, convertView: View?, parent: ViewGroup): View {
        var listItemView = convertView
        val holder: ViewHolder

        if (listItemView == null)
        {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            listItemView = inflater.inflate(R.layout.activity_pantry_inventory, parent, false)

            holder = ViewHolder()
            holder.nameTextView = listItemView.findViewById(R.id.textViewNamePI)
            holder.categoryTextView = listItemView.findViewById(R.id.textViewCategoryPI)
            holder.descriptionTextView = listItemView.findViewById(R.id.textViewDescriptionPI)
            holder.amountTextView = listItemView.findViewById(R.id.textViewAmountPI)

            listItemView.tag = holder
        } else {
            holder = listItemView.tag as ViewHolder
        }

        val product = getItem(pos)

        holder.nameTextView.text = product?.name
        holder.categoryTextView.text = context.getString(R.string.productCategory) + ": " + product?.category
        holder.descriptionTextView.text = context.getString(R.string.adapDescription) + ": " + product?.description
        holder.amountTextView.text = context.getString(R.string.productAmount) + ": " + product?.amount.toString()

        return listItemView!!
    }

    private class ViewHolder
    {
        lateinit var nameTextView: TextView
        lateinit var categoryTextView: TextView
        lateinit var descriptionTextView: TextView
        lateinit var amountTextView: TextView
    }
}