package com.hulkdx.findprofessional.feature.mybookings

import androidx.lifecycle.ViewModel
import com.hulkdx.findprofessional.feature.mybookings.model.MyBookingSegment
import com.hulkdx.findprofessional.feature.mybookings.usecase.GetMyBookingsUseCase

class MyBookingsViewModel(
    private val getMyBookingsUseCase: GetMyBookingsUseCase,
) : ViewModel() {
    fun onSegmentSelected(segment: MyBookingSegment) {
    }

    fun onClickCancel() {
    }

    fun onClickReportProblem() {
    }

    fun onErrorDismissed() {
    }

    fun onClickJoinSession() {
    }

    fun onClickCopyBookingId() {
    }
}
