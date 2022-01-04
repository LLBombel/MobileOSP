package com.rafalropel.mobileosp

import android.app.Application

class App : Application() {
    val db by lazy {
        Database.getInstance(this)
    }
}