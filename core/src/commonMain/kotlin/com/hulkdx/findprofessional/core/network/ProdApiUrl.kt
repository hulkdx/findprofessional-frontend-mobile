package com.hulkdx.findprofessional.core.network

object ProdApiUrl : ApiUrl {
    override fun of(path: String): String {
        return "$PROD_BASE_URL/$path"
    }
}
