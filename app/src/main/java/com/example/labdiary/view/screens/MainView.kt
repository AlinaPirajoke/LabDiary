package com.example.labdiary.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.labdiary.R
import com.example.labdiary.theme.LabDiaryCustomTheme
import com.example.labdiary.view.Screens
import com.example.labdiary.viewModel.MainViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    navController: NavHostController,
    vm: MainViewModel = koinViewModel()
) {
    val uiState by vm.uiState.collectAsState()

    MainView(
        currentDate = uiState.todayDate,
        confirmedDate = uiState.doneUntil,
        semester = uiState.curSemester,
        result = uiState.resultGrade,
        toAddLab = {navController.navigate(Screens.AddLab.route)},
        toCompleted = {navController.navigate(Screens.Completed.route)},
        toSettings = {}
    )
}

@Composable
fun MainView(
    currentDate: String,
    confirmedDate: String,
    semester: Int,
    result: Int,
    toAddLab: () -> Unit,
    toSettings: () -> Unit,
    toCompleted: () -> Unit,
) {
    Surface(color = MaterialTheme.colors.background) {
//        Scaffold(
//            Modifier.padding(vertical = 20.dp, horizontal = 16.dp),
//            backgroundColor = MaterialTheme.colors.background,
//            floatingActionButton = { MainFloatingActionButtons(toAddLab, toCompleted, toSettings) }
//        ) {
//            print(it.toString())
            Column(
                Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                SemesterLabel(semester = semester)
                DatesBlock(
                    currentDate = currentDate,
                    confirmedDate = confirmedDate,
                    result = result
                )
            }
//        }
    }
}

@Composable
fun DatesBlock(currentDate: String, confirmedDate: String, result: Int) {
    Column(
        Modifier
//            .fillMaxWidth()
            .padding(top = 100.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ConfirmedDate(date = confirmedDate)
        Divider(Modifier.padding(horizontal = 20.dp), color = MaterialTheme.colors.primary)
        TodayDate(date = currentDate)
        Divider(Modifier.padding(horizontal = 20.dp), color = MaterialTheme.colors.primary)
        ResultLabel(result = result)
    }
}

@Composable
fun ResultLabel(result: Int) {
    val resultImg: ImageVector
    val resultString: String
    when (result) {
        1 -> {
            resultImg = ImageVector.vectorResource(R.drawable.awesome)
            resultString = stringResource(id = R.string.awesome_result)
        }

        2 -> {
            resultImg = ImageVector.vectorResource(R.drawable.good)
            resultString = stringResource(id = R.string.good_result)
        }

        3 -> {
            resultImg = ImageVector.vectorResource(R.drawable.average)
            resultString = stringResource(id = R.string.average_result)
        }

        4 -> {
            resultImg = ImageVector.vectorResource(R.drawable.not_good)
            resultString = stringResource(id = R.string.not_good_result)
        }

        5 -> {
            resultImg = ImageVector.vectorResource(R.drawable.bad)
            resultString = stringResource(id = R.string.bad_result)
        }

        else -> {
            resultImg = ImageVector.vectorResource(R.drawable.bad)
            resultString = stringResource(id = R.string.error)
        }
    }
    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = 40.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = resultImg,
            contentDescription = "result",
            Modifier
                .padding(end = 12.dp)
                .size(40.dp),
            tint = MaterialTheme.colors.primary
        )
        Text(text = resultString, style = MaterialTheme.typography.subtitle1)
    }
}

@Composable
fun SemesterLabel(semester: Int) {
    Row(Modifier.padding(start = 14.dp, top = 8.dp), verticalAlignment = Alignment.Bottom) {
        Text(
            text = semester.toString(),
            color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.h3
        )
        Text(
            text = stringResource(id = R.string.semester),
            Modifier.padding(start = 5.dp, bottom = 5.dp),
            style = MaterialTheme.typography.h5
        )
    }
}

@Composable
fun ConfirmedDate(date: String) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(top = 20.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.confirmed_date),
            style = MaterialTheme.typography.subtitle1
        )
        Text(
            text = date,
            style = MaterialTheme.typography.h3
        )
    }
}

@Composable
fun TodayDate(date: String) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(top = 20.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.today),
            style = MaterialTheme.typography.subtitle1
        )
        Text(
            text = date,
            style = MaterialTheme.typography.h3
        )
    }
}

@Composable
fun MainFloatingActionButtons(
    firstAction: () -> Unit,
    secondAction: () -> Unit,
    thirdAction: () -> Unit
) {
    Column {
        MyFAB(
            img = ImageVector.vectorResource(R.drawable.add),
            descr = "add",
            onClick = firstAction
        )
        MyFAB(
            img = ImageVector.vectorResource(R.drawable.history),
            descr = "history",
            onClick = secondAction
        )
        MyFAB(
            img = ImageVector.vectorResource(R.drawable.settings_oulined),
            descr = "settings",
            onClick = thirdAction
        )
    }
}

@Composable
fun MyFAB(img: ImageVector, descr: String, onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        Modifier
            .padding(top = 20.dp)
            .size(65.dp),
        backgroundColor = MaterialTheme.colors.surface,
        shape = MaterialTheme.shapes.small
    ) {
        Icon(
            imageVector = img,
            contentDescription = descr,
            Modifier.size(40.dp),
            tint = MaterialTheme.colors.primary
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainViewPreview() {
    LabDiaryCustomTheme {
        MainView(
            currentDate = "12 апреля 2024",
            confirmedDate = "10 апреля 2024",
            semester = 6,
            result = 3,
            toAddLab = {},
            toCompleted = {},
            toSettings = {})
    }
}
