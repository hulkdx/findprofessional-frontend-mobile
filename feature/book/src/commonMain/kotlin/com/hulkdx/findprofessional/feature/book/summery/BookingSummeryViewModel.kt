@file:OptIn(ExperimentalCoroutinesApi::class)

package com.hulkdx.findprofessional.feature.book.summery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hulkdx.findprofessional.core.features.book.SelectedTimes
import com.hulkdx.findprofessional.core.features.pro.model.Professional
import com.hulkdx.findprofessional.core.navigation.NavigationScreen
import com.hulkdx.findprofessional.core.navigation.Navigator
import com.hulkdx.findprofessional.core.utils.StringOrRes
import com.hulkdx.findprofessional.core.utils.generalError
import com.hulkdx.findprofessional.feature.book.summery.BookingSummeryUiState.CheckoutStatus
import com.hulkdx.findprofessional.feature.book.summery.api.PayRequest
import com.hulkdx.findprofessional.feature.book.summery.stripe.PaymentSheetResult
import com.hulkdx.findprofessional.feature.book.summery.usecase.BookingSummeryUseCase
import com.hulkdx.findprofessional.feature.book.summery.usecase.CheckoutUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookingSummeryViewModel(
    professional: Professional,
    times: SelectedTimes,
    useCase: BookingSummeryUseCase,
    private val checkoutUseCase: CheckoutUseCase,
    private val navigator: Navigator,
) : ViewModel() {
    private val _uiState = MutableStateFlow(BookingSummeryUiState())
    val uiState = useCase.getSummeryDetails(professional, times)
        .flatMapLatest {
            _uiState.apply {
                update { uiState -> uiState.copy(summeryDetails = it) }
            }
        }
        .stateIn(viewModelScope, WhileSubscribed(5_000), BookingSummeryUiState())


    fun onCheckoutClicked() {
        viewModelScope.launch {
            setCheckoutStatus(CheckoutStatus.Loading)
            // TODO: get the real values
            // TODO: change the api to the pro
            checkoutUseCase.execute(PayRequest(1000, "EUR"))
                .onFailure { throwable ->
                    setError(throwable.generalError())
                }
                .onSuccess {
                    setCheckoutStatus(CheckoutStatus.Success(it))
                }
        }
    }

    fun onStripeResult(result: PaymentSheetResult) {
        // TODO: add a stripe webhook to the server, wait over here to show confirmations
        println(result)
    }

    fun onEditSkypeIdClicked() {
        navigator.navigate(NavigationScreen.ProfileEdit)
    }

    fun setCheckoutStatus(checkoutStatus: CheckoutStatus) =
        _uiState.update { uiState -> uiState.copy(checkoutStatus = checkoutStatus) }

    fun setError(error: StringOrRes?) =
        _uiState.update { uiState -> uiState.copy(error = error) }
}
