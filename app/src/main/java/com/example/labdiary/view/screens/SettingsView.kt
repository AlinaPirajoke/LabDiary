package com.example.labdiary.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.labdiary.R
import com.example.labdiary.theme.LabDiaryCustomTheme
import com.example.labdiary.view.TitleComponent
import com.example.labdiary.viewModel.MainViewModel
import com.example.labdiary.viewModel.SettingsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingsScreen(
    navController: NavHostController,
    vm: SettingsViewModel = koinViewModel()
) {
    val uiState by vm.uiState.collectAsState()

    SettingsView(
        semester = uiState.semester,
        disQuantity = uiState.disQuantity,
        onSemesterChange = vm::onSemesterChangeToNext,
        toDisList = { /*TODO*/ })
}

@Composable
fun SettingsView(
    semester: Int,
    disQuantity: Int,
    onSemesterChange: () -> Unit,
    toDisList: () -> Unit
) {
    Surface(color = MaterialTheme.colors.background) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            TitleComponent(stringResource(R.string.settings_page))
            MySettingsOption(
                text = stringResource(id = R.string.current_semester),
                value = semester,
                buttonText = stringResource(id = R.string.next_semester),
                onButtonClick = onSemesterChange
            )
            MySettingsOption(
                text = stringResource(id = R.string.dis_quantity),
                value = disQuantity,
                buttonText = stringResource(id = R.string.dis_list),
                onButtonClick = toDisList
            )
        }
    }
}

@Composable
fun MySettingsOption(text: String, value: Int, buttonText: String, onButtonClick: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "$text $value", Modifier.fillMaxWidth(0.6f))
        TextButton(
            onClick = onButtonClick,
            Modifier
                .padding(start = 4.dp)
                .fillMaxWidth(1f)
        ) {
            Text(text = buttonText, fontSize = 17.sp, textAlign = TextAlign.Right)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsPreview() {
    LabDiaryCustomTheme {
        SettingsView(semester = 6, disQuantity = 7, onSemesterChange = {}, toDisList = {})
    }
}