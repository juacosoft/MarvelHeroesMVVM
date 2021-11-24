package com.jmdev.marvelheroes.listener

import com.jmdev.marvelheroes.data.models.characters.CharacterModel

interface HeroSelected {
    fun onHeroSelected(characterModel: CharacterModel)
}