package com.example.demoappkissan.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demoappkissan.R
import com.example.demoappkissan.model.ContactDetails
import com.example.demoappkissan.utils.OnItemClick
import kotlinx.android.synthetic.main.item_contact_details.view.*

class ContactListAdapter(val callback: OnItemClick) :
    RecyclerView.Adapter<ContactListAdapter.ContactsViewHolder>() {

    val list = ArrayList<ContactDetails>()

    fun addData(newList: List<ContactDetails>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ContactsViewHolder(inflater.inflate(R.layout.item_contact_details, parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        holder.bindData(list.get(position))
    }

    inner class ContactsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindData(data: ContactDetails) {
            itemView.apply {
                tvFullName.text = "${data.firstName} ${data.lastName}"
                cardMain.setOnClickListener {
                    callback.onItemClick(data)
                }
            }
        }
    }
}