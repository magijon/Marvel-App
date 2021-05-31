package com.mebeal.marvelapp.presentation.fragment.view.adapter

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.mebeal.marvelapp.databinding.ItemCharacterBinding
import com.mebeal.marvelapp.presentation.model.CharactersDisplayModel
import com.mebeal.marvelapp.presentation.utils.getURLImageList

class CharacterViewHolder(
    private val itemBinding: ItemCharacterBinding,
    private val listener: CharactersAdapter.CharacterItemListener
) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: CharactersDisplayModel) {
        itemBinding.character = item
    }

    override fun onClick(v: View?) {
        itemBinding.character?.id?.let {
            listener.onClickedCharacter(it)
        }
    }
}
