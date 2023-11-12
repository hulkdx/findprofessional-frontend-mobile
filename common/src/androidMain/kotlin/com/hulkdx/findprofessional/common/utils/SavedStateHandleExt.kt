package com.hulkdx.findprofessional.common.utils

import androidx.lifecycle.SavedStateHandle


fun <T> SavedStateHandle.getStateFlow(key: String) =
    getStateFlow(key, requireNotNull(get<T>(key)))
