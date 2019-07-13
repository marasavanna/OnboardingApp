package com.marasavan.navapp.feature.onboarding.view

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.Observable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marasavan.navapp.R
import com.marasavan.navapp.SharedPreferenceManager
import com.marasavan.navapp.SharedPreferenceManager.Companion.USERNAME
import com.marasavan.navapp.feature.onboarding.viewModel.UserInfoViewModel
import com.marasavan.navapp.databinding.FragmentUserInfoBinding

class UserInfoFragment : Fragment() {

    lateinit var binding: FragmentUserInfoBinding
    lateinit var viewModel: UserInfoViewModel
    private var sharedPreferenceManager : SharedPreferenceManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(UserInfoViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_info, container, false)
        binding.viewModel = this.viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        sharedPreferenceManager = SharedPreferenceManager(requireContext())
        with(viewModel) {
            password.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    binding.password.error = null
                }
            })
            username.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    binding.username.error = null
                }
            })
            confirm.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    binding.confirm.error = null
                }
            })
        }
    }


    fun checkFields(): Boolean {
        var isCorrect = true
        with(viewModel) {
            if (username.get().isNullOrEmpty()) {
                binding.username.error = getString(R.string.editText_error)
                isCorrect = false
            } else {
                sharedPreferenceManager?.putString(USERNAME, username.get().toString())
            }
            if (password.get().isNullOrEmpty()) {
                binding.password.error = getString(R.string.editText_error)
                isCorrect = false
            }
            if (confirm.get().isNullOrEmpty()) {
                binding.confirm.error = getString(R.string.editText_error)
                isCorrect = false
            }
            if (!confirm.get().equals(password.get())) {
                binding.confirm.error = getString(R.string.password_error)
                isCorrect = false
            }
        }
        return isCorrect
    }
}