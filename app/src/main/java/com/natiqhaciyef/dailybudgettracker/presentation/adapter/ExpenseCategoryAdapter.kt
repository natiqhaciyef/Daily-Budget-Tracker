package com.natiqhaciyef.dailybudgettracker.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.natiqhaciyef.dailybudgettracker.R
import com.natiqhaciyef.dailybudgettracker.data.model.ExpenseCategory
import com.natiqhaciyef.dailybudgettracker.databinding.RecyclerCategoryRowBinding

class ExpenseCategoryAdapter(
    val mContext: Context,
    val list: MutableList<ExpenseCategory>,
    val totalPrice: Double
) : RecyclerView.Adapter<ExpenseCategoryAdapter.ExpensesHolder>() {

    inner class ExpensesHolder(val binding: RecyclerCategoryRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpensesHolder {
        val binding: RecyclerCategoryRowBinding =
            DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.recycler_category_row, parent, false)
        return ExpensesHolder(binding)
    }

    override fun onBindViewHolder(holder: ExpensesHolder, position: Int) {
        val view = holder.binding
        val expenseCategory = list[position]
        view.expenseCategory = expenseCategory
        view.categoryPercentage.text = "${(expenseCategory.price / totalPrice) * 100} %"

    }

    override fun getItemCount(): Int = list.size

}