package com.mebeal.marvelapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mebeal.marvelapp.base.BaseTestCorountine
import com.mebeal.marvelapp.data.network.Resource
import com.mebeal.marvelapp.data.network.models.CharacterList
import com.mebeal.marvelapp.data.network.models.CharacterListResponse
import com.mebeal.marvelapp.data.network.models.CharacterResponse
import com.mebeal.marvelapp.data.usecase.contract.NetworkUseCase
import com.mebeal.marvelapp.presentation.fragment.logic.CharactersViewModel
import com.mebeal.marvelapp.presentation.model.ScreenFlowState
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
class CharactersViewModelTest : BaseTestCorountine() {

    @Mock
    lateinit var networkUseCase: NetworkUseCase
    private lateinit var charactersViewModel: CharactersViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    override fun setup() {
        super.setup()
        charactersViewModel = CharactersViewModel(networkUseCase)
    }

    @Test
    fun `get data Success`() {
        runBlockingTest {
            //Give
            launch (testDispatcher){
                Mockito.`when`(networkUseCase.getAllCharacters(1)).thenReturn(
                    Resource.success(
                        CharacterListResponse(
                            CharacterList(1, 1, 1, 1, listOf())
                        )
                    )
                )
                //When
                charactersViewModel.loadCharacters(1)
            }.start()

            //Then
            charactersViewModel.resourceData.observeForever {
                if (it.status != Resource.Status.LOADING) {
                    assert(it.status == Resource.Status.SUCCESS)
                }
            }
        }
    }


    @Test
    fun `get data Error`() {
        runBlockingTest {
            //Give
            launch (testDispatcher){
                Mockito.`when`(networkUseCase.getAllCharacters(1)).thenReturn(
                    Resource.error("")
                )
                //When
                charactersViewModel.loadCharacters(1)
            }.start()

            //Then
            charactersViewModel.resourceData.observeForever {
                if (it.status != Resource.Status.LOADING) {
                    assert(it.status == Resource.Status.ERROR)
                }
            }
        }
    }

    @Test
    fun `run select character`() {
        //Give


        //When
        charactersViewModel.selectCharacter(1)

        //Then
        assert(charactersViewModel.screenFlowState.value is ScreenFlowState.NavigateToCharacterDetail)
    }


    @Test
    fun `success get data it asgin to character`() {
        //Give

        val characterListResponse = getCharacterListResponse()
        val listMapper =
            CharactersDisplayModelMapper.fromCharacterListResponse(characterListResponse)

        //When
        charactersViewModel.onSuccessGetData(characterListResponse)

        //Then
        assert(charactersViewModel.characters.value?.get(0) == listMapper.get(0))
    }


    @Test
    fun `success get data change isLoading`() {
        //Give

        //When
        charactersViewModel.onSuccessGetData(getCharacterListResponse())

        //Then
        assert(!charactersViewModel.isLoading)
    }

    @Test
    fun `call super onLoading`() {
        //Give

        //When
        charactersViewModel.onLoadingGetData()

        //Then
        assert(charactersViewModel.screenFlowState.value is ScreenFlowState.ShowLoading)
    }

    @Test
    fun `call onLoading change isLoading`() {
        //Give

        //When
        charactersViewModel.onLoadingGetData()

        //Then
        assert(charactersViewModel.isLoading)
    }

    @Test
    fun `on failure get data custom`() {
        //Give
        val message = "message"

        //When
        charactersViewModel.onFailureGetData("message")

        //Then
        assert(charactersViewModel.screenFlowState.value is ScreenFlowState.ShowError)
        assert((charactersViewModel.screenFlowState.value as ScreenFlowState.ShowError).message == message)
    }


    //Get Mocks
    private fun getCharacterListResponse(): CharacterListResponse {
        val characterResponse1 = CharacterResponse(1, "", "", null, "")
        val characterResponse2 = CharacterResponse(2, "", "", null, "")
        return CharacterListResponse(
            CharacterList(
                1,
                1,
                1,
                1,
                listOf(characterResponse1, characterResponse2)
            )
        )
    }
}

object BuildConfig {
    const val API_KEY = ""
    const val PRIVATE_KEY = ""
}