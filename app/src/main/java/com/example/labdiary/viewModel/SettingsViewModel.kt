package com.example.labdiary.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.labdiary.model.MySharedPreferences
import com.example.labdiary.model.database.DatabaseManager
import com.example.labdiary.model.database.entities.CompletedLabShortInfo
import com.example.labdiary.uiState.CompletedLabsUiState
import com.example.labdiary.uiState.SettingsUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SettingsViewModel(val msp: MySharedPreferences, val dbm: DatabaseManager) : ViewModel() {
    val TAG = "SettingsViewModel"

    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                updateStatistic()
            }
        }
    }

    private suspend fun updateStatistic() {
        _uiState.update { state ->
            state.copy(
                semester = msp.semester,
                disQuantity = dbm.getDisciplinesQuantityFromSemester(msp.semester)
            )
        }
    }

    fun onSemesterChangeToNext(){
        // TODO
    }
}