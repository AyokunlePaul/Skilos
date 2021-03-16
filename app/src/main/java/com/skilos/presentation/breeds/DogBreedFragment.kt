package com.skilos.presentation.breeds

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.skilos.R
import com.skilos.adapters.DogBreedAdapter
import com.skilos.core.BaseFragment
import com.skilos.databinding.FragmentDogBreedBinding
import com.skilos.models.DogDetail
import com.skilos.presentation.MainActivity
import com.skilos.presentation.breeddetail.DogBreedDetailFragment
import com.skilos.utils.DogDetailClick
import com.skilos.utils.RecyclerInsetsDecoration
import com.skilos.utils.addFragment
import com.skilos.utils.popStackIfPossible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class DogBreedFragment : BaseFragment<FragmentDogBreedBinding>(), DogDetailClick {

    private val viewModel by viewModels<DogBreedFragmentViewModel>()
    private val dogBreedAdapter by lazy { DogBreedAdapter(this) }

    override fun getLayoutBinding(container: ViewGroup?): FragmentDogBreedBinding {
        return FragmentDogBreedBinding.inflate(layoutInflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.rvDogImages.apply {
            adapter = dogBreedAdapter
            layoutManager = object : GridLayoutManager(requireContext(), 3) {
                override fun supportsPredictiveItemAnimations(): Boolean = false
            }
            if (itemDecorationCount == 0) addItemDecoration(
                RecyclerInsetsDecoration(20, 20)
            )
        }
        binding.tvToolbar.text = getString(R.string.app_name)
        with(viewModel) {
            lifecycleScope.launchWhenResumed {
                dogBreeds.collect {
                    when (it) {
                        is DogBreedFragmentViewModel.DogBreedResponse.Loading -> {
                        }
                        is DogBreedFragmentViewModel.DogBreedResponse.Failure -> {
                        }
                        is DogBreedFragmentViewModel.DogBreedResponse.Success -> {
                            dogBreedAdapter.updateBreeds(it.data)
                        }
                    }
                }
            }
            getDogBreeds()
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    popStackIfPossible()
                }
            }
        )
    }

    override fun invoke(data: DogDetail) {
        (requireActivity() as MainActivity).addFragment(
            DogBreedDetailFragment.new(data),
            addToBackStack = true
        )
    }
}