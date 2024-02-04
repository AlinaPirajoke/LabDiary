package com.example.labdiary.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.labdiary.R

@Composable
fun TitleComponent(title: String){
    Text(
        text = title,
        Modifier.padding(start = 8.dp, top = 20.dp, bottom = 20.dp),
        style = MaterialTheme.typography.h5
    )
}