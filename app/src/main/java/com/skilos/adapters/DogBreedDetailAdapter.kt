package com.skilos.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skilos.adapters.viewholders.DogBreedDetailViewHolder
import com.skilos.utils.AutoUpdateRecyclerView
import kotlin.properties.Delegates

class DogBreedDetailAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), AutoUpdateRecyclerView {

    private var images: List<String> by Delegates.observable(emptyList()) { _, oldBreeds, newBreeds
        ->
        autoNotify(oldBreeds, newBreeds) { oldBreed, newBreed ->
            oldBreed == newBreed && oldBreed.hashCode() == newBreed.hashCode()
        }
    }

    private var _inflater: LayoutInflater? = null
    private val inflater by lazy { _inflater!! }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (_inflater == null) _inflater = LayoutInflater.from(parent.context)
        return DogBreedDetailViewHolder.new(inflater, parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is DogBreedDetailViewHolder) holder.bind(images[position])
    }

    override fun getItemCount(): Int = images.size

    fun updateBreeds(data: List<String>) {
        images = data
    }
}