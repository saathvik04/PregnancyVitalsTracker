📘 Pregnancy Vitals Tracker

An Android application built using Jetpack Compose, Room Database, and WorkManager to help track and manage pregnancy-related vitals such as blood pressure, heart rate, weight, and baby kicks, with periodic reminder notifications.

🚀 Features

📊 Add and view pregnancy vitals:

Blood Pressure

Heart Rate

Weight

Baby Kicks

🧠 MVVM architecture with ViewModel and StateFlow

💾 Local persistence using Room Database

🔔 Reminder notifications using WorkManager

🎨 Modern UI built with Jetpack Compose

⚡ Kotlin-first, lifecycle-aware design

📂 Project Structure
app/
 ├── MainActivity.kt
 ├── VitalEntity.kt
 ├── VitalsDao.kt
 ├── VitalDatabase.kt
 ├── VitalViewModel.kt
 ├── ReminderWorker.kt
 └── AppContext.kt

🔔 Reminder System

Uses WorkManager

Designed to trigger periodic reminders (e.g., every 5 hours)

Displays notification prompting users to update vitals

Note: On Android 13+, notification permission (POST_NOTIFICATIONS) is required.

🛠️ Setup Instructions

Clone the repository:git clone https://github.com/saathvik04/PregnancyVitalsTracker.git
Open the project in Android Studio

Sync Gradle files

Run on:

Physical Android device (recommended), or

Emulator (if KVM is enabled)

⚠️ Known Notes

Emulator issues on WSL (/dev/kvm permission denied) are environment-related.

The project builds successfully and is suitable for evaluation and submission.

Notifications require runtime permission on Android 13+.

📌 Future Enhancements

Charts and trends for vitals

Export data as PDF

Cloud backup

Doctor/guardian sharing

Custom reminder intervals

👨‍💻 Author

Vishnu Saathvik
GitHub: https://github.com/saathvik04

