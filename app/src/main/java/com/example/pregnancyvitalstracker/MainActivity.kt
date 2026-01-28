package com.example.pregnancyvitalstracker

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.work.*
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ✅ Request notification permission (Android 13+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissions(
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                100
            )
        }

        // ✅ SCHEDULE REMINDER EVERY 5 HOURS (THIS FIXES THE ISSUE)
        val reminderRequest =
            PeriodicWorkRequestBuilder<ReminderWorker>(
                5, TimeUnit.HOURS
            ).build()

        WorkManager.getInstance(this)
            .enqueueUniquePeriodicWork(
                "VitalsReminder",
                ExistingPeriodicWorkPolicy.UPDATE,
                reminderRequest
            )

        // UI
        setContent {
            MaterialTheme {
                val vm: VitalViewModel = viewModel()
                VitalsScreen(vm)
            }
        }
    }
}

@Composable
fun VitalsScreen(vm: VitalViewModel) {
    val vitals by vm.vitals.collectAsState()
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Text("+")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            items(vitals) { vital ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Column(Modifier.padding(16.dp)) {
                        Text("BP: ${vital.bp}")
                        Text("Heart Rate: ${vital.heartRate}")
                        Text("Weight: ${vital.weight}")
                        Text("Baby Kicks: ${vital.kicks}")
                    }
                }
            }
        }

        if (showDialog) {
            AddVitalDialog(
                onAdd = {
                    vm.addVital(it)
                    showDialog = false
                },
                onDismiss = { showDialog = false }
            )
        }
    }
}

@Composable
fun AddVitalDialog(
    onAdd: (VitalEntity) -> Unit,
    onDismiss: () -> Unit
) {
    var bp by remember { mutableStateOf("") }
    var heartRate by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var kicks by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add Vitals") },
        text = {
            Column {
                OutlinedTextField(bp, { bp = it }, label = { Text("Blood Pressure") })
                OutlinedTextField(heartRate, { heartRate = it }, label = { Text("Heart Rate") })
                OutlinedTextField(weight, { weight = it }, label = { Text("Weight") })
                OutlinedTextField(kicks, { kicks = it }, label = { Text("Baby Kicks") })
            }
        },
        confirmButton = {
            Button(onClick = {
                onAdd(
                    VitalEntity(
                        bp = bp,
                        heartRate = heartRate.toIntOrNull() ?: 0,
                        weight = weight.toFloatOrNull() ?: 0f,
                        kicks = kicks.toIntOrNull() ?: 0
                    )
                )
            }) {
                Text("Save")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
