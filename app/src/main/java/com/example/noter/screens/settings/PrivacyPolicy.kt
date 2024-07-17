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
fun PrivacyPolicyScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Privacy Policy") },
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
                text = "Privacy Policy",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            PrivacyPolicyParagraph("Your Privacy")
            Text(
                text = "We respect your privacy and are committed to protecting any personal information you may provide while using our application. This privacy policy outlines how we handle and safeguard your data.",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(22.dp))

            PrivacyPolicyParagraph("Information Collection and Use")
            Text(
                text = "Our notes application does not require an internet connection and does not collect, transmit, or store any personal data on external servers. All data, including notes and preferences, are stored locally on your device using the Room database.",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(22.dp))

            PrivacyPolicyParagraph("Data Storage and Security")
            Text(
                text = "We use the Room database to store your notes and related information locally on your device. We implement reasonable security measures to protect your data from unauthorized access, alteration, disclosure, or destruction. However, please be aware that no security measure is perfect or impenetrable.",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(22.dp))

            PrivacyPolicyParagraph("Changes to This Privacy Policy")
            Text(
                text = "We may update our privacy policy from time to time. We will notify you of any changes by posting the new privacy policy within the application. You are advised to review this policy periodically for any changes.",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(22.dp))

            PrivacyPolicyParagraph("Contact Us")
            Text(
                text = "If you have any questions about this privacy policy, please contact us at poplivedant18@gmail.com.",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun PrivacyPolicyParagraph(heading: String) {
    Text(
        text = heading,
        style = MaterialTheme.typography.headlineSmall,
        fontWeight = FontWeight.W500
    )
    Spacer(modifier = Modifier.height(10.dp))
}
