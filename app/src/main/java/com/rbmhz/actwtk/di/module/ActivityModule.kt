package com.rbmhz.actwtk.di.module

import com.rbmhz.actwtk.view.activity.graph.Graph
import com.rbmhz.actwtk.view.activity.login.Login
import com.rbmhz.actwtk.view.activity.main.MainActivity
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector

@Module(includes = [AndroidInjectionModule::class])
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindLoginActivity(): Login

    @ContributesAndroidInjector
    abstract fun bindGraphActivity(): Graph

}