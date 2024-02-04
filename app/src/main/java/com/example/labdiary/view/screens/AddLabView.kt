package com.example.labdiary.view.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.labdiary.R
import com.example.labdiary.theme.LabDiaryCustomTheme
import com.example.labdiary.view.Screens
import com.example.labdiary.view.TitleComponent
import com.example.labdiary.viewModel.AddLabViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddLabScreen(navController: NavHostController, vm: AddLabViewModel = koinViewModel()) {
    val uiState by vm.uiState.collectAsState()
    if (uiState.isGoingToMain) {
        vm.sendToMain(false)
        navController.navigate(Screens.Main.route)
    }
    AddLabView(
        labList = uiState.labList,
        picked = uiState.pickedLab,
        onPick = vm::chooseLab,
        onConfirm = vm::confirmChoose,
        toAddDis = { navController.navigate(Screens.AddDis.route) }
    )
}

@Composable
fun AddLabView(
    labList: ArrayList<String>,
    picked: Int,
    onPick: (Int) -> Unit,
    onConfirm: () -> Unit,
    toAddDis: () -> Unit
) {
    Surface(color = MaterialTheme.colors.background) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(vertical = 0.dp, horizontal = 12.dp),
        ) {

            TitleComponent(stringResource(R.string.which_lab_was_done))
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                LazyColumn() {
                    itemsIndexed(labList) { i: Int, lab: String ->
                        LabOption(i = i, lab = lab, onPick = onPick, picked = picked)
                    }
                }
                LabViewBottomButtons(onConfirm = onConfirm, toAddDis = toAddDis)
            }
        }
    }
}

@Composable
fun LabOption(i: Int, lab: String, onPick: (Int) -> Unit, picked: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .clickable { onPick(i) }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = (i == picked),
                onCheckedChange = { onPick(i) },
                colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colors.primary)
            )
            Text(text = lab, Modifier.alpha(alpha = if (i == picked) 1f else 0.7f))
        }
    }
}

@Composable
fun LabViewBottomButtons(onConfirm: () -> Unit, toAddDis: () -> Unit) {
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = onConfirm, Modifier.padding(top = 40.dp)) {
            Text(text = stringResource(id = R.string.add_lab))
        }
        TextButton(
            onClick = toAddDis,
            Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 100.dp)
        ) {
            Text(text = stringResource(id = R.string.add_discipline))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddLabPreview() {
    LabDiaryCustomTheme {
        AddLabView(
            labList = arrayListOf("1я лаба по Вебу", "4я лаба по ТСИСу", "3я лаба по ВышМату"),
            picked = 2,
            onPick = {},
            onConfirm = {},
            toAddDis = {}
        )
    }
}