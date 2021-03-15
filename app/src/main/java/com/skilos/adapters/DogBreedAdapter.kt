package com.skilos.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skilos.adapters.viewholders.DogBreedViewHolder
import com.skilos.adapters.viewholders.EmptyDogBreedViewHolder
import com.skilos.models.DogDetail
import com.skilos.utils.AutoUpdateRecyclerView
import com.skilos.utils.DogDetailClick
import kotlin.properties.Delegates

class DogBreedAdapter(
    private val onClick: DogDetailClick
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), AutoUpdateRecyclerView {

    private var breeds: List<DogDetail> by
    Delegates.observable(emptyList()) { _, oldBreeds, newBreeds ->
        autoNotify(oldBreeds, newBreeds) { oldBreed, newBreed ->
            oldBreed.breed == newBreed.breed && oldBreed.subBreed == newBreed.subBreed
        }
    }

    init {
        setHasStableIds(true)
    }

    private var _inflater: LayoutInflater? = null
    private val inflater by lazy { _inflater!! }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (_inflater == null) _inflater = LayoutInflater.from(parent.context)
        return if (viewType == TYPE_EMPTY) {
            EmptyDogBreedViewHolder.new(inflater, parent)
        } else DogBreedViewHolder.new(inflater, parent, onClick)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is DogBreedViewHolder) holder.bind(breeds[position])
    }

    override fun getItemCount(): Int = if (breeds.isEmpty()) 1 else breeds.size

    override fun getItemId(position: Int): Long =
        if (breeds.isEmpty()) 0L else breeds[position].hashCode().toLong()

    override fun getItemViewType(position: Int): Int =
        if (breeds.isEmpty()) TYPE_EMPTY else TYPE_BREED

    fun updateBreeds(data: List<DogDetail>) {
        breeds = data
    }

    companion object {
        private const val TYPE_EMPTY = 0
        private const val TYPE_BREED = 1
    }
}