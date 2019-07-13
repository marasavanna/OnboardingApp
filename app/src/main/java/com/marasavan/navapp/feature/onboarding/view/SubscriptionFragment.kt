package com.marasavan.navapp.feature.onboarding.view
import android.app.AlertDialog
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marasavan.navapp.R
import com.marasavan.navapp.databinding.FragmentSubscribeBinding


class SubscriptionFragment : Fragment() {

    lateinit var binding: FragmentSubscribeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_subscribe, container, false)
        return binding.root
    }

    fun checkFields(): Boolean {
        if (!binding.accept.isChecked) {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle(getString(R.string.accept_terms))
                .setMessage(getString(R.string.accept_dialog_message))
                .setNeutralButton(getString(R.string.ok), null)
                .create()
                .show()
            return false
        }
        return true
    }
}