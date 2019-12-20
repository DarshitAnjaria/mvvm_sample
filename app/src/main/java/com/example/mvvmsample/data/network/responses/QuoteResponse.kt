package com.example.mvvmsample.data.network.responses

import com.example.mvvmsample.data.db.entities.Quote

data class QuoteResponse(
    val isSuccessful: Boolean,
    val quotes: List<Quote>
)