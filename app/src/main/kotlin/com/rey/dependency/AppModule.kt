package com.rey.dependency

import android.app.Application
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton


/**
 * Created by Rey on 12/7/2015.
 */
@Module
open class AppModule(private val application: Application) {

    @Provides
    @Singleton
    internal fun provideApplication(): Application
            = application

    @Provides
    @IOScheduler
    internal open fun provideIoScheduler(): Scheduler
            = Schedulers.io()

    @Provides
    @ComputationScheduler
    internal open fun provideComputationScheduler(): Scheduler
            = Schedulers.computation()

    @Provides
    @MainScheduler
    internal open fun provideMainScheduler(): Scheduler
            = AndroidSchedulers.mainThread()

}
