package com.csci448.pcurran.samodelkin.viewodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.csci448.pcurran.samodelkin.data.SamodelkinCharacter
import com.csci448.pcurran.samodelkin.util.CharacterGenerator
import java.util.*

class SamodelkinCharacterViewModel : ISamodelkinCharacterViewModel(){



    private val _characterListLiveData =
        MutableLiveData< MutableList<SamodelkinCharacter> >( mutableListOf() )
    private val _characterIdLiveData =
        MutableLiveData< UUID >()
    override val characterListLiveData: LiveData<List<SamodelkinCharacter>> =
        Transformations.map(_characterListLiveData)  { characterList -> characterList }
    override val characterLiveData: LiveData<SamodelkinCharacter?> =
        Transformations.map(_characterIdLiveData) {characterId ->
            //methodToConvertObjectOfTypeTToObjectOfTypeR( valueOfTypeT )
            var characterT : SamodelkinCharacter? = null
            _characterListLiveData.value?.let { characterList ->
                for(item in characterList){
                    if(item.id == characterId)
                        characterT = item
                }
            }
            characterT
        }
    init {
        for(i in 1..15){addCharacter(generateRandomCharacter())}
    }
    override fun addCharacter(character: SamodelkinCharacter) {
        _characterListLiveData.value?.let { characterList ->
            characterList.add(character)
            _characterListLiveData.value = characterList
        }
    }

    override fun loadCharacter(id: UUID) {
        _characterIdLiveData.value = id
    }

    override fun generateRandomCharacter(): SamodelkinCharacter {
        return CharacterGenerator.generateRandomCharacter()
    }
}


