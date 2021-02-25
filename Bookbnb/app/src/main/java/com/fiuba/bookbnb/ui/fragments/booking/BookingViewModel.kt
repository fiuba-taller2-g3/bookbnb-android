package com.fiuba.bookbnb.ui.fragments.booking

import com.fiuba.bookbnb.domain.misc.MsgResponse
import com.fiuba.bookbnb.networking.NetworkViewModel
import retrofit2.Call
import retrofit2.Response

class BookingViewModel : NetworkViewModel<MsgResponse>() {

    override fun onSuccessful(response: Response<MsgResponse>) {
        TODO("Not yet implemented")
    }

    override fun onFailure(response: Response<MsgResponse>) {
        TODO("Not yet implemented")
    }

    override fun execute(call: Call<MsgResponse>) {
        TODO("Not yet implemented")
    }

}