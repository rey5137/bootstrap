package com.rey.common.flux

import com.rey.mvp2.Presenter
import io.reactivex.Observable

/**
 * Created by Rey on 5/3/17.
 */
interface FluxPresenter<in C: Command, E: Event, S: State>: Presenter {

    val stateChanged: Observable<S>

    fun dispatchCommand(command: C)

}