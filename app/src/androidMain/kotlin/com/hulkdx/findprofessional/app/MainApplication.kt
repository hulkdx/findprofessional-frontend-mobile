package com.hulkdx.findprofessional.app

import android.app.Application
import com.hulkdx.findprofessional.app.di.initKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}
