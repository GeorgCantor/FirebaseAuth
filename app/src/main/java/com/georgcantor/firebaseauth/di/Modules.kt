package com.georgcantor.firebaseauth.di

import com.georgcantor.firebaseauth.ui.MainViewModel
import com.georgcantor.firebaseauth.ui.auth.code.AuthCodeViewModel
import com.georgcantor.firebaseauth.ui.auth.phone.AuthPhoneViewModel
import com.georgcantor.firebaseauth.util.PreferenceManager
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val preferenceModule = module {
    single { PreferenceManager(androidApplication().applicationContext) }
}

val viewModelModule = module(override = true) {
    viewModel {
        MainViewModel()
    }
    viewModel {
        AuthPhoneViewModel()
    }
    viewModel {
        AuthCodeViewModel()
    }
}