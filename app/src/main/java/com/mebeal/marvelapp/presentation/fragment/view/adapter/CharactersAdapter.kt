package com.mebeal.marvelapp.presentation.fragment.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mebeal.marvelapp.databinding.ItemCharacterBinding
import com.mebeal.marvelapp.presentation.model.CharactersDisplayModel

class CharactersAdapter(private val listener: CharacterItemListener) :
    RecyclerView.Adapter<CharacterViewHolder>() {

    interface CharacterItemListener {
        fun onClickedCharacter(characterId: Int)
    }

    private val items = ArrayList<CharactersDisplayModel>()
    private val itemsAux = ArrayList<CharactersDisplayModel>()

    private fun setItems(items: ArrayList<CharactersDisplayModel>) {
        this.items.clear()
        this.items.addAll(items.sortedWith(compareBy { it.name }))
        if (itemsAux.size < items.size) {
            itemsAux.clear()
            itemsAux.addAll(items)
        }
        notifyDataSetChanged()
    }

    fun updateItems(itemsNew: List<CharactersDisplayModel>) {
        items.addAll(itemsNew)
        setItems(items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding: ItemCharacterBinding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) =
        holder.bind(items[position])

    fun filteritems(newText: String?) {
        newText?.let {
            val itemsFilters: ArrayList<CharactersDisplayModel> = arrayListOf()
            itemsFilters.addAll(itemsAux.filter { character ->
                character.name?.contains(
                    newText,
                    true
                ) ?: false
            })
            setItems(itemsFilters)
        }
    }
}