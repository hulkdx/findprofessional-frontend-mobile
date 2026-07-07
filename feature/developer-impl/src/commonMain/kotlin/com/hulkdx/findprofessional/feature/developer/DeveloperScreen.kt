package com.hulkdx.findprofessional.feature.developer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hulkdx.findprofessional.core.ui.theme.AppTheme
import com.hulkdx.findprofessional.feature.developer.storage.DeveloperStorage
import com.hulkdx.findprofessional.feature.developer.storage.DeveloperStorage.Key.AuthPrefill
import com.hulkdx.findprofessional.feature.developer.storage.DeveloperStorage.Key.MockData
import com.hulkdx.findprofessional.feature.developer.storage.DeveloperStorage.Key.UseProductionBaseUrl
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject

@Composable
fun DeveloperScreen() {
    val storage = koinInject<DeveloperStorage>()
    val scope = rememberCoroutineScope()

    val useMockDataFlow = storage.getAsFlowBoolean(MockData).collectAsStateWithLifecycle(false)
    val useMockData = useMockDataFlow.value ?: false
    val useAuthPrefillFlow = storage.getAsFlowBoolean(AuthPrefill).collectAsStateWithLifecycle(false)
    val useAuthPrefill = useAuthPrefillFlow.value ?: false
    val useProductionBaseUrlFlow =
        storage.getAsFlowBoolean(UseProductionBaseUrl).collectAsStateWithLifecycle(false)
    val useProductionBaseUrl = useProductionBaseUrlFlow.value ?: false

    DeveloperScreen(
        mockData = useMockData,
        onMockDataChanged = {
            scope.launch { storage.setAsBoolean(MockData, it) }
        },
        authPrefill = useAuthPrefill,
        onAuthPrefillChanged = {
            scope.launch { storage.setAsBoolean(AuthPrefill, it) }
        },
        productionBaseUrl = useProductionBaseUrl,
        onProductionBaseUrlChanged = { value ->
            scope.launch { storage.setAsBoolean(UseProductionBaseUrl, value) }
        },
    )
}

@Composable
fun DeveloperScreen(
    mockData: Boolean,
    onMockDataChanged: ((Boolean) -> Unit),
    authPrefill: Boolean,
    onAuthPrefillChanged: ((Boolean) -> Unit),
    productionBaseUrl: Boolean,
    onProductionBaseUrlChanged: ((Boolean) -> Unit),
) {
    Column(
        modifier = Modifier
            .testTag("DeveloperScreen")
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
            .systemBarsPadding(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = mockData,
                onCheckedChange = onMockDataChanged
            )
            Text(text = "Use Mock Data")
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = authPrefill,
                onCheckedChange = onAuthPrefillChanged
            )
            Text(text = "Prefill Auth Data")
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = productionBaseUrl,
                onCheckedChange = onProductionBaseUrlChanged
            )
            Text(text = "Use Production API URL")
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    AppTheme {
        DeveloperScreen(
            mockData = true,
            onMockDataChanged = {},
            authPrefill = true,
            onAuthPrefillChanged = {},
            productionBaseUrl = false,
            onProductionBaseUrlChanged = {},
        )
    }
}
