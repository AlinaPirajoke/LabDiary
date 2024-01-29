package com.example.labdiary.di
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.labdiary.model.MySharedPreferences
import com.example.labdiary.model.database.DatabaseManager
import com.example.labdiary.viewModel.MainViewModel
import org.koin.androidx.compose.get
import org.koin.androidx.viewmodel.dsl.viewModel
//import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val dataModule = module {
    data()
    presentation()
}

private fun Module.data() {
    single<MySharedPreferences> {
        MySharedPreferences(context = get())
    }
    single<DatabaseManager>{
        DatabaseManager(get())
    }

}

private fun Module.presentation() {
    viewModel<MainViewModel> {
        MainViewModel(msp = get(), dbm = get())
    }
}