package com.jmdev.marvelheroes.ui.viewmodels.fragmentsviewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmdev.marvelheroes.data.PageControler
import com.jmdev.marvelheroes.data.models.characters.CharacterModel
import com.jmdev.marvelheroes.domain.CharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchHeroViewModel @Inject constructor(
    private val getCharacterUseCase: CharactersUseCase,
    private val pageControler: PageControler
)
    : ViewModel() {
    private val characterModelList= MutableLiveData<List<CharacterModel>>()
    val isLoading= MutableLiveData<Boolean>()

    fun searchFech(page:Int,query:String){
        pageControler.offset=page
        pageControler.query=query
        Log .d("PageControler","Offset: ${pageControler.offset}")
        viewModelScope.launch {
            isLoading.postValue(true)
            val result=getCharacterUseCase.invokeNetxt()
            if(!result.isNullOrEmpty()){
                Log.d("Viewmodel",result.toString())
                if(characterModelList.value.isNullOrEmpty()) {
                    characterModelList.postValue(result)
                }else{
                    val _temp=characterModelList.value?.toMutableList()
                    _temp?.addAll(result)
                    characterModelList.postValue(_temp)
                }
                isLoading.postValue(false)
            }else{
                isLoading.postValue(false)
            }
        }
    }

    fun getSearchListHero():MutableLiveData<List<CharacterModel>> = characterModelList


    override fun onCleared() {
        super.onCleared()
        pageControler.query=""
    }
}