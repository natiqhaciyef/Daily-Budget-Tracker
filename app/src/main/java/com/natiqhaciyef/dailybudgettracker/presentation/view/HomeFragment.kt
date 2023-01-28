package com.natiqhaciyef.dailybudgettracker.presentation.view

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.natiqhaciyef.dailybudgettracker.R
import com.natiqhaciyef.dailybudgettracker.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*
import org.eazegraph.lib.models.PieModel


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pieChart.addPieSlice(
            PieModel("Fun", 90.0f,Color.parseColor("#FFA726"))
        )
        binding.pieChart.addPieSlice(
            PieModel("Investment", 10.0f,Color.parseColor("#429145"))
        )

        binding.pieChart.startAnimation()
    }
}