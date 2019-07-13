package com.marasavan.navapp.feature.onboarding.adapter

import android.support.annotation.IntDef
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class OnboardingPagerAdapter(manager: FragmentManager) : FragmentStatePagerAdapter(manager) {

    private val fragments = ArrayList<Fragment>()

    @IntDef(value = [Position.USER_INFO, Position.SUBSCRIPTION, Position.CONTACT])
    annotation class Position {
        companion object {
            const val USER_INFO = 0
            const val SUBSCRIPTION = 1
            const val CONTACT = 2
        }
    }

    fun addFragment(fragment: Fragment) {
        fragments.add(fragment)
    }

    override fun getItem(position: Int) = fragments[position]

    override fun getCount() = fragments.size
}