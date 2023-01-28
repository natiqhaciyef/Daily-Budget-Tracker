package com.natiqhaciyef.dailybudgettracker.data.roomdb

import androidx.room.*
import com.natiqhaciyef.dailybudgettracker.data.model.BudgetModel
import com.natiqhaciyef.dailybudgettracker.data.model.ExpenseCategory

@Dao
interface AppDao {

    @Query("SELECT * FROM ExpenseCategory WHERE date = :date")
    fun getAllExpenseCategories(date: String)

    @Query("SELECT * FROM BudgetModel WHERE date = :date")
    fun getBudgetModel(date: String)

    @Insert
    fun insertCategory(expenseCategory: ExpenseCategory)

    @Insert
    fun insertBudget(budgetModel: BudgetModel)

    @Delete
    fun deleteCategory(expenseCategory: ExpenseCategory)

    @Update
    fun updateBudget(budgetModel: BudgetModel)

}