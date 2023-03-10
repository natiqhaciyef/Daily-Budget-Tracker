package com.natiqhaciyef.dailybudgettracker.data.roomdb

import androidx.room.*
import com.natiqhaciyef.dailybudgettracker.data.model.BudgetModel
import com.natiqhaciyef.dailybudgettracker.data.model.ExpenseCategory

@Dao
interface AppDao {

    @Query("SELECT * FROM ExpenseCategory WHERE date = :date")
    suspend fun getAllExpenseCategories(date: String): List<ExpenseCategory>

    @Query("SELECT * FROM BudgetModel WHERE date = :date")
    suspend fun getBudgetModel(date: String): BudgetModel?

    @Insert
    suspend fun insertCategory(expenseCategory: ExpenseCategory)

    @Insert
    suspend fun insertBudget(budgetModel: BudgetModel)

    @Delete
    suspend fun deleteCategory(expenseCategory: ExpenseCategory)

    @Update
    suspend fun updateBudget(budgetModel: BudgetModel)

}