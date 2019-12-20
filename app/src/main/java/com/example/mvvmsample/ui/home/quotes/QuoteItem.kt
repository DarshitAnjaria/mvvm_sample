package com.example.mvvmsample.ui.home.quotes

import com.example.mvvmsample.R
import com.example.mvvmsample.data.db.entities.Quote
import com.example.mvvmsample.databinding.RowQuotesBinding
import com.xwray.groupie.databinding.BindableItem

class QuoteItem(
    private val quote: Quote
) : BindableItem<RowQuotesBinding>() {

    override fun getLayout() = R.layout.row_quotes

    override fun bind(viewBinding: RowQuotesBinding, position: Int) {
        viewBinding.setQuote(quote)
    }

}