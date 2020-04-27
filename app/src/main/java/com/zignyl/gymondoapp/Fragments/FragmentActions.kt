package com.zignyl.gymondoapp.Fragments

import android.os.Bundle

interface FragmentActions {
    fun replaceFragment(bundle: Bundle? = null, TAG: String)
    fun addFragment(bundle: Bundle? = null, TAG: String)
    fun removeFragment(TAG: String)
}