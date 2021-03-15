package com.skilos.presentation.breeds

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.skilos.adapters.DogBreedAdapter
import com.skilos.core.BaseFragment
import com.skilos.databinding.FragmentDogBreedBinding
import com.skilos.models.DogDetail
import com.skilos.utils.DogDetailClick
import com.skilos.utils.RecyclerInsetsDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class DogBreedFragment : BaseFragment<FragmentDogBreedBinding>(), DogDetailClick {

    private val viewModel by viewModels<DogBreedFragmentViewModel>()
    private val dogBreedAdapter = DogBreedAdapter(this)

    override fun getLayoutBinding(container: ViewGroup?): FragmentDogBreedBinding {
        return FragmentDogBreedBinding.inflate(layoutInflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding.root) {
            adapter = dogBreedAdapter
            layoutManager = object : GridLayoutManager(requireContext(), 3) {
                override fun supportsPredictiveItemAnimations(): Boolean = false
            }
            if (itemDecorationCount == 0) addItemDecoration(
                RecyclerInsetsDecoration(20, 20)
            )
        }
        with(viewModel) {
            lifecycleScope.launchWhenStarted {
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
    }

    override fun invoke(data: DogDetail) {
        val direction = DogBreedFragmentDirections.actionDogBreedsToDogBreedDetail(data)
        navigate(direction)
    }
}