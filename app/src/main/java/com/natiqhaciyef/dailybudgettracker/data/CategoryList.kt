package com.natiqhaciyef.dailybudgettracker.data

import com.natiqhaciyef.dailybudgettracker.R
import com.natiqhaciyef.dailybudgettracker.data.model.Category
import com.natiqhaciyef.dailybudgettracker.data.model.ExpenseCategory
import com.natiqhaciyef.dailybudgettracker.data.model.ExpensesType
import com.natiqhaciyef.dailybudgettracker.data.model.ExpensesType.*

object CategoryList {
    val categoryList = mutableListOf(
        Category(R.drawable.food, Food, "${R.color.orange}"),
        Category(R.drawable.transport, Transport, "${R.color.purple}"),
        Category(R.drawable.rent, Rental, "${R.color.blue}"),
        Category(R.drawable.bill, Bill, "${R.color.black}"),
        Category(R.drawable.home_maintenance, HomeMaintenance, "${R.color.brown}"),
        Category(R.drawable.vehicle_maintenance, VehicleMaintenance, "${R.color.dark_blue}"),
        Category(R.drawable.medical, Medical, "${R.color.pink}"),
        Category(R.drawable.insurance, Insurance, "${R.color.dark_pink}"),
        Category(R.drawable.education, Education, "${R.color.yellow}"),
        Category(R.drawable.personal, Personal, "${R.color.dark_yellow}"),
        Category(R.drawable.pets, Pets, "${R.color.dark_purple}"),
        Category(R.drawable.service, Services, "${R.color.dark_red}"),
        Category(R.drawable.`fun`, Fun, "${R.color.light_orange}"),
        Category(R.drawable.sport, Sport, "${R.color.light_blue}"),
        Category(R.drawable.investment, Investment, "${R.color.dark_green}"),
        Category(R.drawable.debt, Debt, "${R.color.light_green}"),
        Category(R.drawable.salary, Salary, "${R.color.green}"),
        Category(R.drawable.other_income, Income, "${R.color.red}"),
    )

    fun getAllName(): MutableList<ExpensesType>{
        var list = mutableListOf<ExpensesType>()
        for (element in categoryList){
            list.add(element.type)
        }
        return list
    }

    fun findCategoryImage(type: ExpensesType): String{
        var color = ""
        for (element in categoryList){
            if (element.type.name == type.name)
                color = element.color
        }
        return color
    }
}