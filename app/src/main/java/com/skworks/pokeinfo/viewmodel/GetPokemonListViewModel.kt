package com.skworks.pokeinfo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skworks.pokeinfo.client.PokeApiClient
import com.skworks.pokeinfo.model.NamedApiResource
import kotlinx.coroutines.launch

class GetPokemonListViewModel: ViewModel() {

    private val _pokemonsList = MutableLiveData<List<NamedApiResource>>()
    val pokemonsList : LiveData<List<NamedApiResource>> = _pokemonsList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val api = PokeApiClient()

    init {
        fetchPokemonList()
    }

    private fun fetchPokemonList() {
        viewModelScope.launch {
            _isLoading.value = true // Set loading to true
            try {
                val allPokemons = api.getPokemonList(0,1500)
                if (allPokemons.results.isNotEmpty()) {
                    _pokemonsList.value = allPokemons.results
                    Log.e("Pokemonsssss count::::","${allPokemons.results.size}")
                }
            } catch (e: Exception) {
                // Handle the error (log or show a message)
                e.printStackTrace()
                Log.e("Exception::", e.message.toString())
                _pokemonsList.value = emptyList() // Fallback to an empty list
            } finally {
                _isLoading.value = false // Set loading to false after completion
            }
        }
    }
}