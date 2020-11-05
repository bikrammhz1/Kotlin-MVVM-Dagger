package com.rbmhz.actwtk.di.component

import android.app.Application
import com.rbmhz.actwtk.KotlinApplication
import  com.rbmhz.actwtk.di.module.*
import com.rbmhz.actwtk.di.module.storage.StorageModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityModule::class,
        ViewModelModule::class,
        RepositoryModule::class,
        StorageModule::class,
        ApiModule::class
    ]
)
interface AppComponent : AndroidInjector<KotlinApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(instance: KotlinApplication?)
}