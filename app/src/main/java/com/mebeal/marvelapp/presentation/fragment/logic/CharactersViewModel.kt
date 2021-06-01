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

class CharactersViewModel(private val providerNetworkUseCase: NetworkUseCase) :
    BaseViewModel<CharacterListResponse>() {

    private val _characters: MutableLiveData<List<CharactersDisplayModel>> = MutableLiveData()
    val characters: LiveData<List<CharactersDisplayModel>> = _characters
    var itemCount = 0

    override fun startLogic(additionalEntry: Any?) {
        loadCharacters(itemCount)
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
            _characters.setValue(CharactersDisplayModelMapper.fromCharacterListResponse(it))
            itemCount += it.data.results.size
        }
    }

    override fun onLoadingGetData() {
        if (itemCount == 0)
            super.onLoadingGetData()
    }
}
