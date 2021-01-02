package com.catra.ibrewer.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

open class BaseViewModel : ViewModel() {
    open val mainContext: CoroutineContext by lazy { Dispatchers.Main }
}