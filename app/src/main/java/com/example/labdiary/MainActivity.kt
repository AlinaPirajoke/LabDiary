package com.example.labdiary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.compose.rememberNavController
import com.example.labdiary.theme.LabDiaryCustomTheme
import com.example.labdiary.view.BottomNavigationBar
import com.example.labdiary.view.MyNavGraph

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LabDiaryCustomTheme {
                window.statusBarColor = MaterialTheme.colors.primary.toArgb()
                val navController = rememberNavController()
                Scaffold(bottomBar = { BottomNavigationBar(navController = navController) }) {
                    print(it.toString())
                    MyNavGraph(
                        navController = navController,
                    )
                }
            }
        }
    }
}