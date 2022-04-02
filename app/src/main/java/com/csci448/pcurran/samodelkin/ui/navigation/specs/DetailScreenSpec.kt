package com.csci448.pcurran.samodelkin.ui.navigation.specs

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.*
import com.csci448.pcurran.samodelkin.R
import com.csci448.pcurran.samodelkin.ui.screens.CharacterDetailScreen
import com.csci448.pcurran.samodelkin.viewodels.ISamodelkinCharacterViewModel
import java.util.*

object DetailScreenSpec: IScreenSpec {

    override var route: String = "detail/{id}"
    override val title: Int = R.string.app_name
    override val arguments: List<NamedNavArgument> = listOf(
        navArgument("id"){
            type = NavType.StringType
        }
    )

    @Composable
    override fun Content(
        viewModel: ISamodelkinCharacterViewModel,
        navController: NavController,
        navBackStackEntry: NavBackStackEntry,
    ) {
        // get argument off of back stack entry
        val arg = navBackStackEntry.arguments?.getString("id","")
        //Observe the ViewModel character liveData as state
        val characterState = viewModel.characterLiveData.observeAsState()
        //view model loads argument
        viewModel.loadCharacter(UUID.fromString(arg))
        //pass character object to our character detail screen composable
        characterState.value?.let { character -> CharacterDetailScreen(character = character)}
    }

    override fun navigateTo(vararg args: String?): String {
        if (args == null){
            return "0"
        }
        Log.d("448","NAVIGATE TO CALLED ::::: $route")
        route = "detail/${args[0].toString()}"
        return route
    }


}