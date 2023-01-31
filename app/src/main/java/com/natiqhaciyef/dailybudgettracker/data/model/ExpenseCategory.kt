package com.natiqhaciyef.dailybudgettracker.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.natiqhaciyef.dailybudgettracker.data.CategoryList
import java.util.*

@Entity
data class ExpenseCategory(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var categoryImage: Int,
    var price: Double,
    var date: String,
){
    fun findByImage(image: Int): Category{
        var category = CategoryList.categoryList[0]
        for (element in CategoryList.categoryList){
            if (image == element.image)
                category = element
        }
        return category
    }
}
