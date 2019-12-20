package com.example.mvvmsample.ui.home.quotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmsample.R
import com.example.mvvmsample.data.db.entities.Quote
import com.example.mvvmsample.util.Coroutines
import com.example.mvvmsample.util.Hide
import com.example.mvvmsample.util.Show
import com.example.mvvmsample.util.toast
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.quotes_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class QuotesFragment : Fragment(), KodeinAware {

    override val kodein by kodein()

    private val factory: QuotesViewModelFactory by instance()

    private lateinit var viewModel: QuotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.quotes_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(QuotesViewModel::class.java)
        bindUI()
    }

    private fun bindUI() = Coroutines.main {
        progress_quotes.Show()
        viewModel.quotes.await().observe(this, Observer {
            progress_quotes.Hide()
            initRecyclerView(it.toQuoteItem())
        })
    }

    private fun initRecyclerView(toQuoteItem: List<QuoteItem>) {

        val recycler_adapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(toQuoteItem)
        }

        recycler_quotes.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = recycler_adapter
        }
    }

    private fun List<Quote>.toQuoteItem(): List<QuoteItem> {
        return this.map {
            QuoteItem(it)
        }
    }

}
