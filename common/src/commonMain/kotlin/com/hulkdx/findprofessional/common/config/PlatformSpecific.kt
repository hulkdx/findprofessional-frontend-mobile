package com.hulkdx.findprofessional.common.config

interface PlatformSpecific {
    fun isDebug(): Boolean
    fun localhostUrl(): String
}
