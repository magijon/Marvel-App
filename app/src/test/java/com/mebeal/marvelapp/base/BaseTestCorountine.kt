package com.mebeal.marvelapp.base

import com.mebeal.marvelapp.utils.ManagedCoroutineScope
import com.mebeal.marvelapp.utils.TestScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

@ExperimentalCoroutinesApi
open class BaseTestCorountine : BaseTest() {

    val testDispatcher = TestCoroutineDispatcher()


    @Before
    override fun setup() {
        super.setup()
        Dispatchers.setMain(testDispatcher)
    }

    @After
    open fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

}