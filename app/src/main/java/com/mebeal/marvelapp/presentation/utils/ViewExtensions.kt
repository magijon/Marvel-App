package com.mebeal.marvelapp.presentation.utils

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.mebeal.marvelapp.presentation.model.CharactersDisplayModel

fun <T> LifecycleOwner.addLifeCycleObserver(liveData: LiveData<T>, onChangedAction: ((T) -> Unit)?) {
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
fun AppCompatImageView.setimageItem(charactersDisplayModel: CharactersDisplayModel){
    Glide.with(this)
        .load(getURLImageList(charactersDisplayModel))
        .transform(CircleCrop())
        .into(this)
}