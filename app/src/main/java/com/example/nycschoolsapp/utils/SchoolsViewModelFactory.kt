package com.example.nycschoolsapp.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nycschoolsapp.rest.SchoolsRepository
import com.example.nycschoolsapp.viewModel.SchoolsViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class SchoolsViewModelFactory @Inject constructor(
    private val schoolsRepository: SchoolsRepository,
    private val ioDispatcher: CoroutineDispatcher
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SchoolsViewModel(schoolsRepository,ioDispatcher) as T
    }

}