package com.natiqhaciyef.dailybudgettracker.data.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.natiqhaciyef.dailybudgettracker.data.model.BudgetModel
import com.natiqhaciyef.dailybudgettracker.data.model.ExpenseCategory

@Database(entities = [ExpenseCategory::class, BudgetModel::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun getDao(): AppDao
}