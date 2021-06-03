package com.mebeal.marvelapp

import com.mebeal.marvelapp.base.BaseTestCrountine
import com.mebeal.marvelapp.data.network.CharacterService
import com.mebeal.marvelapp.data.network.Resource.Status.ERROR
import com.mebeal.marvelapp.data.network.Resource.Status.SUCCESS
import com.mebeal.marvelapp.data.network.models.CharacterCallResponse
import com.mebeal.marvelapp.data.network.models.CharacterDataResponse
import com.mebeal.marvelapp.data.network.models.CharacterList
import com.mebeal.marvelapp.data.network.models.CharacterListResponse
import com.mebeal.marvelapp.data.provider.contract.NetworkProvider
import com.mebeal.marvelapp.data.provider.impl.NetworkProviderImpl
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@ExperimentalCoroutinesApi
class NetworkProviderTest : BaseTestCrountine() {

    @Mock
    private lateinit var characterService: CharacterService

    private lateinit var networkProvider: NetworkProvider


    override fun setup() {
        super.setup()
        networkProvider = NetworkProviderImpl(characterService)
    }

    @Test
    fun `getallCharacters success`() = runBlockingTest {
        //Given
        whenever(
            characterService.getAllCharacters(
                any(),
                anyInt(),
                any(),
                any(),
                any()
            )
        ).thenReturn(
            Response.success(
                CharacterListResponse(
                    CharacterList(
                        anyInt(), anyInt(), anyInt(),
                        anyInt(), listOf()
                    )
                )
            )
        )

        //When
        val result = async { networkProvider.getAllCharacters(anyInt()) }.await()

        //Then
        assert(result.status == SUCCESS)
    }

    @Test
    fun `getallCharacters failure`() = runBlockingTest {
        //Given

        //When
        val result = async { networkProvider.getAllCharacters(anyInt()) }.await()

        //Then
        assert(result.status == ERROR)
    }


    @Test
    fun `get one character Success`() = runBlockingTest {
        //Given
        val responseBody = Response.success<CharacterCallResponse>(
            CharacterCallResponse(
                CharacterDataResponse(listOf())
            )
        )
        whenever(characterService.getCharacter(anyInt(), anyString(), anyString(), anyString()))
            .thenReturn(responseBody)

        //When
        val result = suspendCoroutine<Response<CharacterCallResponse>> { continuation ->
            runBlockingTest {
                networkProvider.getOneCharacter(eq(1)).let {
                    continuation
                        .resume(
                            characterService.getCharacter(
                                anyInt(),
                                anyString(),
                                anyString(),
                                anyString()
                            )
                        )
                }
            }
        }

        //Then
        assert(result.isSuccessful)
    }

    @Test
    fun `get one character Failure`() = runBlockingTest {
        //Give

        //When
        val result = async { networkProvider.getAllCharacters(anyInt()) }.await()


        //Then
        assert(result.status == ERROR)
    }
}


