package com.mebeal.marvelapp.presentation.utils

import android.text.Layout
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.mebeal.marvelapp.presentation.model.CharactersDisplayModel
import com.mebeal.marvelapp.presentation.utils.navigation.getDateFormated

fun <T> LifecycleOwner.addLifeCycleObserver(
    liveData: LiveData<T>,
    onChangedAction: ((T) -> Unit)?
) {
    liveData.removeObservers(this)
    liveData.observe(this, Observer { data -> onChangedAction?.invoke(data) })
}

fun LifecycleOwner.removeObservers(observableList: List<LiveData<out Any>>?) {
    observableList?.map { it.removeObservers(this) }
}

fun View.isVisible() = this.visibility == View.VISIBLE

fun View.isGone() = this.visibility == View.GONE

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

@BindingAdapter("set_image_item")
fun AppCompatImageView.setimageItem(charactersDisplayModel: CharactersDisplayModel?) {
    charactersDisplayModel?.let {
        Glide.with(this.context)
            .load(getURLImageList(it))
            .transform(CircleCrop())
            .into(this)
    }
}

@BindingAdapter("set_image_item_normal")
fun AppCompatImageView.setimageItemNormal(charactersDisplayModel: CharactersDisplayModel?) {
    charactersDisplayModel?.let {
        Glide.with(this.context)
            .load(getURLImageDetail(it))
            .into(this)
    }
}

@BindingAdapter("set_data_format")
fun AppCompatTextView.setDataFormat(data: String?) {
    data?.let {
        this.text = getDateFormated(it)
    }
}