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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentAddDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}