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
import com.natiqhaciyef.dailybudgettracker.databinding.FragmentHomeBinding
import com.natiqhaciyef.dailybudgettracker.presentation.adapter.ExpenseCategoryAdapter
import com.natiqhaciyef.dailybudgettracker.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import org.eazegraph.lib.models.PieModel

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: ExpenseCategoryAdapter
    private val viewModel: HomeViewModel by viewModels()

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

        binding.addCategoryButton.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.addDetailsFragment)
        }
        binding.pieChart.startAnimation()
    }

    private fun observeLiveData() {
        viewModel.liveBudgetModel.observe(viewLifecycleOwner) {
            it.data?.let { budgetModel ->
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
}