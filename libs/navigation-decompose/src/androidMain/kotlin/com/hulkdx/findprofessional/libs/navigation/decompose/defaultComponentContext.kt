package com.hulkdx.findprofessional.libs.navigation.decompose

import androidx.activity.OnBackPressedDispatcherOwner
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelStoreOwner
import androidx.savedstate.SavedStateRegistryOwner
import com.arkivanov.decompose.defaultComponentContext


fun <T> T.defaultComponentContext(): ComponentContext
        where T : SavedStateRegistryOwner,
              T : OnBackPressedDispatcherOwner,
              T : ViewModelStoreOwner,
              T : LifecycleOwner {
    return ComponentContext(defaultComponentContext())
}
