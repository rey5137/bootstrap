package com.rey.common.flux

/**
 * Created by rey5137 on 4/20/17.
 */
interface Reducer<in E: Event, S: State> {

    fun reduce(oldState: S, event: E): S

}