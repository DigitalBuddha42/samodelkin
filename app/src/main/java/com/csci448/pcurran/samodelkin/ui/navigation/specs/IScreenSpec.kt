package com.csci448.pcurran.samodelkin.ui.navigation.specs

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.csci448.pcurran.samodelkin.viewodels.ISamodelkinCharacterViewModel

sealed interface IScreenSpec{
    companion object{
        val start = ListScreenSpec.route
        val screens = IScreenSpec::class.sealedSubclasses.associate {
            it.objectInstance?.route to it.objectInstance
        }
    }
    val route: String
    val title: Int
    val arguments: List<NamedNavArgument>
    @Composable
    fun Content(viewModel: ISamodelkinCharacterViewModel,
                navController: NavController,
                navBackStackEntry: NavBackStackEntry)

    fun navigateTo(vararg args: String?): String

}