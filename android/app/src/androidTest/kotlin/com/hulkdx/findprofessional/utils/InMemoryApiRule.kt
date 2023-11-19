package com.hulkdx.findprofessional.utils

import com.hulkdx.findprofessional.InMemoryApi
import org.junit.rules.ExternalResource
import org.junit.runner.Description
import org.junit.runners.model.Statement

class InMemoryApiRule : ExternalResource() {
    override fun before() {
        InMemoryApi.loadKoinModules()
    }

    override fun after() {
        InMemoryApi.unloadKoinModules()
    }
}
