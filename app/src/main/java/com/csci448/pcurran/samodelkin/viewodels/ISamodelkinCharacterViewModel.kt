package com.csci448.pcurran.samodelkin.viewodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.csci448.pcurran.samodelkin.data.SamodelkinCharacter
import java.util.*

abstract class ISamodelkinCharacterViewModel(): ViewModel(){
    abstract val characterListLiveData: LiveData<List<SamodelkinCharacter>>
    abstract val characterLiveData: LiveData<SamodelkinCharacter?>

    abstract fun addCharacter(character: SamodelkinCharacter)
    abstract fun loadCharacter(id:UUID)
    abstract fun generateRandomCharacter():SamodelkinCharacter

}