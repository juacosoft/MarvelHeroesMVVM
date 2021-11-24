package com.jmdev.marvelheroes.data.network


import android.util.Log
import com.jmdev.marvelheroes.data.PageControler
import com.jmdev.marvelheroes.data.models.characters.CharacterModel
import com.jmdev.marvelheroes.data.models.comics.ComicsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HeroService @Inject constructor(
    private val api: HeroesMarveApi,
    private val pageControler: PageControler
) {

    suspend fun getNextAllcharacters():List<CharacterModel>{
        if(pageControler.query.isEmpty()){
            Log.d("callApi","NextAllCharacter with page: ${pageControler.offset}")
            return withContext(Dispatchers.IO){
                val response=api.getAllCharacters(
                    pageControler.limit,pageControler.offset
                )
                response.body()?.dataModel?.characters ?: emptyList()
            }
        }else{
            Log.d("callApi","NextAllCharacter with query ${pageControler.query}")
            return withContext(Dispatchers.IO){
                val response=api.seacrhWithName(
                    pageControler.query,
                    pageControler.limit,
                    pageControler.offset
                )
                response.body()?.dataModel?.characters ?: emptyList()
            }
        }

    }
    suspend fun getAllComics():List<ComicsModel>{
        return withContext(Dispatchers.IO){
            val response=api.getAllComics(
                pageControler.limit,pageControler.offset
            )
            response.body()?.comicData?.comics ?: emptyList()
        }
    }
    suspend fun getAllCharacters():List<CharacterModel>{
            return withContext(Dispatchers.IO){
                val response=api.getAllCharacters(
                   20,0
                )
                response.body()?.dataModel?.characters ?: emptyList()
            }
    }

}