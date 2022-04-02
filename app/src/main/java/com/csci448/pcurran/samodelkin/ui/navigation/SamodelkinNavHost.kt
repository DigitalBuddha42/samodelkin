package com.csci448.pcurran.samodelkin.ui.navigation


import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.csci448.pcurran.samodelkin.ui.navigation.specs.DetailScreenSpec
import com.csci448.pcurran.samodelkin.ui.navigation.specs.IScreenSpec
import com.csci448.pcurran.samodelkin.viewodels.ISamodelkinCharacterViewModel
import com.csci448.pcurran.samodelkin.viewodels.SamodelkinCharacterViewModel

@Composable
fun SamodelkinNavHost(navController: NavController, viewModel: ISamodelkinCharacterViewModel){
    Log.d("448",IScreenSpec.start)
    NavHost(
        navController = navController as NavHostController,
        startDestination = IScreenSpec.start
    ){
        IScreenSpec.screens.forEach { (route, screen) ->
            if (screen != null){
                Log.d("448",route.toString())
                composable(route = screen.route, arguments = screen.arguments){
                    navBackStackEntry ->  screen.Content(viewModel = viewModel,
                    navController = navController,
                    navBackStackEntry = navBackStackEntry)
                }
            }
        }
    }
}