package com.natiqhaciyef.dailybudgettracker.data.repository

import com.natiqhaciyef.dailybudgettracker.data.model.BudgetModel
import com.natiqhaciyef.dailybudgettracker.data.model.ExpenseCategory
import com.natiqhaciyef.dailybudgettracker.data.roomdb.AppDao
import com.natiqhaciyef.dailybudgettracker.data.source.AppDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class AppRepository(var ds: AppDataSource) {
    suspend fun getAllExpenseCategories(date: String): List<ExpenseCategory> = ds.getAllExpenseCategories(date)

    suspend fun getBudgetModel(date: String): BudgetModel? = ds.getBudgetModel(date)

    suspend fun insertCategory(expenseCategory: ExpenseCategory) = ds.insertCategory(expenseCategory)

    suspend fun insertBudget(budgetModel: BudgetModel) = ds.insertBudget(budgetModel)

    suspend fun deleteCategory(expenseCategory: ExpenseCategory) = ds.deleteCategory(expenseCategory)

    suspend fun updateBudget(budgetModel: BudgetModel) = ds.updateBudget(budgetModel)
}