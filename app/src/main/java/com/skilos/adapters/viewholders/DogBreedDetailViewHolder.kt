package com.skilos.adapters.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.skilos.R
import com.skilos.databinding.GridDogBreedItemBinding

class DogBreedDetailViewHolder private constructor(
    private val binding: GridDogBreedItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(data: String) {
        binding.imgBreedImage.load(data) {
            placeholder(R.drawable.ic_dog)
            transformations(CircleCropTransformation())
            crossfade(true)
        }
    }

    companion object {
        @JvmStatic
        fun new(inflater: LayoutInflater, viewGroup: ViewGroup?): DogBreedDetailViewHolder {
            val binding = GridDogBreedItemBinding.inflate(inflater, viewGroup, false)
            return DogBreedDetailViewHolder(binding)
        }
    }
}