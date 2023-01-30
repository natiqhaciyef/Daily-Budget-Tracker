package com.natiqhaciyef.dailybudgettracker.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class ExpenseCategory(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var image: Int,
    var type: ExpensesType,
    var price: Double,
    var date: String,
    var color: String
)
