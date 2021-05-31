package com.mebeal.marvelapp.presentation.utils.customview

sealed class DualResponse {
    object AcceptResponse : DualResponse()
    object DenyResponse : DualResponse()
}