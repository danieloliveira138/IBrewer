package com.catra.ibrewer.ui

import androidx.fragment.app.Fragment
import com.catra.ibrewer.ui.main.MainProvider

open class BaseFragment : Fragment() {

    fun showLoading(hasShow: Boolean) {
        (activity as? MainProvider)?.loading(hasShow)
    }
}
