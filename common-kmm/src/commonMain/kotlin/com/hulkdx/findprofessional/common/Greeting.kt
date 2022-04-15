package com.hulkdx.findprofessional.common

import com.hulkdx.findprofessional.common.Platform

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}
