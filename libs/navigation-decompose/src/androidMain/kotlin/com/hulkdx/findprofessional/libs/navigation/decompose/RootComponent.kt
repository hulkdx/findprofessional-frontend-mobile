package com.hulkdx.findprofessional.libs.navigation.decompose

import androidx.activity.ComponentActivity
import com.arkivanov.decompose.defaultComponentContext
import org.koin.android.ext.android.get


fun ComponentActivity.getRootComponent() = RootComponent(
    get(),
    defaultComponentContext(),
)
