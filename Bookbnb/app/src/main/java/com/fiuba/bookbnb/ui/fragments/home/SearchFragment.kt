package com.fiuba.bookbnb.ui.fragments.home

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.repository.LoadingStatus
import com.fiuba.bookbnb.ui.fragments.BaseFragment
import kotlinx.android.synthetic.main.bookbnb_search_item.*

class SearchFragment : BaseFragment(R.layout.bookbnb_search_fragment) {

    private val searchViewModel : SearchViewModel by activityViewModels()

    override val isNetworkRequired: Boolean
        get() = true

    override val shouldShowToolbar: Boolean
        get() = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkStatus()
    }

    private fun checkStatus() {
        searchViewModel.statusLiveData.observe(viewLifecycleOwner) { status ->
            when(status) {
                LoadingStatus.LOADING -> setSearchStatus(true)
                else -> setSearchStatus(false)
            }
        }
    }

    private fun setSearchStatus(isLoading: Boolean) {
        val editTextColor = if (isLoading) R.color.colorTextInputFieldDisabled else R.color.colorWhite
        search_field.setTextColor(ContextCompat.getColor(requireContext(), editTextColor))
        search_button.isEnabled = !isLoading
    }

}