package com.natiqhaciyef.dailybudgettracker.presentation.view

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.natiqhaciyef.dailybudgettracker.R
import com.natiqhaciyef.dailybudgettracker.data.model.ExpenseCategory
import com.natiqhaciyef.dailybudgettracker.data.model.ExpensesType
import com.natiqhaciyef.dailybudgettracker.databinding.FragmentHomeBinding
import com.natiqhaciyef.dailybudgettracker.presentation.adapter.ExpenseCategoryAdapter
import com.natiqhaciyef.dailybudgettracker.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import org.eazegraph.lib.models.PieModel
import java.util.*

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: ExpenseCategoryAdapter
    private var totalPrice = 0.0
    private val viewModel: HomeViewModel by viewModels()
    private var categoryList = mutableListOf<ExpenseCategory>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLiveData()
        pieChart()

        binding.addCategoryButton.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.addDetailsFragment)
        }

//        categoryList.add(
//            ExpenseCategory(id = 1, image = R.drawable.food, type = ExpensesType.Food,
//                price = 25.3, date = viewModel.changeCalendar(Calendar.getInstance()), color = "#F16800")
//        )
//        categoryList.add(
//            ExpenseCategory(id = 2, image = R.drawable.`fun`, type = ExpensesType.Fun,
//                price = 10.4, date = viewModel.changeCalendar(Calendar.getInstance()), color = "#429145"),
//        )

        for (s in categoryList){
            totalPrice += s.price
        }
        loadData(categoryList)
    }

    private fun observeLiveData() {
        viewModel.liveBudgetModel.observe(viewLifecycleOwner) {
            it.data?.let { budgetModel ->
                totalPrice = budgetModel.totalPrice
                adapter =
                    ExpenseCategoryAdapter(
                        requireContext(),
                        mutableListOf(),
                        budgetModel.totalPrice
                    )
                binding.categoryRecyclerView.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                binding.categoryRecyclerView.adapter = adapter
            }
        }
    }

    fun pieChart() {
        viewModel.liveExpensesCategory.observe(viewLifecycleOwner) {
            it.data?.let { categories ->
                categoryList = categories.toMutableList()
            }
        }
    }

    fun loadData(list: MutableList<ExpenseCategory>) {
        for (element in list) {
            pieChart.addPieSlice(
                PieModel(
                    element.type.name.lowercase(),
                    ((element.price / totalPrice) * 100).toFloat(),
                    Color.parseColor(element.color)
                )
            )
        }
        binding.pieChart.startAnimation()
    }
}