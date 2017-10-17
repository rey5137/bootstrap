package com.rey.common

import android.app.Application
import android.content.Context
import android.support.annotation.CallSuper
import com.rey.bootstrap.BuildConfig
import com.rey.dependency.AppComponent
import com.rey.dependency.AppModule
import com.rey.dependency.DaggerAppComponent
import com.rey.dependency.HasComponent
import timber.log.Timber

/**
 * Created by Rey on 10/17/17.
 */
open class BootstrapApplication : Application(), HasComponent<AppComponent> {

    private var appComponent = prepareAppComponent()

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        if (BuildConfig.MULTI_DEX) {
            try {
                val clazz = Class.forName("android.support.multidex.MultiDex")
                val method = clazz.getDeclaredMethod("install", Context::class.java)
                method.invoke(null, this)
            } catch (e: Exception) {
                Timber.e(e, "Error")
            }
        }
    }

    @CallSuper
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }

    protected open fun prepareAppComponent(): AppComponent {
        return DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }


    override val component: AppComponent
        get() = appComponent

    companion object {
        operator fun get(context: Context): BootstrapApplication =
                context.applicationContext as BootstrapApplication
    }

}