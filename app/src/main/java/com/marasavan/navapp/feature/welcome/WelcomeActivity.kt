package com.marasavan.navapp.feature.welcome

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.marasavan.navapp.R
import com.marasavan.navapp.SharedPreferenceManager
import com.marasavan.navapp.SharedPreferenceManager.Companion.USERNAME
import com.marasavan.navapp.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding
    private var sharedPreferenceManager: SharedPreferenceManager? = null

    companion object {
        fun getStartIntent(context: Context): Intent = Intent(context, WelcomeActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferenceManager = SharedPreferenceManager(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_welcome)
        binding.welcome.text = getString(R.string.welcome, sharedPreferenceManager?.getString(USERNAME, ""))
    }
}
