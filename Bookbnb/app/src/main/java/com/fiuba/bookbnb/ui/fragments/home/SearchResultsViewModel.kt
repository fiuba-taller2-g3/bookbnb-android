package com.fiuba.bookbnb.ui.fragments.home

import androidx.lifecycle.ViewModel
import com.fiuba.bookbnb.domain.publish.PublishData

class SearchResultsViewModel : ViewModel() {

    private var searchResults : List<PublishData>? = null

    fun setResults(results: List<PublishData>) {
        searchResults = results
    }

    fun getResults() = searchResults

}