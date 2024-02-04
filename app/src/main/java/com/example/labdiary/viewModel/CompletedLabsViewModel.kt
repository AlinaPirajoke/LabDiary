package com.example.labdiary.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.labdiary.model.MySharedPreferences
import com.example.labdiary.model.database.DatabaseManager
import com.example.labdiary.model.database.entities.CompletedLabDbEntity
import com.example.labdiary.model.database.entities.CompletedLabShortInfo
import com.example.labdiary.other.MyDateFormatter
import com.example.labdiary.uiState.CompletedLabsUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CompletedLabsViewModel(val msp: MySharedPreferences, val dbm: DatabaseManager) : ViewModel() {
    val TAG = "CompletedLabsViewModel"
    lateinit var labsList: ArrayList<CompletedLabShortInfo>

    private val _uiState = MutableStateFlow(CompletedLabsUiState())
    val uiState: StateFlow<CompletedLabsUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                updateLabsInfo()
            }
        }
    }

    private suspend fun updateLabsInfo() {
        labsList = ArrayList(dbm.getAllCompletedLabs())
        val labsInfo = ArrayList<Pair<String/*описание*/, String/*дата*/>>()
        labsList.forEach {
            val date = MyDateFormatter.formatFromShortToLong(it.dueDate)
            labsInfo.add("${it.number}я лаба по \"${it.disciplineName}\"" to date)
        }
        _uiState.update { state ->
            state.copy(labsInfo = labsInfo)
        }
    }

    fun setPickedLab(no: Int) {
        _uiState.update { state ->
            state.copy(picked = no)
        }
    }

    fun onDeletionRequest() {
        _uiState.update { state ->
            state.copy(isConfirmWindowShowing = true)
        }
    }

    fun onDeletionReject() {
        _uiState.update { state ->
            state.copy(isConfirmWindowShowing = false)
        }
    }

    fun onDeletionConfirm() {
        val picked = uiState.value.picked
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                dbm.deleteLab(
                    CompletedLabDbEntity(
                        id = labsList[picked].id,
                        discipline = 0,
                        number = 0,
                        date = ""
                    )
                )
                labsList.removeAt(picked)
                uiState.value.labsInfo.removeAt(picked)
                _uiState.update { state ->
                    state.copy(
                        picked = -1,
                        isConfirmWindowShowing = false
                    )
                }

            }
        }
    }
}