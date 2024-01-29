package com.example.labdiary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.graphics.toArgb
import com.example.labdiary.theme.LabDiaryCustomTheme
import com.example.labdiary.view.MainScreen
import com.example.labdiary.view.MainView
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LabDiaryCustomTheme {
                window.statusBarColor = MaterialTheme.colors.primary.toArgb()
                MainScreen()
            }
        }
    }
}

//fun addOneDay(cal: Calendar) {
//    cal.roll(Calendar.DATE, true)
//}