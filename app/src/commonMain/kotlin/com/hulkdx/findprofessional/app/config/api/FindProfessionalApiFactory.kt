package com.hulkdx.findprofessional.app.config.api

import com.hulkdx.findprofessional.core.config.PlatformSpecific

object FindProfessionalApiFactory {
    fun baseUrl(ps: PlatformSpecific): String {
        return if (ps.isDebug()) {
            "http://${ps.localhostUrl()}:8080/"
        } else {
            "http://api.sabajafarzadeh.com:30000/"
        }
    }
}
