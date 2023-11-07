package com.hulkdx.findprofessional.common.config.api

import com.hulkdx.findprofessional.common.config.PlatformSpecific

object FindProfessionalApiFactory {

    fun baseUrl(ps: PlatformSpecific): String {
        return if (ps.isDebug()) {
            localHostBaseUrl(ps)
        } else {
            productionBaseUrl()
        }
    }

    private fun localHostBaseUrl(ps: PlatformSpecific) =
        "http://${ps.localhostUrl()}:8081/"

    private fun productionBaseUrl() =
        "http://api.sabajafarzadeh.com:30000/"

}
