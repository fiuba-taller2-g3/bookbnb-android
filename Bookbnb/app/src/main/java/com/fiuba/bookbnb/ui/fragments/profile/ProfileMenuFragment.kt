package com.fiuba.bookbnb.ui.fragments.profile

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.fiuba.bookbnb.BuildConfig
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.ui.fragments.BaseFragment
import com.fiuba.bookbnb.ui.fragments.footerbar.FooterBarButtons
import com.fiuba.bookbnb.ui.fragments.profile.options.*
import com.fiuba.bookbnb.ui.navigation.NavigationManager
import com.fiuba.bookbnb.user.UserManager
import kotlinx.android.synthetic.main.bookbnb_profile_fragment.*

class ProfileMenuFragment : BaseFragment(R.layout.bookbnb_profile_fragment) {

    override val shouldShowToolbar: Boolean
        get() = false

    override val shouldShowFooterBarMenu: Boolean
        get() = true

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        UserManager.userLoggedInLiveData.observe(viewLifecycleOwner) {
            profile_name.text = it.userName
        }
        version.text = "${getString(R.string.view_profile_version_text)}: ${BuildConfig.VERSION_NAME}"

        view_profile.setOnClickListener {
            NavigationManager.moveForward(ProfileMenuFragmentDirections.actionProfileMenuFragmentToViewProfileFragment())
        }

        loadMenu()
    }

    private fun loadMenu() {
        with(profile_menu_container) {
            /* host */
            addView(ProfileTitleMenuSection(requireContext(), R.string.view_profile_host_title_text))
            addView(ProfilePublicStays(requireContext(), R.string.view_profile_publish_text, R.drawable.ic_public_stays))
            addView(ProfileMyPosts(requireContext(), R.string.view_profile_my_posts_text, R.drawable.ic_public_stays))
            addView(ProfileMyBookings(requireContext(), R.string.view_profile_my_bookings_text, R.drawable.ic_public_stays))
            /* Account config */
            addView(ProfileTitleMenuSection(requireContext(), R.string.view_profile_account_config_title_text))
            // TODO: Hay que confirmar si el usuario logueado con Facebook puede editar la información personal o registar los campos que faltan
            addView(ProfileAccountInfoOption(requireContext(), R.string.view_profile_account_info_text, R.drawable.ic_accountinfo))
            addView(ProfileLogoutOption(requireContext(), R.string.view_profile_logout_text, R.drawable.ic_logout))
        }
    }

    override fun onResume() {
        super.onResume()
        sharedViewModel.setOption(FooterBarButtons.PROFILE)
    }
}