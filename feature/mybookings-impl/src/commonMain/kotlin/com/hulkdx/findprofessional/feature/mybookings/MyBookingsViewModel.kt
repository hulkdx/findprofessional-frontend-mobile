package com.hulkdx.findprofessional.feature.mybookings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hulkdx.findprofessional.core.resources.Res
import com.hulkdx.findprofessional.core.resources.cancellationNotSupported
import com.hulkdx.findprofessional.core.resources.noCoachSessionLink
import com.hulkdx.findprofessional.core.utils.ClockProvider
import com.hulkdx.findprofessional.core.utils.REPORT_PROBLEM_URL
import com.hulkdx.findprofessional.core.utils.StringOrRes
import com.hulkdx.findprofessional.core.utils.generalError
import com.hulkdx.findprofessional.core.utils.toStringOrRes
import com.hulkdx.findprofessional.feature.mybookings.model.BookingUiState
import com.hulkdx.findprofessional.feature.mybookings.model.BookingUiState.Item
import com.hulkdx.findprofessional.feature.mybookings.model.BookingUiState.Navigation
import com.hulkdx.findprofessional.feature.mybookings.model.BookingUiState.Navigation.OpenUrl
import com.hulkdx.findprofessional.feature.mybookings.model.MyBookingSegment
import com.hulkdx.findprofessional.feature.mybookings.usecase.GetMyBookingsUseCase
import com.hulkdx.findprofessional.feature.pro.model.Booking
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MyBookingsViewModel(
    private val getMyBookingsUseCase: GetMyBookingsUseCase,
    private val clockProvider: ClockProvider,
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
        setItems(
            cachedBookings.toUiState(
                segment,
                clockProvider.clock().now(),
                clockProvider.defaultTimeZone(),
            )
        )
    }

    fun onClickJoinSession(item: Item) {
        val sessionLink = item.session.sessionLink?.takeIf { it.isNotBlank() }
        if (sessionLink != null) {
            setNavigation(OpenUrl(sessionLink))
        } else {
            setError(Res.string.noCoachSessionLink.toStringOrRes())
        }
    }

    fun onClickCancel() {
        setError(Res.string.cancellationNotSupported.toStringOrRes())
    }

    fun onClickReportProblem() {
        setNavigation(OpenUrl(REPORT_PROBLEM_URL))
    }

    fun onNavigated() {
        if (_uiState.value.navigation == null) {
            return
        }
        setNavigation(null)
    }

    fun onErrorDismissed() {
        setError(null)
    }

    fun setError(error: StringOrRes?) = _uiState.update { it.copy(error = error) }
    private fun setLoading(loading: Boolean) = _uiState.update { it.copy(isLoading = loading) }
    private fun setItems(items: List<Item>) = _uiState.update { it.copy(items = items) }
    private fun setSegment(sgmt: MyBookingSegment) = _uiState.update { it.copy(segment = sgmt) }
    private fun setNavigation(n: Navigation?) = _uiState.update { it.copy(navigation = n) }
}
