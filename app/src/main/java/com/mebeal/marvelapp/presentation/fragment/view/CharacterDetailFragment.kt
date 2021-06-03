package com.mebeal.marvelapp.presentation.fragment.view

import android.annotation.SuppressLint
import com.mebeal.marvelapp.R
import com.mebeal.marvelapp.data.network.models.CharacterCallResponse
import com.mebeal.marvelapp.databinding.CharacterDetailFragmentBinding
import com.mebeal.marvelapp.presentation.activity.view.MainActivity
import com.mebeal.marvelapp.presentation.fragment.logic.CharacterDetailViewModel
import com.mebeal.marvelapp.presentation.utils.SELECT_ID

class CharacterDetailFragment :
    BaseFragment<CharacterDetailViewModel, CharacterDetailFragmentBinding, CharacterCallResponse>() {


    override fun getAdditionalEntry(): Any = requireArguments().getInt(SELECT_ID)

    override fun getLayoutId(): Int = R.layout.character_detail_fragment

    override fun setBindingVariables() {
        super.setBindingVariables()
        dataBinding.viewModel = viewModel
    }

}