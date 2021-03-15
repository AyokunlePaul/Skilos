package com.skilos.presentation.breeddetail

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.skilos.adapters.DogBreedDetailAdapter
import com.skilos.core.BaseFragment
import com.skilos.databinding.FragmentBreedDetailBinding
import com.skilos.utils.RecyclerInsetsDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class DogBreedDetailFragment : BaseFragment<FragmentBreedDetailBinding>() {

    private val viewModel by viewModels<DogBreedDetailFragmentViewModel>()
    private val args by navArgs<DogBreedDetailFragmentArgs>()
    private val detailAdapter = DogBreedDetailAdapter()

    override fun getLayoutBinding(container: ViewGroup?): FragmentBreedDetailBinding {
        return FragmentBreedDetailBinding.inflate(layoutInflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            val detail = args.dogDetail
            tvBreedName.text = if (detail.subBreed.isEmpty()) detail.breed else detail.subBreed
            rvDogImages.apply {
                adapter = detailAdapter
                layoutManager = object : GridLayoutManager(requireContext(), 3) {
                    override fun supportsPredictiveItemAnimations(): Boolean = false
                }
                if (itemDecorationCount == 0) addItemDecoration(
                    RecyclerInsetsDecoration(
                        10,
                        10
                    )
                )
            }
        }
        with(viewModel) {
            lifecycleScope.launchWhenStarted {
                images.collect {
                    when (it) {
                        is DogBreedDetailFragmentViewModel.DogBreedDetailResponse.Loading -> {
                        }
                        is DogBreedDetailFragmentViewModel.DogBreedDetailResponse.Failure -> {
                        }
                        is DogBreedDetailFragmentViewModel.DogBreedDetailResponse.Success -> {
                            detailAdapter.updateBreeds(it.data)
                        }
                    }
                }
            }
            getImages(args.dogDetail)
        }
    }
}