package com.example.labdiary.view

sealed class Screens(val route: String){
    object Main: Screens("main")
    object AddLab: Screens("add_lab")
    object AddDis: Screens("add_discipline")
    object Completed: Screens("history")
    object Settings: Screens("Settings")
}
