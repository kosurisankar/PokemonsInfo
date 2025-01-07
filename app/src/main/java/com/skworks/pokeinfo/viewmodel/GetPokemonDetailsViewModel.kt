package com.skworks.pokeinfo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skworks.pokeinfo.client.PokeApiClient
import com.skworks.pokeinfo.model.Pokemon
import com.skworks.pokeinfo.model.PokemonSpecies
import kotlinx.coroutines.launch

class GetPokemonDetailsViewModel(pokeId: Int) : ViewModel() {

    private val _pokemoDetails = MutableLiveData<Pokemon>()
    val pokemoDetails: LiveData<Pokemon> = _pokemoDetails

    private val _pokemoSpecies = MutableLiveData<PokemonSpecies>()
    val pokemoSpecies: LiveData<PokemonSpecies> = _pokemoSpecies

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val pokemonId: Int = pokeId

    init {
        getPokemonDetailsById()
        getPokemonSpeciesById()
    }

    private fun getPokemonDetailsById() {
        viewModelScope.launch {
            _isLoading.value = true // Set loading to true
            try {
                Log.e("POkeIDDDD", "$pokemonId")
                val pokeDetailsById = PokeApiClient().getPokemon(pokemonId)
                _pokemoDetails.value = pokeDetailsById
            } catch (e: Exception) {
                // Handle the error (log or show a message)
                e.printStackTrace()
                Log.e("Exception:getPokemonDetailsById",e.message.toString())
            } finally {
                _isLoading.value = false // Set loading to false after completion
            }
        }
    }
    private fun getPokemonSpeciesById() {
        viewModelScope.launch {
            _isLoading.value = true // Set loading to true
            try {
                Log.e("POkeIDDDD", "$pokemonId")
                val pokeSpeciesById = PokeApiClient().getPokemonSpecies(pokemonId)
                _pokemoSpecies.value = pokeSpeciesById
            } catch (e: Exception) {
                // Handle the error (log or show a message)
                e.printStackTrace()
                Log.e("Exception:pokeSpeciesById",e.message.toString())
            } finally {
                _isLoading.value = false // Set loading to false after completion
            }
        }
    }
}