package com.skworks.pokeinfo.repository

import com.skworks.pokeinfo.client.ClientConfig
import com.skworks.pokeinfo.client.ErrorResponse
import com.skworks.pokeinfo.client.PokeApiService
import com.skworks.pokeinfo.model.NamedApiResourceList
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class PokemonRepository {
    private fun <T> Call<T>.result(): T {
        return execute().let {
            if (it.isSuccessful) it.body()!! else throw ErrorResponse(it.code(), it.message())
        }
    }
    private val clientConfig = ClientConfig(
        rootUrl = "https://pokeapi.co/api/v2/",
        okHttpConfig = {
            retryOnConnectionFailure(true)
            connectTimeout(15, TimeUnit.SECONDS)
            readTimeout(15, TimeUnit.SECONDS)
            writeTimeout(15, TimeUnit.SECONDS)
        }
    )
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(clientConfig.rootUrl).client(OkHttpClient.Builder().(clientConfig.okHttpConfig)().build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

   private val apiService :  PokeApiService = retrofit.create(PokeApiService::class.java)

    suspend fun getPokemonList(offset: Int, limit: Int) : NamedApiResourceList {
        return apiService.getPokemonList(offset, limit)
    }
}