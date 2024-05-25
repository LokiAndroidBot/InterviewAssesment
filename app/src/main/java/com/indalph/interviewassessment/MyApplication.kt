package com.indalph.interviewassessment

import android.app.Application
import com.google.firebase.database.FirebaseDatabase

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize Firebase
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
    }
}
