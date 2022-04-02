package com.csci448.pcurran.samodelkin.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.csci448.pcurran.samodelkin.R

import com.csci448.pcurran.samodelkin.data.SamodelkinCharacter
import com.csci448.pcurran.samodelkin.util.CharacterGenerator

@Composable
private fun StatsDisplay(statName: String, statValue: String){
    Row{
        Text(
            text = statName,
            modifier = Modifier.weight(1.0f)
        )
        Text(
            text = statValue,
            modifier = Modifier.weight(1.0f)
        )
    }
}

@Composable
private fun SectionHeader(headerTitle: String){
    Column {
        Text(
            text = headerTitle,
            fontSize = 24.sp
        )
        Divider(
            color = Color.Green,
            modifier = Modifier
                .height(2.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
private fun StatsSection(dexterity: String, strength: String, wisdom: String){
    Column {
        SectionHeader(headerTitle = stringResource(R.string.stats_label))
        StatsDisplay(statName = stringResource(id = R.string.dex_label), statValue = dexterity)
        StatsDisplay(statName = stringResource(id = R.string.str_label), statValue = strength)
        StatsDisplay(statName = stringResource(id = R.string.wis_label), statValue = wisdom)
    }
}

@Composable
private fun RaceSection(race: String){
    Column() {
        SectionHeader(headerTitle = stringResource(id = R.string.race_label))
        Text(
            text = race
        )
    }
}

@Composable
private fun NameSection(name: String){
    Column() {
        SectionHeader(headerTitle = stringResource(R.string.stats_label))
        Text(
            text = name
        )
    }
}

@Composable
fun CharacterDetailScreen(character: SamodelkinCharacter){
    Column() {
        NameSection(name = character.name)
        Spacer(modifier = Modifier.height(16.dp))
        RaceSection(race = character.race)
        Spacer(modifier = Modifier.height(16.dp))
        StatsSection(dexterity = character.dex, strength = character.str, wisdom = character.wis)
    }
}

@Preview
@Composable
private fun PreviewCharacterDetailScreen(){
    CharacterDetailScreen(character = CharacterGenerator.generateRandomCharacter())
}