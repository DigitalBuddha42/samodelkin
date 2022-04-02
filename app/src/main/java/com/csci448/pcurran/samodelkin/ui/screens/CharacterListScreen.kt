package com.csci448.pcurran.samodelkin.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.csci448.pcurran.samodelkin.R
import com.csci448.pcurran.samodelkin.data.SamodelkinCharacter
import com.csci448.pcurran.samodelkin.viewodels.PreviewSamodelkinCharacterViewModel
import com.csci448.pcurran.samodelkin.viewodels.SamodelkinCharacterViewModel

@Composable
private fun CharacterRow(character: SamodelkinCharacter, onSelectCharacter: (SamodelkinCharacter)->Unit){
    Card(
        Modifier
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
            .clickable { onSelectCharacter(character) }) {
        Column() {
            Text(text = stringResource(id = R.string.character_name_race_formatter, character.name, character.race))
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Row(Modifier.weight(0.33f)) {
                    Text(text = stringResource(id = R.string.dex_short_label))
                    Text(text = character.str)
                }
                Row(Modifier.weight(0.33f)) {
                    Text(text = stringResource(id = R.string.str_short_label))
                    Text(text = character.str)
                }
                Row(Modifier.weight(0.33f)) {
                    Text(text = stringResource(id = R.string.wis_short_label))
                    Text(text = character.wis)
                }
            }
        }
    }
}

@Composable
fun CharacterListScreen(characterList: List<SamodelkinCharacter>?, onClick: (SamodelkinCharacter) -> Unit){
    if (characterList != null){
        LazyColumn {
            items(characterList) { character ->
                CharacterRow(character = character, onSelectCharacter = onClick)
            }
        }
    }
}

@Preview
@Composable
private fun PreviewCharacterListScreen() {
    val previewInstance = PreviewSamodelkinCharacterViewModel.getInstance()
    val state = previewInstance.characterListLiveData.observeAsState()
    CharacterListScreen(characterList = state.value, onClick = {})
}