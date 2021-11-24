package com.jmdev.marvelheroes.domain


import com.jmdev.marvelheroes.data.HeroesRepository
import com.jmdev.marvelheroes.data.models.characters.CharacterModel
import com.jmdev.marvelheroes.data.models.comics.ComicsModel
import javax.inject.Inject

class CharactersUseCase @Inject constructor(
    private val repository: HeroesRepository
) {

    suspend fun invokeFirst():List<CharacterModel>?=repository.getAllCharacters()
    suspend fun invokeNetxt():List<CharacterModel>?=repository.getNextallCharacters()
    suspend fun invokeAllComics():List<ComicsModel>?=repository.getAllComics()

}