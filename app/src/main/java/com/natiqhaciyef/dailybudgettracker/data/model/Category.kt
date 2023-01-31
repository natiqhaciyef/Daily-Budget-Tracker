package com.natiqhaciyef.dailybudgettracker.data.model

import androidx.room.Entity


@Entity
data class Category(
    var image: Int,
    var type: ExpensesType,
    var color: String
)