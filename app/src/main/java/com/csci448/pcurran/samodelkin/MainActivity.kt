package com.csci448.pcurran.samodelkin
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.csci448.pcurran.samodelkin.data.SamodelkinCharacterViewModelFactory
import com.csci448.pcurran.samodelkin.ui.navigation.SamodelkinNavHost
import com.csci448.pcurran.samodelkin.ui.navigation.specs.DetailScreenSpec
import com.csci448.pcurran.samodelkin.ui.screens.CharacterDetailScreen
import com.csci448.pcurran.samodelkin.ui.screens.CharacterListScreen
import com.csci448.pcurran.samodelkin.ui.screens.NewCharacterScreen
import com.csci448.pcurran.samodelkin.ui.theme.SamodelkinTheme
import com.csci448.pcurran.samodelkin.util.CharacterGenerator
import com.csci448.pcurran.samodelkin.viewodels.ISamodelkinCharacterViewModel
import com.csci448.pcurran.samodelkin.viewodels.PreviewSamodelkinCharacterViewModel
import com.csci448.pcurran.samodelkin.viewodels.PreviewSamodelkinCharacterViewModel.Companion.getInstance
import com.csci448.pcurran.samodelkin.viewodels.SamodelkinCharacterViewModel

class MainActivity : ComponentActivity() {

    private lateinit var samodelkinCharacterViewModel: SamodelkinCharacterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("448","obCrteate called")
        super.onCreate(savedInstanceState)
        val factory = SamodelkinCharacterViewModelFactory()
        samodelkinCharacterViewModel = ViewModelProvider(this, factory).get(factory.getViewModelClass())
        setContent {
            MainActivityContent(viewModel = samodelkinCharacterViewModel)
        }

    }
    @Preview(showSystemUi = true, showBackground = true)
    @Composable
    private fun PreviewMainActivity() {
        val viewModel = PreviewSamodelkinCharacterViewModel.getInstance()
        SamodelkinTheme{
            MainActivityContent(viewModel = viewModel)
        }
    }

}

@Composable
fun MainActivityContent(viewModel: ISamodelkinCharacterViewModel){
    Log.d("448","main activity content")
    val navController = rememberNavController()
    SamodelkinTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            SamodelkinNavHost(navController = navController, viewModel = viewModel)
        }
    }
}

