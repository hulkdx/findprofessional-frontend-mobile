package com.hulkdx.findprofessional.core.navigation

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable

@Serializable(with = NavigationScreenSerializer::class)
@Polymorphic
abstract class NavigationScreen
