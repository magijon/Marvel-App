package com.mebeal.marvelapp.presentation.fragment.logic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mebeal.marvelapp.data.network.models.CharacterCallResponse
import com.mebeal.marvelapp.data.usecase.contract.NetworkUseCase
import com.mebeal.marvelapp.presentation.model.CharactersDisplayModel
import com.mebeal.marvelapp.presentation.model.mappers.CharactersDisplayModelMapper
import com.mebeal.marvelapp.presentation.utils.performGetOperation

class CharacterDetailViewModel(private val providerNetworkUseCase: NetworkUseCase) : BaseViewModel<CharacterCallResponse>() {

    private val _character: MutableLiveData<CharactersDisplayModel> = MutableLiveData()
    val character: LiveData<CharactersDisplayModel> = _character

    override fun startLogic(additionalEntry: Any?) {
        performGetOperation(_resourceData) {
            providerNetworkUseCase.getOneCharacter(additionalEntry as Int)
        }
    }

    override fun onSuccessGetData(data: CharacterCallResponse?) {
        super.onSuccessGetData(data)
        data?.let {
            _character.setValue(CharactersDisplayModelMapper.fromCharacterResponse(it))
        }
    }
}