package com.hulkdx.findprofessional.tests.screen.book.time

import com.hulkdx.findprofessional.utils.ScreenTest
import org.junit.Test

class BookingTimeScreenTest : ScreenTest() {
    @Test
    fun performContinue() {
        launchBookingTimeScreen(composeRule) {
            pressContinue()
        }.verify {
            bookingSummeryShown()
        }
    }
/* TODO
    @Test
    fun performHighlightSomeTime() {
        launchBookingTimeScreen(composeRule) {
            pressTime(todayAvailableTime)
        }.verify {
            isHighlightedTime(todayAvailableTime)
        }
    }
 */
}
