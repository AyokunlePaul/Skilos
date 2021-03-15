package com.skilos.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<V> : Fragment() where V : ViewBinding {

    private var _binding: V? = null
    protected val binding: V by lazy { _binding!! }

    abstract fun getLayoutBinding(container: ViewGroup?): V

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getLayoutBinding(container)
        return _binding?.root
    }

    protected fun navigate(@IdRes id: Int) {
        findNavController().navigate(id)
    }

    protected fun navigate(direction: NavDirections) {
        findNavController().navigate(direction)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}