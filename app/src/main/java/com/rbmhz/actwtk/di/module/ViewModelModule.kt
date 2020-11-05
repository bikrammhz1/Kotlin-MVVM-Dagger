package com.rbmhz.actwtk.di.module

import androidx.lifecycle.ViewModel
import com.rbmhz.actwtk.di.annotation.ViewModelKey
import com.rbmhz.actwtk.view.activity.graph.GraphViewModel
import com.rbmhz.actwtk.view.activity.login.LoginViewModel
import com.rbmhz.actwtk.view.activity.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel) : ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel: LoginViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(GraphViewModel::class)
    abstract fun bindGraphViewModel(viewModel: GraphViewModel) : ViewModel
}