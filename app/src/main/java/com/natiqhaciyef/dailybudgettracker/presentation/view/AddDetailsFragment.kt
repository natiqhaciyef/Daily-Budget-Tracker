package com.natiqhaciyef.dailybudgettracker.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.natiqhaciyef.dailybudgettracker.R
import com.natiqhaciyef.dailybudgettracker.data.CategoryList
import com.natiqhaciyef.dailybudgettracker.databinding.FragmentAddDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddDetailsFragment : Fragment() {
    private lateinit var binding: FragmentAddDetailsBinding
    private var category = "Non-selected"
    private var categoryList = CategoryList.categoryList
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val categoryAdapter = ArrayAdapter(requireContext(),R.layout.dropdown_item,categoryList)
        binding.categorySelectPanel.setAdapter(categoryAdapter)
        binding.categorySelectPanel.setOnItemClickListener { adapterView, _, p, _ ->
            categoryList.forEach {
                if (it.name.lowercase() == adapterView.getItemAtPosition(p).toString().lowercase())
                    category = it.name
            }
        }
    }
}