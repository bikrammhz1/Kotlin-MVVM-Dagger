package com.rbmhz.actwtk

import com.facebook.stetho.Stetho
import com.rbmhz.actwtk.di.component.AppComponent
import com.rbmhz.actwtk.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication


/**
 * Kotlin Application.
 * It's necessary for dagger injection
 */
class KotlinApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG){
            Stetho.initializeWithDefaults(this)
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        //Build app component
        val appComponent: AppComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
        //inject application instance
        appComponent.inject(this)
        return appComponent
    }
}