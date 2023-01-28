package com.natiqhaciyef.dailybudgettracker.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.natiqhaciyef.dailybudgettracker.data.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddDetailsViewModel @Inject constructor(
    val repo: AppRepository
): ViewModel() {


}