package com.example.labdiary.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.labdiary.model.MySharedPreferences
import com.example.labdiary.model.database.DatabaseManager
import com.example.labdiary.model.database.entities.DisciplineDbEntity
import com.example.labdiary.uiState.AddLabUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddLabViewModel(val msp: MySharedPreferences, val dbm: DatabaseManager) : ViewModel() {
    private lateinit var disciplines: List<DisciplineDbEntity>
    private val labsNumbers = ArrayList<Int>()
    private val semester = msp.semester

    private val _uiState = MutableStateFlow(AddLabUiState())
    val uiState: StateFlow<AddLabUiState> = _uiState.asStateFlow()

    init {
        updateLabList()
    }

    private fun updateLabList() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val labsList = ArrayList<String>()
                disciplines = dbm.getActualDisciplinesFromSemester(semester)

                disciplines.forEachIndexed { index: Int, it: DisciplineDbEntity ->
                    val completed = dbm.getAllCompletedLabsFromDiscipline(it.id).toIntArray()
                    val labNo = findActual(completed)
                    labsNumbers.add(labNo)
                    labsList.add("${labNo}я лаба по \"${it.name}\"")
                }
                _uiState.update { state ->
                    state.copy(labList = labsList)
                }
            }
        }
    }

    private fun findActual(list: IntArray): Int {
        list.sort()
        list.forEachIndexed { i: Int, n: Int ->
            if (n != i+1) return i+1
        }
        return list.size+1
    }

    fun chooseLab(no: Int) {
        _uiState.update { state ->
            state.copy(pickedLab = no)
        }
    }

    fun confirmChoose() {
        if (uiState.value.pickedLab == -1)
            return

        val chosen = disciplines[uiState.value.pickedLab]
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                dbm.insertNewLab(chosen.id, labsNumbers[uiState.value.pickedLab])
            }
        }
        sendToMain()
    }

    fun sendToMain(condition: Boolean = true) {
        _uiState.update { state ->
            state.copy(isGoingToMain = condition)
        }
    }
}