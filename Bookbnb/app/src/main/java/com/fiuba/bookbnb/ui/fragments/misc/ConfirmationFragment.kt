package com.fiuba.bookbnb.ui.fragments.misc

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.navigation.fragment.navArgs
import com.fiuba.bookbnb.R
import com.fiuba.bookbnb.ui.fragments.BaseFragment
import com.fiuba.bookbnb.ui.navigation.NavigationManager
import kotlinx.android.synthetic.main.bookbnb_confirmation_fragment.*

class ConfirmationFragment : BaseFragment(R.layout.bookbnb_confirmation_fragment) {

    private val navArguments by navArgs<ConfirmationFragmentArgs>()
    private val img by lazy { navArguments.imgRes }
    private val title by lazy { navArguments.title }
    private val subtitle by lazy { navArguments.subtitleRes }

    override val shouldShowToolbar: Boolean
        get() = false


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        check_img.setImageResource(img)
        confirmation_title.text = title
        confirmation_subtitle.text = getString(subtitle)

        Handler().postDelayed({ NavigationManager.popBackStack() }, MILLISECONDS_WAIT)
    }

    companion object {
        private const val MILLISECONDS_WAIT : Long = 3000
    }
}