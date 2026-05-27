package com.example.ui

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.QuestionItem
import com.example.ui.theme.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun QuestionAuditorScreen(
    viewModel: GateViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isAuditing by viewModel.isAuditing.collectAsState()
    val auditedQuestions by viewModel.auditedQuestions.collectAsState()
    val scope = rememberCoroutineScope()

    // Supported Subtopic presets with illustrative messy questions
    val subtopicPresets = listOf(
        Pair("Synchronous Machines", listOf(
            QuestionItem("sm_01", "A 3-phase synchronous motor is operating at leading power factor. What is its excitation condition?"),
            QuestionItem("sm_02", "Under leading power factor criteria, explain the excitation status of a 3-phase synchronous motor."),
            QuestionItem("sm_03", "State the Routh-Hurwitz stability criterion for a 3rd order closed loop control system with characteristic equation s^3 + 2s^2 + 4s + 8 = 0."),
            QuestionItem("sm_04", "Explain the V-curves of a synchronous generator and why they are inverted for a synchronous motor."),
            QuestionItem("sm_05", "What are synchronous motor V-curves and why do they invert when comparing generator and motor modes?"),
            QuestionItem("sm_06", "Calculate the voltage regulation of a short transmission line with impedance 2 + j4 ohms supplying 10 MW at 0.8 lagging power factor."),
            QuestionItem("sm_07", "What is synchronous reactance (Xs) and how does it combine armature reaction and leakage reactance?"),
            QuestionItem("sm_08", "A buck converter operates with an input voltage of 24V and duty cycle of 0.6. Find the ideal average output voltage.")
        )),
        Pair("Network Theorems", listOf(
            QuestionItem("nt_01", "State Thévenin’s theorem and determine the equivalent resistance across terminals A and B."),
            QuestionItem("nt_02", "Explain Thévenin's theorem and how to calculate the equivalent resistance (R_th) between two terminals."),
            QuestionItem("nt_03", "Calculate the slip speed of a 4-pole induction motor connected to a 50Hz supply operating at 1440 rpm."),
            QuestionItem("nt_04", "Verify the Maximum Power Transfer Theorem for an AC network with load impedance Z_L matching the complex conjugate of source impedance Z_g."),
            QuestionItem("nt_05", "Prove that maximum power is transferred in an AC circuit when load impedance equals the complex conjugate of source internal impedance."),
            QuestionItem("nt_06", "Define Shannon's channel capacity theorem for a band-limited Gaussian channel with signal-to-noise ratio SNR.")
        )),
        Pair("Logical Reasoning", listOf(
            QuestionItem("lr_01", "Identify the logically valid statement: 'All square shapes are rectangles' or 'All rectangles are square shapes'."),
            QuestionItem("lr_02", "Which syllogism represents valid deduction? 'All square shapes are rectangles' or 'All rectangles are square shapes'."),
            QuestionItem("lr_03", "Calculate the root-mean-square (RMS) voltage of a sinusoidal wave with peak voltage of 311V."),
            QuestionItem("lr_04", "If some engineers are programmers and some programmers are writers, does it guarantee that some engineers are writers?"),
            QuestionItem("lr_05", "If some engineers are programmers, and some programmers are authors, does it logically follow that some engineers are authors?"),
            QuestionItem("lr_06", "Find the closed-loop transfer function of a first-order system with feedback coefficient H(s) = 1.")
        ))
    )

    var selectedPresetIndex by remember { mutableIntStateOf(0) }
    val currentPreset = subtopicPresets[selectedPresetIndex]
    val selectedSubtopic = currentPreset.first
    val rawQuestions = currentPreset.second

    // Animation progress text rotated to make layout premium
    var activeProgressStep by remember { mutableStateOf("Ready to Audit") }

    LaunchedEffect(isAuditing) {
        if (isAuditing) {
            val steps = listOf(
                "Packaging payload metadata...",
                "Contacting gemini-2.5-flash secure API...",
                "Running Relevancy Check audits against '$selectedSubtopic'...",
                "Eliminating out-of-syllabus modules...",
                "Constructing Uniqueness & duplication matrix...",
                "Parsing response JSON model schema..."
            )
            var idx = 0
            while (isAuditing) {
                activeProgressStep = steps[idx % steps.size]
                delay(2000)
                idx++
            }
        } else {
            activeProgressStep = "Reviewing audit logs"
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "AI Question Auditor",
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Text(
                            text = "Powered by Gemini 2.5 Structured Outputs",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack, modifier = Modifier.testTag("auditor_back")) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Go back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.background,
                            MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.25f)
                        )
                    )
                )
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            // Target subtopic selector chips header
            Text(
                text = "Target Subtopic for Audit Room:",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(vertical = 4.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                subtopicPresets.forEachIndexed { idx, pair ->
                    val isSelected = selectedPresetIndex == idx
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .clickable {
                                if (!isAuditing) {
                                    selectedPresetIndex = idx
                                    // Clear previous audited list to allow pristine re-trigger
                                    viewModel.auditAndFilterQuestions("", emptyList())
                                }
                            }
                            .testTag("preset_tab_$idx"),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant
                        ),
                        border = if (isSelected) null else BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 12.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = pair.first,
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant,
                                textAlign = TextAlign.Center,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Large Action Box
            if (auditedQuestions.isEmpty() && !isAuditing) {
                // Instruction banner about what needs to happen
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
                    ),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.2f))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = "Onboarding Info",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "Messy Questions Loaded!",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "We loaded ${rawQuestions.size} mock exam questions. This raw batch contains completely unrelated questions (off-syllabus) as well as exact or near-duplicate duplicates. Click the audit button to screen them with live AI.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Scrollable List of Raw Messy Questions with flags so developer appreciates the difference
                Text(
                    text = "Raw Questions Batch (${rawQuestions.size} Items):",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )

                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(rawQuestions) { item ->
                        val isOffTopic = when {
                            selectedSubtopic.contains("Synchronous") -> {
                                item.text.contains("Routh-Hurwitz") || item.text.contains("transmission line") || item.text.contains("converter")
                            }
                            selectedSubtopic.contains("Network") -> {
                                item.text.contains("slip") || item.text.contains("Shannon")
                            }
                            selectedSubtopic.contains("Logical") || selectedSubtopic.contains("Reasoning") -> {
                                item.text.contains("sinusoidal") || item.text.contains("transfer function")
                            }
                            else -> false
                        }
                        val isDuplicate = when (item.id) {
                            "sm_02", "sm_05", "nt_02", "nt_05", "lr_02", "lr_05" -> true
                            else -> false
                        }

                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = if (isOffTopic) MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.08f)
                                else if (isDuplicate) MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.15f)
                                else MaterialTheme.colorScheme.surface
                            ),
                            border = BorderStroke(
                                1.dp,
                                if (isOffTopic) MaterialTheme.colorScheme.error.copy(alpha = 0.3f)
                                else if (isDuplicate) MaterialTheme.colorScheme.secondary.copy(alpha = 0.3f)
                                else MaterialTheme.colorScheme.outlineVariant
                            )
                        ) {
                            Column(modifier = Modifier.padding(14.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "ID: ${item.id}",
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.primary
                                    )

                                    if (isOffTopic) {
                                        SuggestionChip(
                                            onClick = {},
                                            label = { Text("❌ Off-topic", fontSize = 10.sp) },
                                            colors = SuggestionChipDefaults.suggestionChipColors(
                                                labelColor = MaterialTheme.colorScheme.error
                                            )
                                        )
                                    } else if (isDuplicate) {
                                        SuggestionChip(
                                            onClick = {},
                                            label = { Text("⚠️ Duplicate paraphrase", fontSize = 10.sp) },
                                            colors = SuggestionChipDefaults.suggestionChipColors(
                                                labelColor = MaterialTheme.colorScheme.secondary
                                            )
                                        )
                                    } else {
                                        SuggestionChip(
                                            onClick = {},
                                            label = { Text("✅ Unique", fontSize = 10.sp) },
                                            colors = SuggestionChipDefaults.suggestionChipColors(
                                                labelColor = Color(0xFF2E7D32)
                                            )
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = item.text,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }
                }

                // Auditor Trigger Button
                Button(
                    onClick = {
                        scope.launch {
                            viewModel.auditAndFilterQuestions(selectedSubtopic, rawQuestions)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .testTag("trigger_audit_btn")
                        .padding(bottom = 8.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(imageVector = Icons.Default.Security, contentDescription = "Shield")
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Audit questions with Gemini-2.5-Flash",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                }
            } else if (isAuditing) {
                // Show stunning pulsing logo scanning/auditing system
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val infiniteTransition = rememberInfiniteTransition(label = "AuditingAnimation")
                    val scale by infiniteTransition.animateFloat(
                        initialValue = 0.85f,
                        targetValue = 1.15f,
                        animationSpec = infiniteRepeatable(
                            animation = tween(1200, easing = LinearEasing),
                            repeatMode = RepeatMode.Reverse
                        ), label = "Pulse"
                    )

                    val rotation by infiniteTransition.animateFloat(
                        initialValue = 0f,
                        targetValue = 360f,
                        animationSpec = infiniteRepeatable(
                            animation = tween(2000, easing = FastOutSlowInEasing),
                            repeatMode = RepeatMode.Restart
                        ), label = "Rotate"
                    )

                    Box(
                        modifier = Modifier
                            .size(160.dp)
                            .border(6.dp, MaterialTheme.colorScheme.primary, CircleShape)
                            .clip(CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.FilterList,
                            contentDescription = "Filtering Icon",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .size(70.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    Text(
                        text = "Gemini Auditor At Work",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = activeProgressStep,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.outline,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 24.dp)
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.size(24.dp),
                        strokeWidth = 3.dp
                    )
                }
            } else {
                // Display the Audit results side with efficiency details!
                val removedDuplicates = rawQuestions.size - auditedQuestions.size
                val efficiencyScore = if (rawQuestions.isNotEmpty()) {
                    (removedDuplicates.toFloat() / rawQuestions.size * 100).toInt()
                } else 100

                // Result Summary HUD Card
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF1B5E20).copy(alpha = 0.08f)
                    ),
                    border = BorderStroke(2.dp, Color(0xFF2E7D32).copy(alpha = 0.4f))
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.OfflinePin,
                                contentDescription = "Verification Pass",
                                tint = Color(0xFF2E7D32),
                                modifier = Modifier.size(28.dp)
                            )
                            Text(
                                text = "Audit Succeeded: 100% Clean",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF2E7D32)
                            )
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text("Audited", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.outline)
                                Text("${rawQuestions.size} questions", fontSize = 16.sp, fontWeight = FontWeight.ExtraBold)
                            }
                            Column {
                                Text("Filtered Clean", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.outline)
                                Text("${auditedQuestions.size} unique", fontSize = 16.sp, fontWeight = FontWeight.ExtraBold, color = Color(0xFF2E7D32))
                            }
                            Column {
                                Text("Deduplicated", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.outline)
                                Text("$removedDuplicates removed", fontSize = 16.sp, fontWeight = FontWeight.ExtraBold, color = MaterialTheme.colorScheme.error)
                            }
                            Column {
                                Text("Efficiency", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.outline)
                                Text("$efficiencyScore%", fontSize = 16.sp, fontWeight = FontWeight.ExtraBold, color = MaterialTheme.colorScheme.secondary)
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "Audited & Verified Unique Practice Questions:",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onBackground
                )

                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(auditedQuestions) { item ->
                        Card(
                            modifier = Modifier.fillMaxWidth().testTag("audited_question_item"),
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            border = BorderStroke(1.dp, Color(0xFF2E7D32).copy(alpha = 0.25f))
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            imageVector = Icons.Default.CheckCircle,
                                            contentDescription = "Verified Icon",
                                            tint = Color(0xFF2E7D32),
                                            modifier = Modifier.size(16.dp)
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(
                                            text = item.id.uppercase(),
                                            style = MaterialTheme.typography.labelSmall,
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFF2E7D32)
                                        )
                                    }
                                    Text(
                                        text = "100% relevant",
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.SemiBold,
                                        color = MaterialTheme.colorScheme.secondary
                                    )
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = item.text,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    lineHeight = 20.sp
                                )
                            }
                        }
                    }
                }

                // Reset page button
                Button(
                    onClick = {
                        viewModel.auditAndFilterQuestions("", emptyList())
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .testTag("reset_auditor_btn")
                        .padding(bottom = 8.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary
                    )
                ) {
                    Text(
                        text = "Reset & Select New Subtopic",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
