package com.hulkdx.findprofessional.common.config

import com.hulkdx.findprofessional.common.BuildConfig

actual fun isDebug(): Boolean = BuildConfig.DEBUG
actual fun localhost(): String = "10.0.2.2"
