package com.natiqhaciyef.dailybudgettracker.data.source

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.natiqhaciyef.dailybudgettracker.data.model.BudgetModel
import com.natiqhaciyef.dailybudgettracker.data.model.ExpenseCategory
import com.natiqhaciyef.dailybudgettracker.data.roomdb.AppDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AppDataSource(var dao: AppDao) {
    suspend fun getAllExpenseCategories(date: String): List<ExpenseCategory> = withContext(Dispatchers.IO){
        dao.getAllExpenseCategories(date)
    }

    suspend fun getBudgetModel(date: String): BudgetModel? = withContext(Dispatchers.IO){
        dao.getBudgetModel(date)
    }

    suspend fun insertCategory(expenseCategory: ExpenseCategory) = withContext(Dispatchers.IO){
        dao.insertCategory(expenseCategory)
    }

    suspend fun insertBudget(budgetModel: BudgetModel) = withContext(Dispatchers.IO){
        dao.insertBudget(budgetModel)
    }

    suspend fun deleteCategory(expenseCategory: ExpenseCategory) = withContext(Dispatchers.IO){
        dao.deleteCategory(expenseCategory)
    }

    suspend fun updateBudget(budgetModel: BudgetModel) = withContext(Dispatchers.IO){
        dao.updateBudget(budgetModel)
    }
}