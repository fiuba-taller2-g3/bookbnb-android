package com.fiuba.bookbnb.ui.fragments.profile

import android.util.Log
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.domain.misc.MsgResponse
import com.fiuba.bookbnb.domain.user.UserData
import com.fiuba.bookbnb.networking.NetworkModule
import com.fiuba.bookbnb.ui.fragments.form.FormViewModel
import com.fiuba.bookbnb.ui.navigation.NavigationManager
import com.fiuba.bookbnb.user.UserManager
import retrofit2.Response

class ProfileEditViewModel : FormViewModel(), FormViewModel.CallResponse<MsgResponse> {

    fun update(request: UserData) {
        Log.i(TAG, "Updating profile...")
        executeCallback(request, this) {
            with(UserManager.getUserInfo()) { NetworkModule.buildRetrofitClient().updateUser(getUserId(), getToken(), request) }
        }
    }

    override fun onSuccessful(response: Response<MsgResponse>) {
        msgResponse = response.body()?.msg.toString()
        Log.i(TAG, "Profile updated successfully")
        NavigationManager.moveForwardWithPopUpTo(ProfileEditFragmentDirections.actionProfileEditFragmentToConfirmationFragment(
            R.drawable.ic_checked, msgResponse, R.string.edit_info_profile_confirmed_description), R.id.profileMenuFragment)
    }

    override fun onFailure(response: Response<MsgResponse>) {
        msgResponse = "El correo ingresado ya existe, introduzca un correo diferente."
        Log.e(TAG, "Profile update failed: $msgResponse")
    }

    companion object {
        private const val TAG = "EDIT_PROFILE"
    }

}