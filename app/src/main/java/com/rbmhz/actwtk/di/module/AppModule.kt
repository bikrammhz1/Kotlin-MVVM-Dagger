package com.rbmhz.actwtk.di.module

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.rbmhz.actwtk.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    @Binds
    abstract fun bindContext(application: Application): Context

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory) : ViewModelProvider.Factory
}