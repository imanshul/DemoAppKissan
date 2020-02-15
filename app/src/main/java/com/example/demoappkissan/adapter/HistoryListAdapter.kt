package com.example.demoappkissan.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.demoappkissan.R
import com.example.demoappkissan.constants.SuccessFailureConstants
import com.example.demoappkissan.db.entity.SmsSentHistoryEntity
import com.example.demoappkissan.utils.DateTimeUtils
import kotlinx.android.synthetic.main.item_history_details.view.*

class HistoryListAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val EMPTY_VIEW: Int = 555

    val list = ArrayList<SmsSentHistoryEntity>()

    fun addData(newList: List<SmsSentHistoryEntity>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        if (list.size == 0) {
            return EMPTY_VIEW
        }
        return super.getItemViewType(position)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == EMPTY_VIEW) {
            EmptyViewHolder(
                inflater.inflate(
                    R.layout.item_no_data,
                    parent,
                    false
                )
            )
        } else {
            ContactsHistoryViewHolder(
                inflater.inflate(
                    R.layout.item_history_details,
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemCount(): Int = if (list.size > 0) list.size else 1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            EMPTY_VIEW -> {
                (holder as EmptyViewHolder).bind()
            }
            else -> {
                (holder as ContactsHistoryViewHolder).bindData(list.get(position))
            }
        }

    }

    inner class ContactsHistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        
        fun bindData(data: SmsSentHistoryEntity) {
            itemView.apply {
                tvFullName.text = data.fullName
                tvContactNumber.text = data.contactNumber
                tvMessgaeText.text = data.messageBody
                if(data.statusCode==SuccessFailureConstants.SUCCESS){
                    tvStatus.text = context.getString(R.string.success)
                    tvStatus.setTextColor(ContextCompat.getColor(context,R.color.green))
                }else{
                   tvStatus.text = context.getString(R.string.fail)
                    tvStatus.setTextColor(ContextCompat.getColor(context,R.color.red))
                }
                tvSentTime.text = DateTimeUtils.getDateAndTimeFromMillies(data.sentTime)
            }
        }
    }

    inner class EmptyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {}
    }
}