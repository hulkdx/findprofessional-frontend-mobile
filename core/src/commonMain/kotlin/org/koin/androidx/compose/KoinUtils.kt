package org.koin.androidx.compose

import androidx.compose.runtime.Composable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import org.koin.compose.currentKoinScope
import org.koin.core.parameter.ParametersHolder
import kotlin.reflect.KClass


// TODO: replace it when koin has koinViewModel to compose multiplatform
@Composable
inline fun <reified T : ViewModel> koinViewModel(): T {
    val scope = currentKoinScope()
    return viewModel { scope.get<T> { SavedInstanceParametersHolder(this) } }
}

class SavedInstanceParametersHolder(private val extras: CreationExtras) :
    ParametersHolder(mutableListOf()) {

    override fun <T> elementAt(i: Int, clazz: KClass<*>): T {
        return createSavedStateHandleOrElse(clazz) { super.elementAt(i, clazz) }
    }

    override fun <T> getOrNull(clazz: KClass<*>): T? {
        return createSavedStateHandleOrElse(clazz) { super.getOrNull(clazz) }
    }

    private fun <T> createSavedStateHandleOrElse(clazz: KClass<*>, block: () -> T): T {
        return if (clazz == SavedStateHandle::class) {
            extras.createSavedStateHandle() as T
        } else block()
    }
}
