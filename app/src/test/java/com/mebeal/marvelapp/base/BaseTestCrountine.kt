package com.mebeal.marvelapp.base

import com.mebeal.marvelapp.utils.ManagedCoroutineScope
import com.mebeal.marvelapp.utils.TestScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before

@ExperimentalCoroutinesApi
open class BaseTestCrountine : BaseTest() {

    private val testDispatcher = TestCoroutineDispatcher()
    private val managedCoroutineScope: ManagedCoroutineScope = TestScope(testDispatcher)

    @Before
    override fun setup() {
        super.setup()
        //resProvider.mockColors()
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

}