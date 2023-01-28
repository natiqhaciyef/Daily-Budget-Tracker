package com.natiqhaciyef.dailybudgettracker.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BudgetModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var totalPrice: String,
    var date: String
)
