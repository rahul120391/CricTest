package com.example.rahulkumar.test.view.activities.main

import com.example.rahulkumar.test.commoncontroller.CommonUseCaseController
import com.example.rahulkumar.test.presenter.main.MainPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides


/**
 * Created by rahulkumar on 21/04/18.
 */

@Module
abstract class MainActivityModule {


    @Module
    companion object {
        @JvmStatic
        @Provides
        fun provideMainPresenter(context: MainActivity,commonUseCaseController: CommonUseCaseController): MainPresenter {
            return MainPresenter(context,commonUseCaseController)
        }
    }


    @Binds
    abstract fun provideMainView(activity: MainActivity): IMainView
}