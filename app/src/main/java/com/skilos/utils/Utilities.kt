package com.skilos.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import timber.log.Timber

// To add a fragment
fun FragmentActivity.addFragment(
    fragment: Fragment,
    layoutId: Int = android.R.id.content,
    addToBackStack: Boolean = false,
    enterTransition: Int = 0,
    exitTransition: Int = 0,
    popEnterTransition: Int = 0,
    popExitTransition: Int = 0
) {
    fragmentTransaction(
        fragment,
        FragmentTransactionType.ADD,
        layoutId,
        addToBackStack,
        enterTransition,
        exitTransition,
        popEnterTransition,
        popExitTransition
    )
}

private fun FragmentActivity.fragmentTransaction(
    fragment: Fragment,
    fragmentTransactionType: FragmentTransactionType,
    containerId: Int,
    addToBackStack: Boolean,
    enterTransition: Int = 0,
    exitTransition: Int = 0,
    popEnterTransition: Int = 0,
    popExitTransition: Int = 0
) {
    val transaction = supportFragmentManager.beginTransaction()
    transaction.setCustomAnimations(
        enterTransition,
        exitTransition,
        popEnterTransition,
        popExitTransition
    )
    when (fragmentTransactionType) {
        FragmentTransactionType.ADD -> transaction.add(containerId, fragment)
        FragmentTransactionType.REPLACE -> transaction.replace(containerId, fragment)
    }

    // For adding the transaction to backstack
    if (addToBackStack) {
        transaction.addToBackStack(fragment.tag)
    }
    transaction.commitAllowingStateLoss()
}

fun Fragment.popStackIfPossible(): Boolean {
    val childCount = parentFragment?.childFragmentManager?.backStackEntryCount
    return if (childCount != null && childCount > 0) {
        parentFragment?.childFragmentManager?.popBackStack()
        true
    } else {
        val stackCount = requireActivity().supportFragmentManager.backStackEntryCount
        if (stackCount > 0) {
            if (stackCount == 1) {
                activity?.finish()
            } else {
                try {
                    activity?.supportFragmentManager?.popBackStack()
                } catch (e: IllegalStateException) {
                    Timber.e(e)
                }
            }
            true
        } else false
    }
}

enum class FragmentTransactionType {
    ADD, REPLACE
}