package com.marasavan.navapp.feature.onboarding.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.marasavan.navapp.R
import com.marasavan.navapp.databinding.ActivityMainBinding
import com.marasavan.navapp.feature.onboarding.adapter.OnboardingPagerAdapter
import com.marasavan.navapp.feature.onboarding.adapter.OnboardingPagerAdapter.Position.Companion.CONTACT
import com.marasavan.navapp.feature.onboarding.adapter.OnboardingPagerAdapter.Position.Companion.SUBSCRIPTION
import com.marasavan.navapp.feature.onboarding.adapter.OnboardingPagerAdapter.Position.Companion.USER_INFO
import com.marasavan.navapp.feature.welcome.WelcomeActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var selectedTab = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val pagerAdapter =
            OnboardingPagerAdapter(supportFragmentManager)
        pagerAdapter.addFragment(UserInfoFragment())
        pagerAdapter.addFragment(SubscriptionFragment())
        pagerAdapter.addFragment(ContactFragment())

        binding.onBoardingPager.adapter = pagerAdapter
        binding.onBoardingPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(position: Int) {

            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

            }

            override fun onPageSelected(position: Int) {
                selectedTab = position
                when (position) {
                    CONTACT -> {
                        if (!((binding.onBoardingPager.adapter as OnboardingPagerAdapter).getItem(SUBSCRIPTION) as SubscriptionFragment).checkFields()) {
                            binding.stepIndicator.currentStepPosition = selectedTab--
                            binding.onBoardingPager.currentItem = selectedTab--
                        }
                        binding.navigationButton.text = getText(R.string.finish)
                    }
                    SUBSCRIPTION -> {
                        if (!((binding.onBoardingPager.adapter as OnboardingPagerAdapter).getItem(USER_INFO) as UserInfoFragment).checkFields()) {
                            binding.stepIndicator.currentStepPosition = selectedTab--
                            binding.onBoardingPager.currentItem = selectedTab--
                        }
                        binding.navigationButton.text = getText(R.string.next)
                    }
                    else -> binding.navigationButton.text = getText(R.string.next)
                }
            }
        })
        binding.stepIndicator.setupWithViewPager(binding.onBoardingPager)
        binding.onBoardingPager.offscreenPageLimit = 3
        binding.navigationButton.setOnClickListener {
            when {
                (binding.onBoardingPager.adapter as OnboardingPagerAdapter).getItem(selectedTab) is ContactFragment -> {
                    if (((binding.onBoardingPager.adapter as OnboardingPagerAdapter).getItem(selectedTab) as ContactFragment).checkFields()) {
                        startActivity(WelcomeActivity.getStartIntent(this@MainActivity))
                    }
                }
                (binding.onBoardingPager.adapter as OnboardingPagerAdapter).getItem(selectedTab) is UserInfoFragment -> {
                    if (((binding.onBoardingPager.adapter as OnboardingPagerAdapter).getItem(selectedTab) as UserInfoFragment).checkFields()) {
                        binding.stepIndicator.currentStepPosition = selectedTab++
                        binding.onBoardingPager.currentItem = selectedTab++
                    }
                }
                else -> {
                    if (((binding.onBoardingPager.adapter as OnboardingPagerAdapter).getItem(selectedTab) as SubscriptionFragment).checkFields()) {
                        binding.stepIndicator.currentStepPosition = selectedTab++
                        binding.onBoardingPager.currentItem = selectedTab++
                    }
                }
            }
        }
    }

}
