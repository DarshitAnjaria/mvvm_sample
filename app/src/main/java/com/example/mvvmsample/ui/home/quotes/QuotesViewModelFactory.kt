package com.example.mvvmsample.ui.home.quotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmsample.data.repositories.QuoteRepository
import com.example.mvvmsample.data.repositories.UserRepository

@Suppress("UNCHECKED_CAST")
class QuotesViewModelFactory(
    private val repository: QuoteRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return QuotesViewModel(repository) as T
    }
}