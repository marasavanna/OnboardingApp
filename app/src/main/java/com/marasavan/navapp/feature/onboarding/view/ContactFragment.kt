package com.marasavan.navapp.feature.onboarding.view

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.Observable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marasavan.navapp.feature.onboarding.viewModel.ContactViewModel
import com.marasavan.navapp.R
import com.marasavan.navapp.databinding.FragmentContactBinding

class ContactFragment : Fragment() {

    lateinit var binding: FragmentContactBinding
    lateinit var viewModel: ContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ContactViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_contact, container, false)
        binding.viewModel = this.viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.email.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                binding.email.error = null
            }
        })

        viewModel.phone.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                binding.phone.error = null
            }
        })
    }


    fun checkFields(): Boolean {
        var isCorrect = true
        if (viewModel.email.get().isNullOrEmpty()) {
            binding.email.error = getString(R.string.editText_error)
            isCorrect =  false
        }
        if (viewModel.phone.get().isNullOrEmpty()) {
            binding.phone.error = getString(R.string.editText_error)
            isCorrect =  false
        }
        return isCorrect
    }
}