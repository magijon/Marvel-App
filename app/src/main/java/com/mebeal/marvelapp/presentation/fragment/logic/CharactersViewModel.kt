package com.mebeal.marvelapp.presentation.fragment.logic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mebeal.marvelapp.data.network.models.CharacterListResponse
import com.mebeal.marvelapp.data.usecase.contract.NetworkUseCase
import com.mebeal.marvelapp.presentation.model.CharactersDisplayModel
import com.mebeal.marvelapp.presentation.model.ScreenFlowState
import com.mebeal.marvelapp.presentation.utils.performGetOperation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharactersViewModel(private val providerNetworkUseCase: NetworkUseCase) : BaseViewModel<CharacterListResponse>() {

    override fun startLogic(additionalEntry: Any?) {
        loadCharacters()
    }

    fun selectCharacter(characterId: Int) {
        screenState.setValue(ScreenFlowState.NavigateToCharacterDetail(characterId))
    }

    fun loadCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            _resourceData = performGetOperation { providerNetworkUseCase.getAllCharacters(0) }
        }
    }
}