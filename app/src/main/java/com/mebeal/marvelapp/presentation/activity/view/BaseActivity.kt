package com.mebeal.marvelapp.presentation.activity.view

import android.Manifest
import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.mebeal.marvelapp.presentation.activity.logic.MainViewModel
import com.mebeal.marvelapp.presentation.fragment.logic.BaseViewModel
import com.mebeal.marvelapp.presentation.fragment.logic.factory.ViewModelFactory
import com.mebeal.marvelapp.presentation.utils.gone
import com.mebeal.marvelapp.presentation.utils.visible
import dagger.android.AndroidInjection
import java.lang.reflect.ParameterizedType
import java.util.*
import javax.inject.Inject

abstract class BaseActivity<V : MainViewModel, T : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var viewModel: V
    protected lateinit var dataBinding: T

    @Inject
    protected lateinit var viewModelFactory: ViewModelFactory

    @SuppressLint("DefaultLocale")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        if (BuildConfig.BUILD_TYPE == RELEASE.buildType) {
//            window.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)
//        }

        AndroidInjection.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[getViewModelClass()]
        bindView()
    }

    private fun bindView() {
        DataBindingUtil.setContentView<T>(this, getLayoutId()).also {
            dataBinding = it
            setBindingVariables()
            dataBinding.executePendingBindings()
            dataBinding.lifecycleOwner = this
            initViews()
        }
    }

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun setBindingVariables()

    protected open fun initViews() {}

    override fun onStart() {
        super.onStart()
        viewModel.startLogic(getAdditionalEntry())
    }

    open fun getAdditionalEntry(): Any? = null

    private fun getViewModelClass() = (Objects.requireNonNull(javaClass.genericSuperclass) as ParameterizedType)
        .actualTypeArguments[0] as Class<V>

    fun showLoading() {
        getLoader()?.visible()
    }

    fun hideLoading() {
        getLoader()?.gone()
    }

    abstract fun getLoader(): View?

    abstract fun showToolbar()

    abstract fun hideToolbar()

}