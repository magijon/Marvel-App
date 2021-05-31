package com.mebeal.marvelapp.presentation.fragment.view

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mebeal.marvelapp.R
import com.mebeal.marvelapp.data.network.models.CharacterListResponse
import com.mebeal.marvelapp.databinding.CharactersFragmentBinding
import com.mebeal.marvelapp.presentation.fragment.logic.CharactersViewModel
import com.mebeal.marvelapp.presentation.fragment.view.adapter.CharactersAdapter
import com.mebeal.marvelapp.presentation.model.CharactersDisplayModel
import com.mebeal.marvelapp.presentation.model.mappers.CharactersDisplayModelMapper
import com.mebeal.marvelapp.presentation.utils.addLifeCycleObserver
import com.mebeal.marvelapp.presentation.utils.visible

class CharactersFragment : BaseFragment<CharactersViewModel, CharactersFragmentBinding, CharacterListResponse>(), CharactersAdapter.CharacterItemListener {

    override fun getLayoutId(): Int = R.layout.characters_fragment

    override fun setBindingVariables() {
        dataBinding.viewmodel = viewModel
    }

    override fun initViews() {
        super.initViews()
        dataBinding.charactersRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            this.adapter = CharactersAdapter(this@CharactersFragment)
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    if (!recyclerView.canScrollVertically(1)) {
                        dataBinding.progressBarMoreItems.visible()
                        viewModel.loadCharacters()
                    }
                }
            })
        }
    }

    override fun onSuccessGetData(data: CharacterListResponse?) {
        data?.let {
            loadRecyclerView(CharactersDisplayModelMapper.fromCharacterListResponse(data))
        }
    }

    private fun loadRecyclerView(list: List<CharactersDisplayModel>) {
        (dataBinding.charactersRv.adapter as CharactersAdapter).updateItems(ArrayList(list))
    }

    override fun onClickedCharacter(characterId: Int) {
        viewModel.selectCharacter(characterId)
    }

}