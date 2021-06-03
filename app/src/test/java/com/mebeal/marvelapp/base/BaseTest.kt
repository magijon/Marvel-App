package com.mebeal.marvelapp.base

import org.junit.Before
import org.mockito.MockitoAnnotations
import java.lang.reflect.Field
import java.lang.reflect.Modifier

typealias OurFunction = (Any?) -> Unit

abstract class BaseTest {
    @Throws(Exception::class)
    fun setFinalStatic(field: Field, newValue: Any) {
        field.setAccessible(true)

        val modifiersField = Field::class.java.getDeclaredField("modifiers")
        modifiersField.setAccessible(true)
        modifiersField.setInt(field, field.getModifiers() and Modifier.FINAL.inv())

        field.set(null, newValue)
    }

    @Before
    open fun setup() {
        MockitoAnnotations.openMocks(this)
    }
}