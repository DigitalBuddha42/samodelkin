package com.csci448.pcurran.samodelkin.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.csci448.pcurran.samodelkin.viewodels.SamodelkinCharacterViewModel

class SamodelkinCharacterViewModelFactory: ViewModelProvider.Factory {
    fun getViewModelClass() = SamodelkinCharacterViewModel::class.java
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if( modelClass.isAssignableFrom(getViewModelClass()) )
            return modelClass.getConstructor()
                .newInstance()
        throw IllegalArgumentException("Unknown Exception")
    }
}