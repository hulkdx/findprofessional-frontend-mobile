package com.hulkdx.findprofessional.feature.pro.schedule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hulkdx.findprofessional.core.resources.Res
import com.hulkdx.findprofessional.core.resources.noCoachSessionLink
import com.hulkdx.findprofessional.core.utils.ClockProvider
import com.hulkdx.findprofessional.core.utils.StringOrRes
import com.hulkdx.findprofessional.core.utils.generalError
import com.hulkdx.findprofessional.core.utils.toStringOrRes
import com.hulkdx.findprofessional.feature.pro.model.Booking
import com.hulkdx.findprofessional.feature.pro.schedule.mapper.toUiState
import com.hulkdx.findprofessional.feature.pro.schedule.model.ScheduleUiState
import com.hulkdx.findprofessional.feature.pro.schedule.model.ScheduleUiState.Item
import com.hulkdx.findprofessional.feature.pro.schedule.model.ScheduleUiState.Navigation
import com.hulkdx.findprofessional.feature.pro.schedule.model.Segment
import com.hulkdx.findprofessional.feature.pro.schedule.usecase.GetScheduleUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProScheduleViewModel(
    private val useCase: GetScheduleUseCase,
    private val clockProvider: ClockProvider,
) : ViewModel() {
    private val _uiState = MutableStateFlow(ScheduleUiState())
    val state = _uiState.asStateFlow()

    private var cachedBookings = listOf<Booking>()

    init {
        onRefresh()
    }

    fun onRefresh() {
        viewModelScope.launch {
            setRefreshing(true)
            useCase.execute()
                .onFailure {
                    setError(it.generalError())
                }
                .onSuccess {
                    cachedBookings = it
                    applySegment()
                }
            setRefreshing(false)
        }
    }

    fun onSegmentSelected(segment: Segment) {
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
            setNavigation(Navigation.OpenUrl(sessionLink))
        } else {
            setError(Res.string.noCoachSessionLink.toStringOrRes())
        }
    }

    fun onNavigated() {
        if (_uiState.value.navigation == null) {
            return
        }
        setNavigation(null)
    }

    fun setSegment(sgmt: Segment) = _uiState.update { it.copy(segment = sgmt) }
    fun setRefreshing(r: Boolean) = _uiState.update { it.copy(isRefreshing = r) }
    fun setError(error: StringOrRes?) = _uiState.update { it.copy(error = error) }
    fun setItems(items: List<Item>) = _uiState.update { it.copy(items = items) }
    fun setNavigation(n: Navigation?) = _uiState.update { it.copy(navigation = n) }
}
