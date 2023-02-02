package com.natiqhaciyef.dailybudgettracker.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.natiqhaciyef.dailybudgettracker.data.model.BudgetModel
import com.natiqhaciyef.dailybudgettracker.data.model.ExpenseCategory
import com.natiqhaciyef.dailybudgettracker.data.model.Resource
import com.natiqhaciyef.dailybudgettracker.data.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val repo: AppRepository
) : ViewModel() {
    var liveBudgetModel = MutableLiveData<Resource<BudgetModel>>()
    var liveExpensesCategory = MutableLiveData<Resource<List<ExpenseCategory>>>()

    init {
        getBudgetModel(date = calendarFormatter(Calendar.getInstance()))
        getAllCategories(calendarFormatter(Calendar.getInstance()))
    }

    fun getBudgetModel(date: String) {
        viewModelScope.launch(Dispatchers.Main) {
            val budget = repo.getBudgetModel(date)
            if (budget != null) {
                liveBudgetModel.value = Resource.success(budget)
            } else {
                liveBudgetModel.value = Resource.error("Empty budget", null)
                insertBudget(date)
            }
        }
    }

    fun calendarFormatter(calendar: Calendar): String {
        val format = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(format, Locale.UK)
        return sdf.format(calendar.time)
    }

    fun getAllCategories(date: String) {
        viewModelScope.launch(Dispatchers.Main) {
            if (repo.getAllExpenseCategories(date).isNotEmpty())
                liveExpensesCategory.value = (Resource.success(repo.getAllExpenseCategories(date)))
            else
                liveExpensesCategory.value = (Resource.success(repo.getAllExpenseCategories(date)))
        }
    }

    fun insertCategory(expenseCategory: ExpenseCategory) {
        viewModelScope.launch(Dispatchers.Main) {
            repo.insertCategory(expenseCategory)
        }
    }

    fun insertBudget(date: String) {
        viewModelScope.launch(Dispatchers.Main) {
            repo.insertBudget(BudgetModel(0, totalPrice = 0.0, date))
        }
    }

    fun updateBudget(budgetModel: BudgetModel){
        viewModelScope.launch(Dispatchers.Main) {
            repo.updateBudget(budgetModel)
        }
    }
}



