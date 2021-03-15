package com.skilos.adapters.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skilos.databinding.RowDogBreedItemBinding
import com.skilos.models.DogDetail
import com.skilos.utils.DogDetailClick

class DogBreedViewHolder private constructor(
    private val binding: RowDogBreedItemBinding,
    private val onClick: DogDetailClick
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(data: DogDetail) {
        with(binding) {
            tvDogBreed.text = if (data.subBreed.trim().isEmpty()) data.breed else data.subBreed
            root.setOnClickListener { onClick.invoke(data) }
        }
    }

    companion object {
        @JvmStatic
        fun new(
            inflater: LayoutInflater,
            container: ViewGroup,
            onClick: DogDetailClick
        ): DogBreedViewHolder {
            val binding = RowDogBreedItemBinding.inflate(inflater, container, false)
            return DogBreedViewHolder(binding, onClick)
        }
    }
}