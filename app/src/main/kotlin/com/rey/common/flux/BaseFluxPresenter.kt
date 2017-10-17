package com.rey.common.flux

import android.support.annotation.CallSuper
import com.rey.bootstrap.BuildConfig
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import timber.log.Timber

/**
 * Created by Rey on 5/3/17.
 */
abstract class BaseFluxPresenter<in C : Command, E : Event, S : State>(initialState: S,
                                                                       commandScheduler: Scheduler,
                                                                       private val reducer: Reducer<E, S>) : FluxPresenter<C, E, S> {

    open val logging: Boolean
        get() = BuildConfig.DEBUG

    open val tag: String
        get() = BaseFluxPresenter::class.java.simpleName

    private val commandSubject = PublishSubject.create<C>()
    private val stateSubject = BehaviorSubject.create<S>()
    private val stateDisposable: Disposable

    init {
        stateDisposable = if (logging) {
            commandSubject.observeOn(commandScheduler)
                    .doOnNext { Timber.d("%s | Command | %s", tag, it) }
                    .flatMap { processCommand(it) }
                    .doOnNext { Timber.d("%s | Event | %s", tag, it) }
                    .scan(initialState, { state, event -> reducer.reduce(state, event) })
                    .doOnNext { Timber.d("%s | State | %s", tag, it) }
                    .doOnError { Timber.w(it, "%s | Error", tag) }
                    .subscribe { stateSubject.onNext(it) }
        } else {
            commandSubject.observeOn(commandScheduler)
                    .flatMap { processCommand(it) }
                    .scan(initialState, { state, event -> reducer.reduce(state, event) })
                    .doOnError { Timber.w(it, "%s | Error", tag) }
                    .subscribe { stateSubject.onNext(it) }
        }
    }

    override fun onCreate() {
    }

    @CallSuper
    override fun onDestroy() {
        stateDisposable.dispose()
    }

    protected abstract fun processCommand(command: C): Observable<out E>

    override val stateChanged: Observable<S>
        get() = stateSubject

    override fun dispatchCommand(command: C) {
        commandSubject.onNext(command)
    }

}