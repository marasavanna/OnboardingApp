package com.marasavan.navapp.feature.onboarding.viewModel

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField

class ContactViewModel: ViewModel() {
    val email  = ObservableField<String>()
    val phone  = ObservableField<String>()
    val web  = ObservableField<String>()
}