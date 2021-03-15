package com.skilos.adapters.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skilos.databinding.RowLayoutEmptyBinding

class EmptyDogBreedViewHolder private constructor(
    binding: RowLayoutEmptyBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        @JvmStatic
        fun new(inflater: LayoutInflater, container: ViewGroup): EmptyDogBreedViewHolder {
            val binding = RowLayoutEmptyBinding.inflate(inflater, container, false)
            return EmptyDogBreedViewHolder(binding)
        }
    }
}