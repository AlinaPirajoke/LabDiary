package com.example.labdiary.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.labdiary.R
import com.example.labdiary.theme.LabDiaryCustomTheme
import com.example.labdiary.view.Screens
import com.example.labdiary.view.TitleComponent
import com.example.labdiary.viewModel.AddDisViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddDisScreen(navController: NavHostController, vm: AddDisViewModel = koinViewModel()) {
    val uiState by vm.uiState.collectAsState()
    if (uiState.isGoingToMain) {
        vm.sendToMain(false)
        navController.navigate(Screens.Main.route)
    }
    AddDisView(
        disName = uiState.inputtedName,
        labsQuantity = uiState.inputtedQuantity,
        onNameChange = vm::setName,
        onQuantityChange = vm::setQuantity,
        onConfirm = vm::addDiscipline,
        isInputValid = uiState.isFieldsValid
    )
}

@Composable
fun AddDisView(
    disName: String,
    labsQuantity: Int,
    onNameChange: (String) -> Unit,
    onQuantityChange: (String) -> Unit,
    onConfirm: () -> Unit,
    isInputValid: Boolean
) {
    Surface(color = MaterialTheme.colors.background) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp)
        ) {
            TitleComponent(title = stringResource(id = R.string.adding_new_discipline))

            Column(
                Modifier
                    .padding(bottom = 150.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                val focusManager = LocalFocusManager.current
                NameInput(value = disName, onChange = onNameChange, focusManager = focusManager)
                QuantityInput(
                    value = labsQuantity.toString(),
                    onChange = onQuantityChange,
                    onConfirm = onConfirm
                )
                Button(onClick = onConfirm, Modifier.padding(top = 20.dp), enabled = isInputValid) {
                    Text(
                        text = stringResource(id = R.string.confirm),
                        Modifier.padding(horizontal = 25.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun NameInput(value: String, onChange: (String) -> Unit, focusManager: FocusManager) {
    OutlinedTextField(
        value = value,
        onValueChange = onChange,
        Modifier.padding(top = 20.dp),
        label = { Text(text = stringResource(id = R.string.name_field)) },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.background,
            focusedLabelColor = MaterialTheme.colors.onBackground,
            unfocusedLabelColor = MaterialTheme.colors.primary,
            unfocusedIndicatorColor = MaterialTheme.colors.onBackground
        ),
        singleLine = true,
        keyboardActions = KeyboardActions(onNext = {
            focusManager.moveFocus(FocusDirection.Down)
        }),
    )
}

@Composable
fun QuantityInput(value: String, onChange: (String) -> Unit, onConfirm: () -> Unit) {
    OutlinedTextField(
        value = if (value == "0") "" else value,
        onValueChange = onChange,
        Modifier.padding(top = 20.dp),
        label = { Text(text = stringResource(id = R.string.labs_quantity_field)) },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.background,
            focusedLabelColor = MaterialTheme.colors.onBackground,
            unfocusedLabelColor = MaterialTheme.colors.primary,
            unfocusedIndicatorColor = MaterialTheme.colors.onBackground
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        keyboardActions = KeyboardActions(onDone = { onConfirm() }),
    )
}

@Preview(showBackground = true)
@Composable
fun AddDisPreview() {
    LabDiaryCustomTheme {
        AddDisView(
            disName = "sample",
            labsQuantity = 5,
            onNameChange = {},
            onQuantityChange = {},
            onConfirm = {},
            isInputValid = true
        )
    }
}
