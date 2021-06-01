package com.mebeal.marvelapp.presentation.fragment.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mebeal.marvelapp.data.network.Resource
import com.mebeal.marvelapp.presentation.activity.view.BaseActivity
import com.mebeal.marvelapp.presentation.activity.view.MainActivity
import com.mebeal.marvelapp.presentation.fragment.logic.BaseViewModel
import com.mebeal.marvelapp.presentation.fragment.logic.factory.ViewModelFactory
import com.mebeal.marvelapp.presentation.model.ScreenFlowState.HideLoading
import com.mebeal.marvelapp.presentation.model.ScreenFlowState.ShowLoading
import com.mebeal.marvelapp.presentation.navigation.NavigationContract
import com.mebeal.marvelapp.presentation.utils.addLifeCycleObserver
import dagger.android.support.AndroidSupportInjection
import java.lang.reflect.ParameterizedType
import java.util.*
import javax.inject.Inject

abstract class BaseFragment <V : BaseViewModel<R>, T : ViewDataBinding, R : Any?> : Fragment() {

    protected lateinit var viewModel: V
    protected lateinit var dataBinding: T

    @Inject
    protected lateinit var viewModelFactory: ViewModelFactory

    @Inject
    protected lateinit var navigator: NavigationContract


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
        DataBindingUtil.inflate<T>(inflater, getLayoutId(), container, false).also {
            dataBinding = it
            setBindingVariables()
            dataBinding.executePendingBindings()
            dataBinding.lifecycleOwner = this
            initViews()
        }.root

    private fun getViewModelClass() =
        (Objects.requireNonNull(javaClass.genericSuperclass) as ParameterizedType)
            .actualTypeArguments[0] as Class<V>

    @LayoutRes
    abstract fun getLayoutId(): Int

    protected open fun setBindingVariables() {}

    protected open fun initViews() {
        addLifeCycleObserver(viewModel.resourceData) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    viewModel.onSuccessGetData(it.data)
                }
                Resource.Status.LOADING -> {
                    viewModel.onLoadingGetData()
                }
                Resource.Status.ERROR -> {
                    viewModel.onFailureGetData(it.message)
                }
            }
        }

        addLifeCycleObserver(viewModel.screenFlowState) {
            when (it) {
                is ShowLoading -> (activity as? BaseActivity<*, *>)?.showLoading()
                is HideLoading -> (activity as? BaseActivity<*, *>)?.hideLoading()
                else -> navigator.handle(this, it)
            }
        }


        viewModel.startLogic(getAdditionalEntry())
    }

    protected open fun getAdditionalEntry(): Any? = null

    fun showToolbar() {
        (activity as? MainActivity)?.showToolbar()
    }

    fun hideToolbar() {
        (activity as? MainActivity)?.hideToolbar()
    }
}