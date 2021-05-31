package com.mebeal.marvelapp.presentation.model

data class ErrorDisplayModel(var hasErrors: Boolean = false,
                             val message: String? = null)