package com.example.labdiary.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.labdiary.model.MySharedPreferences
import com.example.labdiary.model.database.DatabaseManager
import com.example.labdiary.model.database.entities.DisciplineDbEntity
import com.example.labdiary.uiState.AddDisUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddDisViewModel(val msp: MySharedPreferences, val dbm: DatabaseManager) : ViewModel() {
    val TAG = "AddDisViewModel"
    private val semester = msp.semester

    private val _uiState = MutableStateFlow(AddDisUiState())
    val uiState: StateFlow<AddDisUiState> = _uiState.asStateFlow()

    fun setName(name: String) {
        _uiState.update { state ->
            state.copy(inputtedName = name)
        }
        checkValidation()
    }

    fun setQuantity(quantity: String) {
        try {
            _uiState.update { state ->
                state.copy(inputtedQuantity = quantity.toInt())
            }
            checkValidation()
        } catch (e: Exception) {
            Log.e(TAG, e.message.toString())
        }
    }

    private fun checkValidation(){
        _uiState.update { state ->
            if (state.inputtedQuantity > 0 && state.inputtedName.isNotBlank())
                return@update state.copy(isFieldsValid = true)
            else
                return@update state.copy(isFieldsValid = false)
        }
    }

    fun addDiscipline() {
        if (!uiState.value.isFieldsValid)
            return

        viewModelScope.launch {
            withContext(Dispatchers.IO) {

                dbm.insertNewDis(
                    DisciplineDbEntity(
                        0,
                        semester = semester,
                        name = uiState.value.inputtedName,
                        labs = uiState.value.inputtedQuantity
                    )
                )
                sendToMain()
            }
        }
    }

    fun sendToMain(condition: Boolean = true) {
        _uiState.update { state ->
            state.copy(isGoingToMain = condition)
        }
    }
}