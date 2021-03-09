package com.fiuba.bookbnb.ui.fragments.profile

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.ui.fragments.BaseFragment
import kotlinx.android.synthetic.main.bookbnb_view_profile_fragment.*

class ViewProfileFragment : BaseFragment(R.layout.bookbnb_view_profile_fragment) {

    private val navArguments by navArgs<ViewProfileFragmentArgs>()
    private val userData by lazy { navArguments.userData }

    override fun shouldClearInputsWhenBackPressed(): Boolean = false

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        user_name.text = "${userData.name} ${userData.surname}"
    }

}