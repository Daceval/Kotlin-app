package com.example.App

import android.app.Application
import android.content.Context

class App: Application() {
    companion object {
        lateinit var context: Context
    }
    override fun onCreate() {
        context = applicationContext
        super.onCreate()
    }
}