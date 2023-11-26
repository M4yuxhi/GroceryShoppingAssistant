package com.maybomitobar.groceryshoppingassistant.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.maybomitobar.groceryshoppingassistant.Classes.MovementInfo
import com.maybomitobar.groceryshoppingassistant.R

class MovementInfoAdapterMI(
    context : Context,
    resource : Int,
    movementsInfo : List<MovementInfo>
) : ArrayAdapter<MovementInfo> (context, resource, movementsInfo)
{
    override fun getView(pos: Int, convertView: View?, parent: ViewGroup): View
    {
        var listItemView = convertView
        val holder : ViewHolder

        if(listItemView == null)
        {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            listItemView = inflater.inflate(R.layout.activity_movement_info_in_mvactivity, parent, false)

            holder = ViewHolder()
            holder.idTextView = listItemView.findViewById(R.id.textViewIDMI)
            holder.actionTextView = listItemView.findViewById(R.id.textViewActionMI)
            holder.productIdTextView = listItemView.findViewById(R.id.textViewProductIDMI)

            listItemView.tag = holder
        }
        else
        {
            holder = listItemView.tag as ViewHolder
        }

        val movementInfo = getItem(pos)

        holder.idTextView.text = context.getString(R.string.productId) + ": " + movementInfo?.id

        var actionValue: String = ""
        when (movementInfo?.action) {
            "Add" -> {
                actionValue = context.getString(R.string.addActionAdap)
            }
            "Update" -> {
                actionValue = context.getString(R.string.updateActionAdap)
            }
            "Delete" -> {
                actionValue = context.getString(R.string.deleteActionAdap)
            }
        }

        holder.idTextView.append("\n" + context.getString(R.string.movementInfoAction) + ": " + actionValue)
        holder.idTextView.append("\n" + context.getString(R.string.movementInfoProductId) + ": " + movementInfo?.productId)

        return listItemView!!
    }

    private class ViewHolder
    {
        lateinit var idTextView: TextView
        lateinit var actionTextView: TextView
        lateinit var productIdTextView: TextView
    }
}