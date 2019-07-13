package com.marasavan.navapp.feature.onboarding.viewModel

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField

class UserInfoViewModel: ViewModel() {
    val name  = ObservableField<String>()
    val username  = ObservableField<String>()
    val password  = ObservableField<String>()
    val confirm  = ObservableField<String>()
}