@file:OptIn(ExperimentalCoroutinesApi::class)

package com.hulkdx.findprofessional.feature.book.summery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hulkdx.findprofessional.core.features.pro.model.Professional
import com.hulkdx.findprofessional.core.features.pro.model.ProfessionalAvailability
import com.hulkdx.findprofessional.core.navigation.NavigationScreen
import com.hulkdx.findprofessional.core.navigation.Navigator
import com.hulkdx.findprofessional.core.utils.StringOrRes
import com.hulkdx.findprofessional.core.utils.generalError
import com.hulkdx.findprofessional.feature.book.summery.BookingSummeryUiState.CheckoutStatus
import com.hulkdx.findprofessional.feature.book.summery.stripe.PaymentSheetResult
import com.hulkdx.findprofessional.feature.book.summery.usecase.BookingSummeryUseCase
import com.hulkdx.findprofessional.feature.book.summery.usecase.CreateBookingUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookingSummeryViewModel(
    private val professional: Professional,
    private val availabilities: List<ProfessionalAvailability>,
    useCase: BookingSummeryUseCase,
    private val createBookingUseCase: CreateBookingUseCase,
    private val navigator: Navigator,
) : ViewModel() {
    private val _uiState = MutableStateFlow(BookingSummeryUiState())
    val uiState = useCase.getSummeryDetails(professional, availabilities)
        .flatMapLatest {
            _uiState.apply {
                update { uiState -> uiState.copy(summeryDetails = it) }
            }
        }
        .stateIn(viewModelScope, WhileSubscribed(5_000), BookingSummeryUiState())


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
                    setLoading(false)
                    setCheckoutStatus(CheckoutStatus.Success(it))
                }
        }
    }

    fun onStripeResult(result: PaymentSheetResult) {
        setLoading(false)
        setCheckoutStatus(CheckoutStatus.Idle)

        when (result) {
            is PaymentSheetResult.Failed -> {
                setError(result.error.generalError())
            }

            is PaymentSheetResult.Canceled -> {
            }

            is PaymentSheetResult.Completed -> {
                // TODO: write down we will check your payments
                navigator.goBack(NavigationScreen.Home::class)
            }
        }
    }

    fun onEditSkypeIdClicked() {
        navigator.navigate(NavigationScreen.ProfileEdit)
    }

    fun setCheckoutStatus(checkoutStatus: CheckoutStatus) =
        _uiState.update { uiState -> uiState.copy(checkoutStatus = checkoutStatus) }

    fun setError(error: StringOrRes?) =
        _uiState.update { uiState -> uiState.copy(error = error) }

    fun setLoading(loading: Boolean) =
        _uiState.update { uiState -> uiState.copy(isLoading = loading) }
}
