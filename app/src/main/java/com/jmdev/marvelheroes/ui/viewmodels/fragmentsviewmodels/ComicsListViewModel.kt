package com.jmdev.marvelheroes.ui.viewmodels.fragmentsviewmodels


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmdev.marvelheroes.data.PageControler
import com.jmdev.marvelheroes.data.models.comics.ComicsModel
import com.jmdev.marvelheroes.domain.CharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComicsListViewModel @Inject constructor(
    private val getCharacterUseCase: CharactersUseCase,
    private val pageControler: PageControler
)
    : ViewModel() {

    private val comicsModelList = MutableLiveData<List<ComicsModel>>()
    val isLoading = MutableLiveData<Boolean>()

    fun fetchComics(page:Int){
        pageControler.query=""
        pageControler.offset=page
        viewModelScope.launch {
            isLoading.postValue(true)
            val result=getCharacterUseCase.invokeAllComics()
            isLoading.postValue(false)
            if(!result.isNullOrEmpty()){
                Log.d("Viewmodel",result.toString())
                if(comicsModelList.value.isNullOrEmpty()){
                    comicsModelList.postValue(result)
                }else{
                    val _temp=comicsModelList.value?.toMutableList()
                    _temp?.addAll(result)
                    comicsModelList.postValue(_temp)
                }

            }
        }
    }

    fun getComicsList():MutableLiveData<List<ComicsModel>> = comicsModelList

}