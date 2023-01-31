package com.natiqhaciyef.dailybudgettracker.presentation.view

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.natiqhaciyef.dailybudgettracker.R
import com.natiqhaciyef.dailybudgettracker.data.CategoryList
import com.natiqhaciyef.dailybudgettracker.data.model.ExpenseCategory
import com.natiqhaciyef.dailybudgettracker.data.model.ExpensesType
import com.natiqhaciyef.dailybudgettracker.databinding.FragmentAddDetailsBinding
import com.natiqhaciyef.dailybudgettracker.databinding.FragmentHomeBinding
import com.natiqhaciyef.dailybudgettracker.presentation.adapter.ExpenseCategoryAdapter
import com.natiqhaciyef.dailybudgettracker.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_add_details.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.eazegraph.lib.models.PieModel
import java.util.*

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: ExpenseCategoryAdapter
    private var totalPrice = 0.0
    private var category = ExpensesType.Personal
    private var categories = CategoryList.getAllName()
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
            val bottomSheetDialog =
                BottomSheetDialog(requireActivity(), R.style.BottomSheetDialogTheme)
            val bindingBottomSheet = FragmentAddDetailsBinding.inflate(layoutInflater)
            bindingBottomSheet.saveButton.setOnClickListener {
                // save categories
                viewModel.insertCategory(
                    ExpenseCategory(
                    id = 0,
                    categoryImage = CategoryList.findCategoryImage(type = category).toInt(),
                    price = bindingBottomSheet.priceInputText.text.toString().toDouble(),
                    date = viewModel.changeCalendar(Calendar.getInstance()))
                )
                Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
            }
            bottomSheetDialog.setContentView(bindingBottomSheet.root)
            bottomSheetDialog.show()
            val categoryAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, categories)
            bindingBottomSheet.categorySelectPanel.setAdapter(categoryAdapter)
            bindingBottomSheet.categorySelectPanel.setOnItemClickListener { adapterView, _, p, _ ->
                categories.forEach {
                    if (it.name.lowercase() == adapterView.getItemAtPosition(p).toString()
                            .lowercase())
                        category = it
                }
            }
        }


        for (s in categoryList) {
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
                Log.e("JSJAS","$categoryList")
            }
        }
    }

    fun loadData(list: MutableList<ExpenseCategory>) {
        if (totalPrice != 0.0) {
            for (element in list) {
                var cate = element.findByImage(element.categoryImage)
                pieChart.addPieSlice(
                    PieModel(
                        cate.type.name.lowercase(),
                        ((element.price / totalPrice) * 100).toFloat(),
                        Color.parseColor(cate.color)
                    )
                )
            }
            binding.pieChart.startAnimation()
        } else {
            pieChart.addPieSlice(
                PieModel(
                    "Empty",
                    100f,
                    Color.GRAY
                )
            )
        }
    }
}