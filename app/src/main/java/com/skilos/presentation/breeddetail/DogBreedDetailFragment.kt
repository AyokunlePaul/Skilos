package com.skilos.presentation.breeddetail

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.skilos.adapters.DogBreedDetailAdapter
import com.skilos.core.BaseFragment
import com.skilos.databinding.FragmentBreedDetailBinding
import com.skilos.models.DogDetail
import com.skilos.utils.RecyclerInsetsDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import java.util.*

@AndroidEntryPoint
class DogBreedDetailFragment : BaseFragment<FragmentBreedDetailBinding>() {

    private val viewModel by viewModels<DogBreedDetailFragmentViewModel>()
    private val args by lazy {
        requireArguments().getParcelable<DogDetail>(DOG_DETAIL) as DogDetail
    }
    private val detailAdapter by lazy { DogBreedDetailAdapter() }

    override fun getLayoutBinding(container: ViewGroup?): FragmentBreedDetailBinding {
        return FragmentBreedDetailBinding.inflate(layoutInflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            val title = if (args.subBreed.isEmpty()) args.breed else args.subBreed
            tvBreedName.text = title.capitalize(Locale.getDefault())
            rvDogImages.apply {
                adapter = detailAdapter
                layoutManager = object : GridLayoutManager(requireContext(), 2) {
                    override fun supportsPredictiveItemAnimations(): Boolean = false
                }
                if (itemDecorationCount == 0) addItemDecoration(
                    RecyclerInsetsDecoration(
                        20,
                        20
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
            getImages(args)
        }
    }

    companion object {

        private const val DOG_DETAIL = "dog_detail"

        @JvmStatic
        fun <T> new(data: T): DogBreedDetailFragment where T : Parcelable {
            val bundle = Bundle().apply {
                putParcelable(DOG_DETAIL, data)
            }
            return DogBreedDetailFragment().apply { arguments = bundle }
        }
    }
}