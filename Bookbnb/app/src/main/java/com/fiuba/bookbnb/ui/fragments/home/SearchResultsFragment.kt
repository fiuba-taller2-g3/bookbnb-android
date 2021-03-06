package com.fiuba.bookbnb.ui.fragments.home

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.ui.fragments.BaseFragment
import kotlinx.android.synthetic.main.bookbnb_search_results_fragment.*

class SearchResultsFragment : BaseFragment(R.layout.bookbnb_search_results_fragment) {

    private val searchResultsViewModel by activityViewModels<SearchResultsViewModel>()

    override fun shouldClearInputsWhenBackPressed(): Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        search_results_container.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            searchResultsViewModel.getResults()?.let { results ->
                adapter = StayPostsAdapter(results)
                isVisible = results.isNotEmpty()
            }
        }
        empty_results_text.isVisible = !search_results_container.isVisible
    }
}