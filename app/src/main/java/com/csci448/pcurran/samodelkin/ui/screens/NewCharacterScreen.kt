package com.csci448.pcurran.samodelkin.ui.screens

import android.content.res.Configuration
import android.telecom.Conference
import android.widget.Button
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.csci448.pcurran.samodelkin.data.SamodelkinCharacter
import com.csci448.pcurran.samodelkin.util.CharacterGenerator
import com.csci448.pcurran.samodelkin.R



@Composable
fun NewCharacterScreen(
    initialCharacter: SamodelkinCharacter,
    onGenerateRandomCharacter: () -> SamodelkinCharacter,
    onRequestApiCharacter: () -> SamodelkinCharacter,
    onSaveCharacter: (SamodelkinCharacter) -> Unit
) {
    val characterDataState = rememberSaveable { mutableStateOf(initialCharacter) }
    val portrait = LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT

    if (portrait) Column() {
        //portrait layout
        CharacterCard(characterDataState)
        Spacer(modifier = Modifier.height(16.dp))
        Row() {
            Box(modifier = Modifier.weight(0.5f)) {
                GenerateRandomCharacterButton(
                    characterDataState = characterDataState,
                    onGenerateRandomCharacter = onGenerateRandomCharacter
                )
            }
            Box(Modifier.weight(0.5f)) {
                ApiCharacterButton(characterDataState, onRequestApiCharacter)
            }
        }
        Spacer(Modifier.height(15.dp))
        SaveCharacterButton(characterDataState, onSaveCharacter)
    }
    else{
        //Landscape
        Row(Modifier.padding(16.dp)){
            Box(Modifier.weight (0.5f)){
                CharacterCard(character = characterDataState)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(Modifier.weight (0.5f)){
                GenerateRandomCharacterButton(characterDataState = characterDataState, onGenerateRandomCharacter = onGenerateRandomCharacter)
                Spacer(modifier = Modifier.height(16.dp))
                ApiCharacterButton(characterDataState = characterDataState, onRequestApiCharacter = onRequestApiCharacter)
                Spacer(modifier = Modifier.height(16.dp))
                SaveCharacterButton(characterDataState = characterDataState, onSaveCharacter = onSaveCharacter)
            }
        }
    }
}

@Composable
private fun ApiCharacterButton(characterDataState: MutableState<SamodelkinCharacter>, onRequestApiCharacter: () -> SamodelkinCharacter){
    characterDataState.value = onRequestApiCharacter()
    NewCharacterButton(text = stringResource(id = R.string.api_label), enabled = false, onClick = {onRequestApiCharacter()})
}

@Composable
private fun SaveCharacterButton(characterDataState: MutableState<SamodelkinCharacter>, onSaveCharacter: (SamodelkinCharacter) -> Unit){
    NewCharacterButton(text = stringResource(id = R.string.save_to_codex_label), enabled = false, onClick = {onSaveCharacter(characterDataState.value)})

}


@Composable
private fun GenerateRandomCharacterButton(
    characterDataState: MutableState<SamodelkinCharacter>,
    onGenerateRandomCharacter: () -> SamodelkinCharacter
){
    NewCharacterButton(text = stringResource(id = R.string.generate_new_random_label), onClick = { characterDataState.value = onGenerateRandomCharacter() })
}

@Composable
private fun NewCharacterButton(text: String, enabled: Boolean = true, onClick: () -> Unit){
    Button(onClick = { onClick() }, enabled = enabled, modifier = Modifier.fillMaxWidth() ) {
        Text(text, textAlign = TextAlign.Center)
    }
}

@Composable
private fun CharacterCard(character: MutableState<SamodelkinCharacter>){
    Card(){
        CharacterDetailScreen(character = character.value)
    }
}

@Preview
@Composable
private fun PreviewCharacterDetailScreen(){
    NewCharacterScreen(initialCharacter = CharacterGenerator.placeHolderCharacter(),
        onGenerateRandomCharacter = { CharacterGenerator.generateRandomCharacter() },
        onRequestApiCharacter = { CharacterGenerator.generateRandomCharacter() },
        onSaveCharacter = { })
}

@Preview(device = Devices.AUTOMOTIVE_1024p, widthDp = 1024, heightDp = 720)
@Composable
private fun PreviewLandscapeNewCharacterScreen(){
    NewCharacterScreen(initialCharacter = CharacterGenerator.placeHolderCharacter(),
        onGenerateRandomCharacter = { CharacterGenerator.generateRandomCharacter() },
        onRequestApiCharacter = { CharacterGenerator.generateRandomCharacter() },
        onSaveCharacter = { })
}