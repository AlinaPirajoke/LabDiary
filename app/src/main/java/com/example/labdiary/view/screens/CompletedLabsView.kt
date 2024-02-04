package com.example.labdiary.view.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.AlertDialog
import androidx.compose.material.Divider
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.labdiary.R
import com.example.labdiary.theme.LabDiaryCustomTheme
import com.example.labdiary.view.TitleComponent
import com.example.labdiary.viewModel.CompletedLabsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun CompletedLabsScreen(
    navController: NavHostController,
    vm: CompletedLabsViewModel = koinViewModel()
) {
    val uiState by vm.uiState.collectAsState()
    if (uiState.isConfirmWindowShowing)
        DeletionConfirmAlert(onConfirm = vm::onDeletionConfirm, onReject = vm::onDeletionReject)
    CompletedLabsView(
        labs = uiState.labsInfo,
        picked = uiState.picked,
        onPick = vm::setPickedLab,
        onDelete = vm::onDeletionRequest
    )
}

@Composable
fun CompletedLabsView(
    labs: ArrayList<Pair<String, String>>,
    picked: Int,
    onPick: (Int) -> Unit,
    onDelete: () -> Unit
) {
    Surface(color = MaterialTheme.colors.background) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp)
        ) {
            TitleComponent(stringResource(R.string.completed_labs))
            LazyColumn(
                Modifier
                    .fillMaxSize()
                    .padding(bottom = 65.dp),
//                contentPadding = PaddingValues(top = 20.dp)
            ) {
                item {
                    Divider(
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 0.dp),
                        color = MaterialTheme.colors.primary
                    )
                }
                itemsIndexed(labs) { i: Int, lab: Pair<String, String> ->
                    labOption(
                        isPicked = (picked == i),
                        label = lab.first,
                        date = lab.second,
                        onPick = { onPick(i) },
                        onDelete = onDelete
                    )
                }
            }
        }
    }
}

@Composable
fun labOption(
    isPicked: Boolean,
    label: String,
    date: String,
    onPick: () -> Unit,
    onDelete: () -> Unit
) {
    Column {
        Row(
            Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth()
                .clickable { onPick() },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(Modifier.padding(start = 8.dp)) {
                Text(text = date, Modifier.alpha(0.6f), style = MaterialTheme.typography.caption)
                Text(text = label, Modifier.padding(top = 8.dp))
            }
            if (isPicked)
                TextButton(
                    onClick = onDelete,
                    Modifier
                        .padding(end = 16.dp)
                        .height(45.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.delete),
                        fontSize = 17.sp
                    )
                }
        }
        Divider(
            Modifier
                .fillMaxWidth(), color = MaterialTheme.colors.primary
        )
    }
}

@Composable
fun DeletionConfirmAlert(onConfirm: () -> Unit, onReject: () -> Unit) {
    AlertDialog(
        onDismissRequest = onReject,
        text = { Text(text = stringResource(id = R.string.deletion_confirm)) },
        confirmButton = {
            TextButton(
                onClick = onConfirm
            ) {
                Text(text = stringResource(id = R.string.answer_yes))
            }
        },
        dismissButton = {
            TextButton(
                onClick = onReject
            ) {
                Text(text = stringResource(id = R.string.answer_no))
            }
        }
    )
}

@Preview
@Composable
fun CompletedLabsPreview() {
    LabDiaryCustomTheme {
        CompletedLabsView(
            labs = arrayListOf(
                "1я лаба по Вебу" to "20 января 2024",
                "4я лаба по ТСИСу" to "4 февраля 2024",
                "3я лаба по ВышМату" to "6 февраля 2024"
            ), picked = 1, onPick = {}, onDelete = {})
    }
}