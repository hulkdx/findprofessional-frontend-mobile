package com.hulkdx.findprofessional.app.test.utils

import io.ktor.util.Platform
import io.ktor.util.PlatformUtils
import io.ktor.util.platform


fun isIOS() = PlatformUtils.platform == Platform.Native
