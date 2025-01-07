package com.skworks.pokeinfo.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.skworks.pokeinfo.viewmodel.GetPokemonDetailsViewModel


class GetPokemonDetailsViewModelFactory(private val pokemonId: Int) : ViewModelProvider.Factory {
    // Correctly override 'create' method
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Check if the modelClass is assignable from GetPokemonDetailsViewModel
        if (modelClass.isAssignableFrom(GetPokemonDetailsViewModel::class.java)) {
            return GetPokemonDetailsViewModel(pokemonId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}