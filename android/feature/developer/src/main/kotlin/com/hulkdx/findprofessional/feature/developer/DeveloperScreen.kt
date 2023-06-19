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
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hulkdx.findprofessional.common.config.storage.DeveloperStorage
import com.hulkdx.findprofessional.common.config.storage.DeveloperStorage.Key.MockData
import com.hulkdx.findprofessional.core.theme.AppTheme
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@Composable
fun DeveloperScreen() {
    val storage = koinInject<DeveloperStorage>()
    val scope = rememberCoroutineScope()

    val useMockDataFlow = storage.getAsFlowBoolean(MockData).collectAsStateWithLifecycle(false)
    val useMockData = useMockDataFlow.value ?: false

    DeveloperScreen(
        mockData = useMockData,
        onMockDataChanged = { scope.launch { storage.setAsBoolean(MockData, !useMockData) } },
    )
}

@Composable
fun DeveloperScreen(
    mockData: Boolean,
    onMockDataChanged: ((Boolean) -> Unit),
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
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    AppTheme {
        DeveloperScreen(
            mockData = true,
            onMockDataChanged = {},
        )
    }
}

