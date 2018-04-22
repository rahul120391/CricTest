package com.example.rahulkumar.test.di.module

import com.example.rahulkumar.test.view.activities.addoreditmedicine.AddOrEditMedicineActivity
import com.example.rahulkumar.test.view.activities.addoreditmedicine.AddOrEditMedicineModule
import com.example.rahulkumar.test.view.activities.main.MainActivity
import com.example.rahulkumar.test.view.activities.main.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by rahulkumar on 21/04/18.
 */
@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = arrayOf(MainActivityModule::class))
    internal abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = arrayOf(AddOrEditMedicineModule::class))
    internal abstract fun bindAddMedicineActivity(): AddOrEditMedicineActivity
}