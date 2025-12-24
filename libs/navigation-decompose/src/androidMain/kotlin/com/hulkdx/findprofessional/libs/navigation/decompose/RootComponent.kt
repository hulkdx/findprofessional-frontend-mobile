package com.hulkdx.findprofessional.libs.navigation.decompose

import androidx.activity.ComponentActivity
import com.arkivanov.decompose.defaultComponentContext
import com.hulkdx.findprofessional.core.navigation.NavigationScreen
import kotlinx.serialization.KSerializer
import org.koin.android.ext.android.get


fun ComponentActivity.getRootComponent(
    serializer: KSerializer<NavigationScreen>,
) = RootComponent(
    get(),
    defaultComponentContext(),
    serializer,
)
