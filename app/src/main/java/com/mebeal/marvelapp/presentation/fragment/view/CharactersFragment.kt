package com.mebeal.marvelapp.presentation.fragment.view

import android.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mebeal.marvelapp.R
import com.mebeal.marvelapp.data.network.models.CharacterListResponse
import com.mebeal.marvelapp.databinding.CharactersFragmentBinding
import com.mebeal.marvelapp.presentation.fragment.logic.CharactersViewModel
import com.mebeal.marvelapp.presentation.fragment.view.adapter.CharactersAdapter
import com.mebeal.marvelapp.presentation.model.CharactersDisplayModel
import com.mebeal.marvelapp.presentation.utils.addLifeCycleObserver
import com.mebeal.marvelapp.presentation.utils.gone
import com.mebeal.marvelapp.presentation.utils.visible

class CharactersFragment :
    BaseFragment<CharactersViewModel, CharactersFragmentBinding, CharacterListResponse>(),
    CharactersAdapter.CharacterItemListener {

    override fun getLayoutId(): Int = R.layout.characters_fragment

    override fun setBindingVariables() {
        dataBinding.viewmodel = viewModel
    }

    override fun initViews() {
        super.initViews()
        dataBinding.charactersRv.apply {
            layoutManager = object : LinearLayoutManager(requireContext()) {
                override fun canScrollVertically(): Boolean {
                    return !viewModel.isLoading
                }
            }
            this.adapter = CharactersAdapter(this@CharactersFragment)
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    if (!recyclerView.canScrollVertically(1)) {
                        dataBinding.progressBarMoreItems.visible()
                        viewModel.loadCharacters((this@apply.adapter as CharactersAdapter).itemCount)
                    }

                    if (!recyclerView.canScrollVertically(-1)) {
                        dataBinding.searchViewContainer.visible()
                    }
                }

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (dy > 10)
                        dataBinding.searchViewContainer.gone()
                    super.onScrolled(recyclerView, dx, dy)
                }

            })
        }
        dataBinding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener, OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                (dataBinding.charactersRv.adapter as CharactersAdapter).filteritems(newText)
                return true
            }

        })
        addLifeCycleObserver(viewModel.characters) {
            loadRecyclerView(it)
            dataBinding.progressBarMoreItems.gone()

        }
    }

    private fun loadRecyclerView(list: List<CharactersDisplayModel>) {
        (dataBinding.charactersRv.adapter as CharactersAdapter).updateItems(ArrayList(list))
    }

    override fun onClickedCharacter(characterId: Int) {
        viewModel.selectCharacter(characterId)
    }

}