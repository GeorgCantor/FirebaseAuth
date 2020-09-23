package com.georgcantor.firebaseauth.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.georgcantor.firebaseauth.util.Constants.IS_LOGGED_IN
import com.georgcantor.firebaseauth.util.PreferenceManager

class MainViewModel(prefManager: PreferenceManager) : ViewModel() {

    val isLoggedIn = MutableLiveData<Boolean>()

    init {
        isLoggedIn.value = prefManager.getBoolean(IS_LOGGED_IN)
    }
}