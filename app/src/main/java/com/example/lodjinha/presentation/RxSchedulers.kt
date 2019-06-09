package com.example.lodjinha.presentation

import dagger.Reusable
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named

@Reusable
data class RxSchedulers @Inject constructor(
    @Named(MAIN) val androidMainThread: Scheduler,
    @Named(IO) val ioThread: Scheduler
){
    companion object{
        const val MAIN = "main"
        const val IO  = "io"
    }
}