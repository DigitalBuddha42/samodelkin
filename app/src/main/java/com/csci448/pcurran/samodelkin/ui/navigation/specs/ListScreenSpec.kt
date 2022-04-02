package com.csci448.pcurran.samodelkin.ui.navigation.specs

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.csci448.pcurran.samodelkin.ui.screens.CharacterDetailScreen
import com.csci448.pcurran.samodelkin.ui.screens.CharacterListScreen
import com.csci448.pcurran.samodelkin.viewodels.ISamodelkinCharacterViewModel
import java.util.*

object ListScreenSpec: IScreenSpec {
    override var route: String = "list"
    override val title: Int
        get() = TODO("Not yet implemented")
    override val arguments: List<NamedNavArgument> = listOf()

    override fun navigateTo(vararg args: String?): String {
        Log.d("448","NAVIGATE TO CALLED from listscreen")
        return route
    }

    @Composable
    override fun Content(
        viewModel: ISamodelkinCharacterViewModel,
        navController: NavController,
        navBackStackEntry: NavBackStackEntry,
    ) {
        // observe character list Livedata as state
        val charList = viewModel.characterListLiveData.observeAsState()
        //call characterListScreen composable
        CharacterListScreen(characterList = charList.value, onClick = { character -> navController.navigate(DetailScreenSpec.navigateTo(character.id.toString()))})
        Log.d("448", "hello, anythoing")
        // get argument off of back stack entry

    }

}