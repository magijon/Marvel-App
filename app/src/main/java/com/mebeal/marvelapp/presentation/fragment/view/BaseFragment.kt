package com.mebeal.marvelapp.presentation.fragment.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mebeal.marvelapp.data.network.Resource
import com.mebeal.marvelapp.presentation.activity.view.MainActivity
import com.mebeal.marvelapp.presentation.fragment.logic.BaseViewModel
import com.mebeal.marvelapp.presentation.fragment.logic.factory.ViewModelFactory
import com.mebeal.marvelapp.presentation.utils.addLifeCycleObserver
import dagger.android.support.AndroidSupportInjection
import java.lang.reflect.ParameterizedType
import java.util.*
import javax.inject.Inject

abstract class BaseFragment<VM : ViewModel, DB : ViewDataBinding, R> : Fragment() {

    protected lateinit var viewModel: VM
    protected lateinit var dataBinding: DB

    @Inject
    protected lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[getViewModelClass()]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        DataBindingUtil.inflate<DB>(inflater, getLayoutId(), container, false).also {
            dataBinding = it
            setBindingVariables()
            dataBinding.executePendingBindings()
            dataBinding.lifecycleOwner = this
            initViews()
        }.root

    private fun getViewModelClass() =
        (Objects.requireNonNull(javaClass.genericSuperclass) as ParameterizedType)
            .actualTypeArguments[0] as Class<VM>

    @LayoutRes
    abstract fun getLayoutId(): Int

    protected open fun setBindingVariables() {}

    protected open fun initViews() {
        addLifeCycleObserver((viewModel as BaseViewModel<R>).resourceData){
            when(it.status){
                Resource.Status.SUCCESS -> {
                    onSuccessGetData(it.data)
                }
                Resource.Status.LOADING -> {
                    onLoadingGetData()
                }
                Resource.Status.ERROR -> {
                    onFailureGetData(it.message)
                }
            }
        }
    }

    open fun onSuccessGetData(data: R?) {}

    open fun onFailureGetData(message: String?) {}

    open fun onLoadingGetData(){}

    protected open fun getAdditionalEntry(): Any? = null

    fun showToolbar() {
        (activity as? MainActivity)?.showToolbar()
    }

    fun hideToolbar() {
        (activity as? MainActivity)?.hideToolbar()
    }
}