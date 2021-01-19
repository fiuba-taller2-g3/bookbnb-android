package com.fiuba.bookbnb.ui.fragments.home

import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.domain.publish.PublishData
import com.fiuba.bookbnb.forms.FormInputData
import com.fiuba.bookbnb.forms.FormInputType
import com.fiuba.bookbnb.networking.NetworkModule
import com.fiuba.bookbnb.ui.fragments.form.FormWithNetworkStatusFragment
import com.fiuba.bookbnb.user.UserManager
import retrofit2.Call

class SearchFragment : FormWithNetworkStatusFragment<SearchViewModel, List<PublishData>>() {

    override val isNetworkRequired: Boolean
        get() = true

    override fun getTitleTextRes(): Int = R.string.search_title
    override fun getSubtitleTextRes(): Int = R.string.search_subtitle
    override fun getButtonTextRes(): Int = R.string.search_button

    override fun getInputList(): List<FormInputData> {
        return listOf(
            FormInputData(FormInputType.STAY_TYPE_SEARCH),
            FormInputData(FormInputType.CITY),
            FormInputData(FormInputType.START_DATE),
            FormInputData(FormInputType.END_DATE),
            FormInputData(FormInputType.MIN_PRICE),
            FormInputData(FormInputType.MAX_PRICE)
        )
    }

    private fun getPrice(formInputType: FormInputType) : String? {
        val price = formViewModel.getContentFromItem(formInputType)
        return if (price.isNotEmpty()) price else null
    }

    override fun getViewModelClass(): Class<SearchViewModel> = SearchViewModel::class.java
    override fun call(): Call<List<PublishData>> = NetworkModule.buildRetrofitClient().getPosts(
        formViewModel.getContentFromItem(FormInputType.STAY_TYPE_SEARCH),
        getPrice(FormInputType.MIN_PRICE),
        getPrice(FormInputType.MAX_PRICE),
        formViewModel.getContentFromItem(FormInputType.START_DATE),
        formViewModel.getContentFromItem(FormInputType.END_DATE),
        UserManager.getUserInfo().getUserId(),
        UserManager.getUserInfo().getToken())

}