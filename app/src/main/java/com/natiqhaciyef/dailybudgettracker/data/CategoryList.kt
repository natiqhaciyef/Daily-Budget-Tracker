package com.natiqhaciyef.dailybudgettracker.data

import com.natiqhaciyef.dailybudgettracker.R
import com.natiqhaciyef.dailybudgettracker.data.model.Category
import com.natiqhaciyef.dailybudgettracker.data.model.ExpensesType
import com.natiqhaciyef.dailybudgettracker.data.model.ExpensesType.*

object CategoryList {
    val categoryList = mutableListOf(
        Category(R.drawable.food, Food, "#F16800"),
        Category(R.drawable.transport, Transport, "#5E35B1"),
        Category(R.drawable.rent, Rental,"#3C148F"),
        Category(R.drawable.bill, Bill, "#A173FF"),
        Category(R.drawable.home_maintenance, HomeMaintenance, "#0088FF"),
        Category(R.drawable.vehicle_maintenance, VehicleMaintenance, "#5FB2FB"),
        Category(R.drawable.medical, Medical, "#004785"),
        Category(R.drawable.insurance, Insurance, "#873D04"),
        Category(R.drawable.education, Education, "#FFE054"),
        Category(R.drawable.personal, Personal, "#987C00"),
        Category(R.drawable.pets, Pets, "#FF0000"),
        Category(R.drawable.service, Services, "#F84D4D"),
        Category(R.drawable.`fun`, Fun, "#A521C8"),
        Category(R.drawable.sport, Sport,"#2E0255"),
        Category(R.drawable.investment, Investment, "#7CB342"),
        Category(R.drawable.debt, Debt, "#691580"),
        Category(R.drawable.salary, Salary,"#429145"),
        Category(R.drawable.other_income, Income, "#286A2B"),
    )

    fun getAllName(): MutableList<ExpensesType>{
        var list = mutableListOf<ExpensesType>()
        for (element in categoryList){
            list.add(element.type)
        }
        return list
    }

    fun findImageByCategory(type: ExpensesType): Int{
        var image = 0
        for (element in categoryList){
            if (element.type.name == type.name)
                image = element.image
        }
        return image
    }
}