package com.rey.dependency

import javax.inject.Qualifier

/**
 * Created by Rey on 10/17/17.
 */
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ComputationScheduler

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class IOScheduler

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class MainScheduler
