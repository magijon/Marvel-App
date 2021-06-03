package com.mebeal.marvelapp

import com.mebeal.marvelapp.base.BaseTest
import com.mebeal.marvelapp.data.provider.impl.NetworkProviderImpl
import com.mebeal.marvelapp.data.usecase.contract.NetworkUseCase
import com.mebeal.marvelapp.data.usecase.impl.NetworkUseCaseImpl
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.mockito.Mock

@ExperimentalCoroutinesApi
class NetworkUseCaseTest : BaseTest() {
    @Mock
    private lateinit var provider: NetworkProviderImpl
    private lateinit var useCase: NetworkUseCase

    override fun setup() {
        super.setup()
        useCase = NetworkUseCaseImpl(provider)
    }

    @Test
    fun `get all characters call provider`() {
        //Given

        //When
        runBlockingTest { useCase.getAllCharacters(1) }

        //Then
        runBlockingTest { verify(provider, times(1)).getAllCharacters(1) }
    }

    @Test
    fun `get one characters call provider`() {
        //Given

        //When
        runBlockingTest { useCase.getOneCharacter(1) }

        //Then
        runBlockingTest { verify(provider, times(1)).getOneCharacter(1) }
    }

}

