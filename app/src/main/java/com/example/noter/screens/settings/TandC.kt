package com.example.noter.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TermsAndConditionsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Terms and Conditions") },
                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "Terms and Conditions",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            TermsAndConditionsParagraph("Acceptance of Terms")
            Text(
                text = "By using our notes application, you agree to be bound by these terms and conditions. If you do not agree to these terms, please do not use the application.",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(22.dp))

            TermsAndConditionsParagraph("License")
            Text(
                text = "We grant you a limited, non-exclusive, non-transferable, and revocable license to use the application for personal use only. You may not modify, distribute, or create derivative works based on the application without our prior written consent.",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(22.dp))

            TermsAndConditionsParagraph("User Responsibilities")
            Text(
                text = "You are responsible for maintaining the confidentiality of your data and for any activities that occur under your use of the application. You agree not to use the application for any unlawful or prohibited activities.",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(22.dp))

            TermsAndConditionsParagraph("Data Backup")
            Text(
                text = "As the application stores data locally on your device, it is your responsibility to back up your data regularly. We are not responsible for any data loss or corruption.",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(22.dp))

            TermsAndConditionsParagraph("Modification and Termination")
            Text(
                text = "We reserve the right to modify or discontinue the application at any time without notice. We also reserve the right to terminate your access to the application if you violate these terms and conditions.",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(22.dp))

            TermsAndConditionsParagraph("Limitation of Liability")
            Text(
                text = "In no event shall we be liable for any indirect, incidental, special, or consequential damages arising out of or in connection with the use or inability to use the application.",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(22.dp))

            TermsAndConditionsParagraph("Governing Law")
            Text(
                text = "These terms and conditions are governed by and construed in accordance with the laws of [your jurisdiction]. Any disputes arising from or relating to these terms shall be resolved in the courts of [your jurisdiction].",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(22.dp))

            TermsAndConditionsParagraph("Contact Us")
            Text(
                text = "If you have any questions about these terms and conditions, please contact us at [your email address].",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun TermsAndConditionsParagraph(heading: String) {
    Text(
        text = heading,
        style = MaterialTheme.typography.headlineSmall,
        fontWeight = FontWeight.W500
    )
    Spacer(modifier = Modifier.height(10.dp))
}
