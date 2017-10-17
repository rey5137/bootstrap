package com.rey.dependency

/**
 * Created by Rey on 5/10/2016.
 */
interface HasComponent<out T> {

    val component: T

}
