package com.example.labdiary.other

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.example.labdiary.R
import com.example.labdiary.view.Screens

object Constants {
    @Composable
    fun getBottomNavItems(): List<BottomNavItem> = listOf(
        BottomNavItem(
            title = stringResource(id = R.string.main_page),
            icon = ImageVector.vectorResource(R.drawable.home),
            route = Screens.Main.route
        ),
        BottomNavItem(
            title = stringResource(id = R.string.add_lab_page),
            icon = ImageVector.vectorResource(R.drawable.add),
            route = Screens.AddLab.route
        ),
        BottomNavItem(
            title = stringResource(id = R.string.completed_labs_page),
            icon = ImageVector.vectorResource(R.drawable.history),
            route = Screens.Completed.route
        ),
        BottomNavItem(
            title = stringResource(id = R.string.settings_page),
            icon = ImageVector.vectorResource(R.drawable.settings_oulined),
            route = Screens.Settings.route
        ),
    )
}