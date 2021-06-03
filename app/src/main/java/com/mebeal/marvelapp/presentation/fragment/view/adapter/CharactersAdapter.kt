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
        this.items.addAll(
            this.items.size,
            items.sortedWith(compareBy { it.name }).let { it.subList(itemCount, it.size) })
        itemsAux.clear()
        itemsAux.addAll(this.items)
        notifyDataSetChanged()
    }

    fun updateItems(itemsNew: List<CharactersDisplayModel>) {
        setItems(arrayListOf<CharactersDisplayModel>().apply { addAll(itemsNew) })
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterViewHolder {
        val binding: ItemCharacterBinding =
            ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
            items.clear()
            items.addAll(itemsFilters)
            notifyDataSetChanged()
        }
    }
}