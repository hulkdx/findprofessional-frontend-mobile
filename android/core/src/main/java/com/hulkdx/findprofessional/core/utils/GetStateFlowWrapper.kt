package com.hulkdx.findprofessional.core.utils

import androidx.compose.runtime.Composable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.StateFlow
import kotlin.properties.ReadOnlyProperty


fun <T> SavedStateHandle.getStateFlowWrapper(initialValue: T) =
    ReadOnlyProperty<Any?, GetStateFlowWrapper<T>> { _, property ->
        GetStateFlowWrapper(this, property.name, initialValue)
    }

class GetStateFlowWrapper<T>(
    private val savedStateHandle: SavedStateHandle,
    private val key: String,
    initialValue: T,
) {

    private val flow = savedStateHandle.getStateFlow(key, initialValue)

    fun get(): StateFlow<T> = flow

    fun set(value: T) {
        savedStateHandle[key] = value
    }

    val value: T
        get() = flow.value

    @Composable
    fun collectAsStateWithLifecycle() = flow.collectAsStateWithLifecycle()

}
