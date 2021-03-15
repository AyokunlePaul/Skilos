package com.skilos.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.skilos.presentation.breeds.DogBreedFragment
import com.skilos.utils.addFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addFragment(DogBreedFragment(), addToBackStack = true)
    }
}