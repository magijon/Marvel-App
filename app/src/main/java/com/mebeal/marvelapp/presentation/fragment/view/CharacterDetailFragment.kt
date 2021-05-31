package com.mebeal.marvelapp.presentation.fragment.view

import com.mebeal.marvelapp.R
import com.mebeal.marvelapp.data.network.models.CharacterCallResponse
import com.mebeal.marvelapp.databinding.CharacterDetailFragmentBinding
import com.mebeal.marvelapp.presentation.fragment.logic.CharacterDetailViewModel

class CharacterDetailFragment :
    BaseFragment<CharacterDetailViewModel, CharacterDetailFragmentBinding, CharacterCallResponse>() {
    override fun getLayoutId(): Int = R.layout.character_detail_fragment
}