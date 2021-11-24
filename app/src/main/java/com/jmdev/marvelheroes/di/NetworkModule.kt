package com.jmdev.marvelheroes.di

import com.jmdev.marvelheroes.constants.ServerUrls
import com.jmdev.marvelheroes.data.network.HeroesMarveApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun providerRetrogfit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ServerUrls.SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Singleton
    @Provides
    fun provideQuoteApiClient(retrofit: Retrofit):HeroesMarveApi{
        return retrofit.create(HeroesMarveApi::class.java)
    }
}