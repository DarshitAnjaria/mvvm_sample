package com.example.mvvmsample.ui.home.quotes

import androidx.lifecycle.ViewModel
import com.example.mvvmsample.data.repositories.QuoteRepository
import com.example.mvvmsample.util.lazyDeferred

class QuotesViewModel(
    repository: QuoteRepository
) : ViewModel() {

    val quotes by lazyDeferred {
        repository.getQuotes()
    }

}
