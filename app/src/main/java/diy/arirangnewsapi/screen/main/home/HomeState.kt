package diy.arirangnewsapi.screen.main.home

import androidx.annotation.StringRes

sealed class HomeState {

    object Uninitialized: HomeState()

    object Loading: HomeState()

    object Success: HomeState()

    object Error: HomeState()

}