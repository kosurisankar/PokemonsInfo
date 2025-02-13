package com.skworks.pokeinfo.client

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.skworks.pokeinfo.model.ApiResource
import com.skworks.pokeinfo.model.NamedApiResource
import com.skworks.pokeinfo.util.ApiResourceAdapter
import com.skworks.pokeinfo.util.NamedApiResourceAdapter
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal class PokeApiServiceImpl(
    private val config: ClientConfig = ClientConfig()
) : PokeApiService by Retrofit.Builder()
    .baseUrl(config.rootUrl)
    .addConverterFactory(
        GsonConverterFactory.create(
            GsonBuilder().apply {
                setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                registerTypeAdapter(
                    TypeToken.get(ApiResource::class.java).type,
                    ApiResourceAdapter()
                )
                registerTypeAdapter(
                    TypeToken.get(NamedApiResource::class.java).type,
                    NamedApiResourceAdapter()
                )
            }.create()
        )
    )
    .client(OkHttpClient.Builder().(config.okHttpConfig)().build())
    .build()
    .create(PokeApiService::class.java)