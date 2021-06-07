package com.mebeal.marvelapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mebeal.marvelapp.base.BaseTestCorountine
import com.mebeal.marvelapp.data.network.Resource
import com.mebeal.marvelapp.data.network.models.*
import com.mebeal.marvelapp.data.usecase.contract.NetworkUseCase
import com.mebeal.marvelapp.presentation.fragment.logic.CharacterDetailViewModel
import com.mebeal.marvelapp.presentation.model.CharactersDisplayModel
import com.mebeal.marvelapp.presentation.model.mappers.CharactersDisplayModelMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class CharacterDetailViewModelTest : BaseTestCorountine() {

    @Mock
    lateinit var networkUseCase: NetworkUseCase
    private lateinit var characterDetailViewModel: CharacterDetailViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()


    override fun setup() {
        super.setup()
        characterDetailViewModel = CharacterDetailViewModel(networkUseCase)
    }

    @Test
    fun `startLogic get data success`() {
        runBlockingTest {
            launch (testDispatcher) {
                //Given
                Mockito.`when`(networkUseCase.getOneCharacter(1)).thenReturn(
                    Resource.success(
                        CharacterCallResponse(CharacterDataResponse(listOf()))
                    )
                )

                //When
                characterDetailViewModel.startLogic(1)
            }.start()
        }

        //Then
        characterDetailViewModel.resourceData.observeForever {
            if (it.status != Resource.Status.LOADING) {
                assert(it.status == Resource.Status.SUCCESS)
            }
        }
    }

    @Test
    fun `startLogic get data error`() {
        runBlockingTest {
            launch (testDispatcher) {
                //Given
                Mockito.`when`(networkUseCase.getOneCharacter(1)).thenReturn(
                    Resource.error("")
                )

                //When
                characterDetailViewModel.startLogic(1)
            }.start()
        }

        //Then
        characterDetailViewModel.resourceData.observeForever {
            if (it.status != Resource.Status.LOADING) {
                assert(it.status == Resource.Status.ERROR)
            }
        }
    }

    @Test
    fun `success get data it asgin to character`() {
        //Give
        val characterResponse: CharacterCallResponse = getCharacterListResponse()
        val mapper : CharactersDisplayModel = CharactersDisplayModelMapper.fromCharacterResponse(characterResponse)

        //When
        characterDetailViewModel.onSuccessGetData(characterResponse)

        //Then
        assert(characterDetailViewModel.character.value == mapper)
    }

    //Get Mocks
    private fun getCharacterListResponse(): CharacterCallResponse =
        CharacterCallResponse(CharacterDataResponse(listOf(CharacterResponse(1,"","",null,""))))
}