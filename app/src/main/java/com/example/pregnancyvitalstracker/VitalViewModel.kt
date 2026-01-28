package com.example.pregnancyvitalstracker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class VitalViewModel(application: Application) : AndroidViewModel(application) {

    private val dao =
        VitalDatabase.getDatabase(application).vitalDao()

    val vitals = dao.getAll()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            emptyList()
        )

    fun addVital(vital: VitalEntity) {
        viewModelScope.launch {
            dao.insert(vital)
        }
    }
}
