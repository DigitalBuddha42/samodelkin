package com.csci448.pcurran.samodelkin.ui.navigation.specs

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.csci448.pcurran.samodelkin.ui.screens.NewCharacterScreen
import com.csci448.pcurran.samodelkin.viewodels.ISamodelkinCharacterViewModel

object NewCharacterScreenSpec :IScreenSpec{
    override val route = "new"
    override val title: Int
        get() = TODO("Not yet implemented")
    override val arguments: List<NamedNavArgument> = listOf()
    @Composable
    override fun Content(
        viewModel: ISamodelkinCharacterViewModel,
        navController: NavController,
        navBackStackEntry: NavBackStackEntry,
    ) {
        NewCharacterScreen(initialCharacter = viewModel.generateRandomCharacter(),
            onGenerateRandomCharacter = { viewModel.generateRandomCharacter() },
            onRequestApiCharacter = { viewModel.generateRandomCharacter() },
            onSaveCharacter = {character -> viewModel.addCharacter(character)
                navController.popBackStack()})
    }

    override fun navigateTo(vararg args: String?): String {
        return ListScreenSpec.route
    }


}