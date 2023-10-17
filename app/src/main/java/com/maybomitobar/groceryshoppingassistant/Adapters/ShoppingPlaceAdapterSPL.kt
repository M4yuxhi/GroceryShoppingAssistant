package com.maybomitobar.groceryshoppingassistant.Adapters

import android.widget.ArrayAdapter
import com.maybomitobar.groceryshoppingassistant.Classes.Shop
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.maybomitobar.groceryshoppingassistant.R

class ShoppingPlaceAdapter
    (
    context : Context,
    resource : Int,
    shops : List<Shop>
) : ArrayAdapter<Shop>(context, resource, shops)
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View
    {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val listItemView = convertView ?: inflater.inflate(R.layout.activity_place_to_spactivity, null)

        val shop = getItem(position)

        val nameTV = listItemView.findViewById<TextView>(R.id.textViewNamePTSP)
        val latitudeTV = listItemView.findViewById<TextView>(R.id.textViewLatitudePTSP)
        val directionTV = listItemView.findViewById<TextView>(R.id.textViewDirectionPTSP)
        val descriptionTV = listItemView.findViewById<TextView>(R.id.textViewDescriptionPTSP)

        nameTV.text = shop?.name
        latitudeTV.text = context.getString(R.string.adapLatitude) + ": " + shop?.latitude
        directionTV.text = context.getString(R.string.adapDirection) + ": " + shop?.direction
        descriptionTV.text = context.getString(R.string.adapDescription) + ": " + shop?.description

        return listItemView
    }
}