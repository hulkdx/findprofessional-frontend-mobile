@file:OptIn(ExperimentalCoroutinesApi::class)

package com.hulkdx.findprofessional.feature.booking.summery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hulkdx.findprofessional.core.navigation.Navigator
import com.hulkdx.findprofessional.core.resources.Res
import com.hulkdx.findprofessional.core.resources.bookingFailed
import com.hulkdx.findprofessional.core.resources.paymentsUnderReview
import com.hulkdx.findprofessional.core.utils.StringOrRes
import com.hulkdx.findprofessional.core.utils.generalError
import com.hulkdx.findprofessional.core.utils.toStringOrRes
import com.hulkdx.findprofessional.feature.booking.summery.BookingSummeryUiState.CheckoutStatus
import com.hulkdx.findprofessional.feature.booking.summery.stripe.PaymentSheetResult
import com.hulkdx.findprofessional.feature.booking.summery.usecase.BookingSummeryUseCase
import com.hulkdx.findprofessional.feature.booking.summery.usecase.CheckBookingStatusUseCase
import com.hulkdx.findprofessional.feature.booking.summery.usecase.CreateBookingUseCase
import com.hulkdx.findprofessional.feature.home.HomeNavigationScreen
import com.hulkdx.findprofessional.feature.pro.model.Professional
import com.hulkdx.findprofessional.feature.pro.model.ProfessionalAvailability
import com.hulkdx.findprofessional.feature.pro.model.response.GetBookingStatusResponse.Status.FAILED
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookingSummeryViewModel(
    private val professional: Professional,
    private val availabilities: List<ProfessionalAvailability>,
    useCase: BookingSummeryUseCase,
    private val createBookingUseCase: CreateBookingUseCase,
    private val checkBookingStatusUseCase: CheckBookingStatusUseCase,
    private val navigator: Navigator,
) : ViewModel() {

    private val _uiState = MutableStateFlow(BookingSummeryUiState())
    val uiState = _uiState.asStateFlow()

    private var bookingId: Long? = null

    init {
        viewModelScope.launch {
            useCase.getSummeryDetails(professional, availabilities)
                .collect { summaryDetails ->
                    _uiState.update { it.copy(summeryDetails = summaryDetails) }
                }
        }
    }

    fun onCheckoutClicked() {
        viewModelScope.launch {
            setLoading(true)
            createBookingUseCase.execute(
                _uiState.value.summeryDetails.amountInCents,
                _uiState.value.summeryDetails.currency,
                availabilities,
                professional,
            )
                .onFailure { throwable ->
                    setLoading(false)
                    setError(throwable.generalError())
                }
                .onSuccess {
                    bookingId = it.id
                    setCheckoutStatus(CheckoutStatus.Success(it))
                }
        }
    }

    fun onStripeResult(result: PaymentSheetResult) {
        setCheckoutStatus(CheckoutStatus.Idle)

        when (result) {
            is PaymentSheetResult.Failed -> {
                setError(result.error.generalError())
                setLoading(false)
            }

            is PaymentSheetResult.Canceled -> {
                setLoading(false)
            }

            is PaymentSheetResult.Completed -> {
                checkBookingStatus()
            }
        }
    }

    private fun checkBookingStatus() = viewModelScope.launch {
        val bookingId = bookingId
        if (bookingId == null) {
            showBookingFailure()
            return@launch
        }
        val result = checkBookingStatusUseCase.execute(bookingId)
        if (result.isFailure || result.getOrNull() == FAILED) {
            showBookingFailure()
            return@launch
        }
        showBookingSuccess()
    }

    private fun showBookingFailure() {
        _uiState.update { uiState ->
            uiState.copy(
                isLoading = false,
                error = Res.string.bookingFailed.toStringOrRes()
            )
        }
    }

    private fun showBookingSuccess() {
        navigator.navigate(
            screen = HomeNavigationScreen.Home(Res.string.paymentsUnderReview.toStringOrRes()),
            popTo = HomeNavigationScreen.Home(),
            inclusive = true
        )
    }

    fun setCheckoutStatus(checkoutStatus: CheckoutStatus) =
        _uiState.update { uiState -> uiState.copy(checkoutStatus = checkoutStatus) }

    fun setError(error: StringOrRes?) =
        _uiState.update { uiState -> uiState.copy(error = error) }

    fun setLoading(loading: Boolean) =
        _uiState.update { uiState -> uiState.copy(isLoading = loading) }
}
