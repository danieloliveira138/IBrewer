package com.catra.ibrewer.ui.main

interface MainProvider {
    fun displayError(retry: () -> Unit)
    fun loading(hasShow: Boolean)
}