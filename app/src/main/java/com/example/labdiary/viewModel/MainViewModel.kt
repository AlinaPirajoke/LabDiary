package com.example.labdiary.viewModel

import android.icu.util.GregorianCalendar
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.labdiary.model.MySharedPreferences
import com.example.labdiary.model.database.DatabaseManager
import com.example.labdiary.other.MyDateFormatter
import com.example.labdiary.uiState.MainUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.Period
import java.time.temporal.ChronoUnit

class MainViewModel(val msp: MySharedPreferences, val dbm: DatabaseManager): ViewModel() {
    val TAG = "MainViewModel"

    private val cal = GregorianCalendar()

    private val _uiState = MutableStateFlow(MainUiState(
        curSemester = msp.semester
    ))
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    init{
        updateDatesBlock()
    }

    fun updateDatesBlock(){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                dbm.testing()
                val confirmedDate = calculateConfirmedDate()
                val todayDate = getTodayDate()
                val period = Period.between(todayDate, confirmedDate).days

                val result = when{
                    period <= -15 -> 5
                    period in -14..-5 -> 4
                    period in -4..-4 -> 3
                    period in 5..14 -> 2
                    else -> 1
                }

                _uiState.update { state ->
                    state.copy(
                        resultGrade = result,
                        todayDate = MyDateFormatter.formatToLong(todayDate),
                        doneUntil = MyDateFormatter.formatToLong(confirmedDate)
                    )
                }
            }
        }
    }

    private suspend fun calculateConfirmedDate(): LocalDate {
        val semester = uiState.value.curSemester
        val total = dbm.getTotalLabsQuantityFromSemester(semester)
        val done = dbm.getCompletedLabsQuantityFromSemester(semester)
        val dates = getSemesterStartAndFinishDates(semester)
        val daysTotal = ChronoUnit.DAYS.between(dates.first, dates.second)
        Log.i(TAG, "Semester info: start - ${dates.first.toString()}; finish - ${dates.second.toString()}; total days - $daysTotal")
        val daysCompleted = if(total != 0) (daysTotal / total * done).toLong() else 0
        Log.i(TAG, "Labs info: total - $total; done - $done; days completed - $daysCompleted")
        return dates.first.plusDays(daysCompleted)
    }

    private fun getSemesterStartAndFinishDates(semester: Int): Pair<LocalDate, LocalDate>{
        val year = LocalDate.now().year
        val startDate: LocalDate
        val endDate: LocalDate

        if(semester % 2 == 1){ // Если осенний семестр
            startDate = LocalDate.of(year, 9, 1)
            endDate = LocalDate.of(year, 12, 31)
        }
        else{ // Если весенний семестр
            startDate = LocalDate.of(year, 2, 7)
            endDate = LocalDate.of(year, 5, 31)
        }
        return startDate to endDate
    }

    private fun getTodayDate(): LocalDate = LocalDate.now()
}