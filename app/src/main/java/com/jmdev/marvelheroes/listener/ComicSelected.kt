package com.jmdev.marvelheroes.listener

import com.jmdev.marvelheroes.data.models.comics.ComicsModel


interface ComicSelected {
    fun onComicSelected(comicsModel: ComicsModel)
}