package com.jmdev.marvelheroes.ui.viewmodels.fragmentsviewmodels

import android.util.Log
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
class HeroListFViewModel @Inject constructor(
    private val getCharacterUseCase: CharactersUseCase,
    private val pageControler: PageControler
) : ViewModel() {
    private val characterModelList=MutableLiveData<List<CharacterModel>>()
    val isLoading=MutableLiveData<Boolean>()

    fun create(){
        viewModelScope.launch {
            isLoading.postValue(true)
            val result=getCharacterUseCase.invokeFirst()
            if(!result.isNullOrEmpty()){
                var _temp=characterModelList.value
                if(_temp!=null) {
                    _temp.toMutableList().addAll(result)
                    characterModelList.postValue(_temp)
                }else {
                    characterModelList.postValue(result)
                }
                isLoading.postValue(false)
            }
        }
    }
    fun nextFech(page:Int){
        pageControler.offset=page
        Log .d("PageControler","Offset: ${pageControler.offset}")
        viewModelScope.launch {
            isLoading.postValue(true)
            val result=getCharacterUseCase.invokeNetxt()
            if(!result.isNullOrEmpty()){
                val _temp=characterModelList.value?.toMutableList()
                _temp?.addAll(result)
                characterModelList.postValue(_temp)
                isLoading.postValue(false)
            }
        }
    }


    fun getCharactersList():MutableLiveData<List<CharacterModel>> {
        return characterModelList
    }



}