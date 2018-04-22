package com.example.rahulkumar.test.view.activities.addoreditmedicine

import com.example.rahulkumar.test.commoncontroller.CommonUseCaseController
import com.example.rahulkumar.test.presenter.addmedicine.AddOrEditMedicinePresenter
import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * Created by rahulkumar on 21/04/18.
 */
@Module
abstract class AddOrEditMedicineModule {

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun provideAddMedicine(context: AddOrEditMedicineActivity, iAddMedicine: IAddOrEditMedicine, commonUseCaseController: CommonUseCaseController): AddOrEditMedicinePresenter {
            return AddOrEditMedicinePresenter(context, iAddMedicine,commonUseCaseController)
        }
    }


    @Binds
    abstract fun provideAddMedicineView(activity: AddOrEditMedicineActivity): IAddOrEditMedicine
}