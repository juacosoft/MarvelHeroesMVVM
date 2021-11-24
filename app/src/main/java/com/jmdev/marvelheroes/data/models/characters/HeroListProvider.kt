package com.jmdev.marvelheroes.data.models.characters

import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class HeroListProvider @Inject constructor() {

    var characterList:List<CharacterModel> = emptyList()



}