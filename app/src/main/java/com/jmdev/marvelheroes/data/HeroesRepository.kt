package com.jmdev.marvelheroes.data


import android.util.Log
import com.jmdev.marvelheroes.data.models.characters.CharacterModel
import com.jmdev.marvelheroes.data.models.characters.HeroListProvider
import com.jmdev.marvelheroes.data.models.comics.ComicsModel
import com.jmdev.marvelheroes.data.network.HeroService
import javax.inject.Inject

class HeroesRepository @Inject constructor(
    private val api: HeroService,
    private val heroListProvider: HeroListProvider
) {

    suspend fun getNextallCharacters():List<CharacterModel>{
        val response=api.getNextAllcharacters()
        heroListProvider.characterList.toMutableList().addAll(response)
        Log.d("CallRepository", "repository $response")
        return response
    }
    suspend fun getAllCharacters():List<CharacterModel>{
        val response=api.getAllCharacters()
        //heroListProvider.characterList=response
        heroListProvider.characterList.toMutableList().addAll(response)
        Log.d("CallRepository", "repository $response")
        return response
    }
    suspend fun getAllComics():List<ComicsModel>{
        val response =api.getAllComics()
        Log.d("CallRepository","repository $response")
        return response
    }


}