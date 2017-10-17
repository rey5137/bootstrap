package com.rey.dependency

import android.app.Application
import dagger.Component
import io.reactivex.Scheduler
import javax.inject.Singleton

/**
 * Created by Rey on 12/7/2015.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    val application: Application

    @get: MainScheduler
    val mainScheduler: Scheduler

    @get: ComputationScheduler
    val computationScheduler: Scheduler

    @get: IOScheduler
    val ioScheduler: Scheduler

}
