package com.hulkdx.findprofessional.feature.mybookings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hulkdx.findprofessional.core.utils.StringOrRes
import com.hulkdx.findprofessional.core.utils.generalError
import com.hulkdx.findprofessional.feature.mybookings.api.Booking
import com.hulkdx.findprofessional.feature.mybookings.model.BookingUiState
import com.hulkdx.findprofessional.feature.mybookings.model.BookingUiState.Item
import com.hulkdx.findprofessional.feature.mybookings.model.MyBookingSegment
import com.hulkdx.findprofessional.feature.mybookings.usecase.GetMyBookingsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.TimeZone
import kotlin.time.Clock
import kotlin.time.Instant

class MyBookingsViewModel(
    private val getMyBookingsUseCase: GetMyBookingsUseCase,
    private val now: Instant = Clock.System.now(),
    private val timeZone: TimeZone = TimeZone.currentSystemDefault(),
) : ViewModel() {
    private val _uiState = MutableStateFlow(BookingUiState())
    val uiState: StateFlow<BookingUiState> = _uiState.asStateFlow()

    private var cachedBookings = listOf<Booking>()

    init {
        onRefresh()
    }

    fun onRefresh() {
        viewModelScope.launch {
            setLoading(true)
            getMyBookingsUseCase.execute()
                .onFailure {
                    setError(it.generalError())
                }
                .onSuccess {
                    cachedBookings = it
                    applySegment()
                }
            setLoading(false)
        }
    }

    fun onSegmentSelected(segment: MyBookingSegment) {
        if (segment == _uiState.value.segment) {
            return
        }
        setSegment(segment)
        applySegment()
    }

    private fun applySegment() {
        val segment = _uiState.value.segment
        setItems(cachedBookings.toUiState(segment, now, timeZone))
    }

    fun onClickCancel() {
    }

    fun onClickReportProblem() {
    }

    fun onErrorDismissed() {
        setError(null)
    }

    fun onClickJoinSession() {
    }

    fun onClickCopyBookingId() {
    }

    private fun setLoading(loading: Boolean) = _uiState.update { it.copy(isLoading = loading) }
    private fun setError(error: StringOrRes?) = _uiState.update { it.copy(error = error) }
    private fun setItems(items: List<Item>) = _uiState.update { it.copy(items = items) }
    private fun setSegment(sgmt: MyBookingSegment) = _uiState.update { it.copy(segment = sgmt) }
}
