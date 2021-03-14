package com.hulkdx.findprofessional.utils

import androidx.annotation.StringRes
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithText

inline fun AndroidComposeTestRule<*, *>.onNodeWithTextRes(@StringRes res: Int): SemanticsNodeInteraction {
    return onNodeWithText(activity.getString(res))
}
