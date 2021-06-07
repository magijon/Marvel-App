package com.mebeal.marvelapp.presentation.fragment.logic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mebeal.marvelapp.data.network.models.CharacterListResponse
import com.mebeal.marvelapp.data.usecase.contract.NetworkUseCase
import com.mebeal.marvelapp.presentation.model.CharactersDisplayModel
import com.mebeal.marvelapp.presentation.model.ScreenFlowState
import com.mebeal.marvelapp.presentation.model.ScreenFlowState.NavigateToCharacterDetail
import com.mebeal.marvelapp.presentation.model.mappers.CharactersDisplayModelMapper
import com.mebeal.marvelapp.presentation.utils.performGetOperation
import com.mebeal.marvelapp.presentation.wrapper.RestrictedLiveData

class CharactersViewModel(private val providerNetworkUseCase: NetworkUseCase) :
    BaseViewModel<CharacterListResponse>() {

    private val _characters: MutableLiveData<MutableList<CharactersDisplayModel>> =
        MutableLiveData(mutableListOf())
    val characters: LiveData<MutableList<CharactersDisplayModel>> = _characters

    var isLoading: Boolean = false

    init {
        loadCharacters(_characters.value?.size ?: 0)
    }

    fun selectCharacter(characterId: Int) {
        screenState.setValue(NavigateToCharacterDetail(characterId))
    }

    fun loadCharacters(itemCount: Int) {
        performGetOperation(_resourceData) {
            providerNetworkUseCase.getAllCharacters(itemCount)
        }
    }

    override fun onSuccessGetData(data: CharacterListResponse?) {
        super.onSuccessGetData(data)
        data?.let {
            _characters.value = _characters.value?.apply {
                this.addAll(
                    this.size,
                    CharactersDisplayModelMapper.fromCharacterListResponse(it)
                )
            }
            isLoading = false
        }
    }


    override fun onLoadingGetData() {
        if (characters.value?.size ?: 0 == 0)
            super.onLoadingGetData()
        isLoading = true
    }

}
