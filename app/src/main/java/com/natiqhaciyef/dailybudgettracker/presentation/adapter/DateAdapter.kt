package com.natiqhaciyef.dailybudgettracker.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.natiqhaciyef.dailybudgettracker.databinding.RecyclerDateRowBinding

class DateAdapter(val mContext: Context, val list: MutableList<String>) :
    RecyclerView.Adapter<DateAdapter.DateHolder>() {

    private var listener: SetOnDateClick? = null

    inner class DateHolder(val binding: RecyclerDateRowBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateHolder {
        val binding = RecyclerDateRowBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return DateHolder(binding)
    }

    override fun onBindViewHolder(holder: DateHolder, position: Int) {
        val view = holder.binding
        val date = list[position]
        view.dateTextView.text = date

        holder.itemView.setOnClickListener {
            listener?.setOnClick(date)
        }
    }

    fun onClick(l: SetOnDateClick){
        this.listener = l
    }

    override fun getItemCount(): Int = list.size

}