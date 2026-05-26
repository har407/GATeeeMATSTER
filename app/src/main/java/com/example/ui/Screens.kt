package com.example.ui

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.*
import com.example.ui.theme.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// --- MAIN GATE DASHBOARD SCREEN ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GateDashboardScreen(
    viewModel: GateViewModel,
    onSubjectClick: (Subject) -> Unit,
    onNavigateToBookmarks: () -> Unit,
    onNavigateToMistakes: () -> Unit,
    onStartMockTest: () -> Unit
) {
    val stats by viewModel.userStats.collectAsState()
    val progressList by viewModel.allProgress.collectAsState()

    // Interactive mock badges
    val badges = listOf(
        Pair("Gate Starter", Icons.Default.ThumbUp),
        Pair("Streak Master", Icons.Default.Whatshot),
        Pair("Formula Pro", Icons.Default.Calculate),
        Pair("Doubt Solver", Icons.Default.QuestionAnswer)
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Welcome and Streak HUD Card
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("welcome_streak_hud"),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "Welcome, GATE Aspirant!",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(
                                text = "Crush your targets today ⚡",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                            )
                        }
                        // Streak Indicator
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .background(
                                    Color.Black.copy(alpha = 0.15f),
                                    CircleShape
                                )
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Whatshot,
                                contentDescription = "Streak Flame",
                                tint = TechGoldXP,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "${stats.streak} Days",
                                style = MaterialTheme.typography.labelLarge,
                                fontWeight = FontWeight.ExtraBold,
                                color = TechGoldXP
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // XP system HUD
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Stars,
                            contentDescription = "XP Stars",
                            tint = TechGoldXP,
                            modifier = Modifier.size(28.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                val hours = stats.timeSpentMinutes / 60
                                val mins = stats.timeSpentMinutes % 60
                                val timeText = if (hours > 0) "Time Spent: ${hours}h ${mins}m" else "Time Spent: ${mins}m"
                                Text(
                                    text = timeText,
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                                Text(
                                    text = "${stats.totalXp % 100}/100 XP to next level",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            // Experience Progress bar
                            LinearProgressIndicator(
                                progress = { (stats.totalXp % 100) / 100f },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(10.dp)
                                    .clip(CircleShape),
                                color = TechGoldXP,
                                trackColor = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.15f)
                            )
                        }
                    }
                }
            }
        }

        // Core Hub Quick Actions (Saved, Mistakes, CBT Mock)
        item {
            Text(
                text = "My Revision Arsenal",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.ExtraBold
            )
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Bookmarked Questions Button Card
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .clickable { onNavigateToBookmarks() }
                        .testTag("bookmarks_button"),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.BookmarkBorder,
                            contentDescription = "Bookmark list",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(28.dp)
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "Bookmarks",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Starred questions",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f)
                        )
                    }
                }

                // Mistakes Notebook Button Card
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .clickable { onNavigateToMistakes() }
                        .testTag("mistakes_button"),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.HistoryEdu,
                            contentDescription = "Mistakes notebook",
                            tint = Color(0xFFCF1322),
                            modifier = Modifier.size(28.dp)
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "Mistakes",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Practice weak fields",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f)
                        )
                    }
                }
            }
        }

        // Live CBT Platform Simulator Trigger Card
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onStartMockTest() }
                    .border(
                        1.dp,
                        MaterialTheme.colorScheme.tertiary.copy(alpha = 0.3f),
                        RoundedCornerShape(20.dp)
                    )
                    .testTag("cbt_mock_test_card"),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Row(
                    modifier = Modifier.padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "CBT Test Platform",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.tertiary
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Complete subject combinations, negative marking (-1/3), timers, and immediate rank predictions.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .background(
                                MaterialTheme.colorScheme.tertiaryContainer,
                                RoundedCornerShape(12.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Launch,
                            contentDescription = "Start Test",
                            tint = MaterialTheme.colorScheme.onTertiaryContainer,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }
            }
        }

        // Subject Tracker Cards
        item {
            Text(
                text = "Official Syllabus Categories",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.ExtraBold
            )
        }

        items(viewModel.repository.subjects) { subject ->
            // Compute percentage
            val subjectProgress = progressList.filter { it.subjectId == subject.id }
            val totalSubtopics = subject.topics.flatMap { it.subtopics }.size
            val completedCount = subjectProgress.filter { it.isCompleted }.size
            val progressPercent = if (totalSubtopics > 0) {
                (completedCount.toFloat() / totalSubtopics.toFloat() * 100).toInt()
            } else {
                0
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSubjectClick(subject) }
                    .testTag("subject_card_${subject.id}"),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(52.dp)
                            .background(
                                MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f),
                                RoundedCornerShape(12.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        val iconVector = when (subject.iconName) {
                            "settings" -> Icons.Default.SettingsInputComponent
                            "bolt" -> Icons.Default.Bolt
                            "calculate" -> Icons.Default.Calculate
                            "menu_book" -> Icons.Default.MenuBook
                            "psychology" -> Icons.Default.Psychology
                            "share" -> Icons.Default.Share
                            "build" -> Icons.Default.Build
                            "flash_on" -> Icons.Default.FlashOn
                            "analytics" -> Icons.Default.Analytics
                            "tune" -> Icons.Default.Tune
                            "memory" -> Icons.Default.Memory
                            "speed" -> Icons.Default.Speed
                            "explore" -> Icons.Default.Explore
                            else -> Icons.Default.Book
                        }
                        Icon(
                            imageVector = iconVector,
                            contentDescription = subject.name,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(28.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = subject.name,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "$completedCount/$totalSubtopics Topics Checked",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                            )
                            Text(
                                text = "$progressPercent%",
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        LinearProgressIndicator(
                            progress = { progressPercent / 100f },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(6.dp)
                                .clip(CircleShape),
                            color = MaterialTheme.colorScheme.primary,
                            trackColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f)
                        )
                    }
                }
            }
        }

        // Achievements Badge Grid
        item {
            Text(
                text = "Aspirant Achievements",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
                        RoundedCornerShape(16.dp)
                    )
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                badges.forEach { badge ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.weight(1f)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .background(
                                    MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                                    CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = badge.second,
                                contentDescription = badge.first,
                                tint = TechGoldXP,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = badge.first,
                            style = MaterialTheme.typography.labelSmall,
                            fontSize = 10.sp,
                            textAlign = TextAlign.Center,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}

// --- SYLLABUS DETAIL DRILL DOWN SCREEN ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectDetailScreen(
    subject: Subject,
    onBack: () -> Unit,
    viewModel: GateViewModel,
    onSubtopicClick: (Topic, Subtopic) -> Unit
) {
    val progressList by viewModel.allProgress.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(subject.name, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack, modifier = Modifier.testTag("back_button")) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Go back")
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (subject.topics.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(40.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No topics created yet in this subject. Stay tuned!",
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                    }
                }
            }

            items(subject.topics) { topic ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("topic_card_${topic.id}"),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = topic.name,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        if (topic.subtopics.isEmpty()) {
                            Text(
                                text = "⚡ Concept structure pending mapping under GATE guidelines.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                            )
                        } else {
                            topic.subtopics.forEach { subtopic ->
                                val progress = progressList.find { it.subtopicId == subtopic.id }
                                val checkmark = if (progress?.isCompleted == true) "✅" else "⭕"

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { onSubtopicClick(topic, subtopic) }
                                        .padding(vertical = 10.dp)
                                        .testTag("subtopic_row_${subtopic.id}"),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.weight(1f)
                                    ) {
                                        Text(text = checkmark, fontSize = 16.sp)
                                        Spacer(modifier = Modifier.width(10.dp))
                                        Column {
                                            Text(
                                                text = subtopic.name,
                                                style = MaterialTheme.typography.bodyMedium,
                                                fontWeight = FontWeight.SemiBold
                                            )
                                            if (progress != null && progress.questionsAttempted > 0) {
                                                Text(
                                                    text = "Accuracy: ${progress.scorePercent}% | Attempted: ${progress.questionsAttempted}",
                                                    style = MaterialTheme.typography.labelSmall,
                                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                                                )
                                            }
                                        }
                                    }
                                    Icon(
                                        imageVector = Icons.Default.ChevronRight,
                                        contentDescription = "Open learning workspace",
                                        tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)
                                    )
                                }
                                HorizontalDivider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.05f))
                            }
                        }
                    }
                }
            }
        }
    }
}

// --- ACTIVE STUDYING WORKSPACE SCREEN (TABS SYSTEM) ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActiveStudyWorkspaceScreen(
    subjectId: String,
    topicId: String,
    subtopicId: String,
    viewModel: GateViewModel,
    onBack: () -> Unit
) {
    val subtopicState by viewModel.selectedSubtopic.collectAsState()
    val scope = rememberCoroutineScope()

    // Ensure state matches current intent parameters
    LaunchedEffect(subtopicId) {
        viewModel.selectSubtopic(subjectId, topicId, subtopicId)
    }

    val sub = subtopicState ?: return

    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Theory", "Formulas", "Practice PYQs", "AI Assistant")

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = sub.name,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        Text(
                            text = "Concepts Workspace",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack, modifier = Modifier.testTag("back_button")) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Go back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Material 3 Tabs
            PrimaryTabRow(
                selectedTabIndex = selectedTab,
                modifier = Modifier.fillMaxWidth()
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = {
                            Text(
                                title,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal
                            )
                        },
                        modifier = Modifier.testTag("tab_button_$index")
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                when (selectedTab) {
                    0 -> TheoryTabContent(sub.theory)
                    1 -> FormulaSheetTabContent(sub.formulaSheet)
                    2 -> PracticeQuestionsTabContent(sub, viewModel)
                    3 -> AiDoubtSupportTabContent(sub.id, viewModel)
                }
            }
        }
    }
}

// --- TAB CONTENT: THEORY WORKSPACE ---
@Composable
fun TheoryTabContent(theory: TheoryContent) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Synopsis",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = theory.synopsis,
                        style = MaterialTheme.typography.bodyLarge,
                        lineHeight = 22.sp
                    )
                }
            }
        }

        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Detailed syllabus breakdown",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    theory.detailedBullets.forEach { bullet ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Text(
                                text = "•",
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Text(
                                text = bullet,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }
        }

        item {
            // Key Insight banner card
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.TipsAndUpdates,
                        contentDescription = "Insight",
                        tint = MaterialTheme.colorScheme.onTertiaryContainer,
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            text = "Aspirant Insight Checkpoint",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onTertiaryContainer
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = theory.keyInsight,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onTertiaryContainer.copy(alpha = 0.9f)
                        )
                    }
                }
            }
        }
    }
}

// --- TAB CONTENT: FORMULA COMPENDIUM SHEET ---
@Composable
fun FormulaSheetTabContent(formulas: List<FormulaItem>) {
    if (formulas.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.Outlined.Calculate,
                    contentDescription = "No Formula",
                    tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
                    modifier = Modifier.size(56.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "No critical equations listed under this topic.",
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                    textAlign = TextAlign.Center
                )
            }
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(formulas) { formula ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = formula.name,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        // High contrast math expression bar
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.2f),
                                    RoundedCornerShape(8.dp)
                                )
                                .padding(12.dp)
                        ) {
                            Text(
                                text = formula.expression,
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = formula.description,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                        )

                        Spacer(modifier = Modifier.height(8.dp))
                        // Shortcut row
                        Row(
                            verticalAlignment = Alignment.Top,
                            modifier = Modifier
                                .background(
                                    TechGoldXP.copy(alpha = 0.1f),
                                    RoundedCornerShape(6.dp)
                                )
                                .padding(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Bolt,
                                contentDescription = "Shortcut",
                                tint = TechGoldXP,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Shortcut Trick: ${formula.applicationTrick}",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f)
                            )
                        }
                    }
                }
            }
        }
    }
}

// --- TAB CONTENT: ACTIVE INTERACTIVE SOLVER WORKSPACE ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PracticeQuestionsTabContent(sub: Subtopic, viewModel: GateViewModel) {
    val isConfiguring by viewModel.isConfiguringAttempt.collectAsState()
    val customAttemptQuestions by viewModel.customAttemptQuestions.collectAsState()
    val currentIdx by viewModel.currentQuestionIndex.collectAsState()
    val isGenerating by viewModel.isGeneratingQuestions.collectAsState()

    // Configuration states
    val topics = remember(sub.subjectId) { viewModel.getTopicsForSubject(sub.subjectId) }
    var selectedSubtopicIds by remember(sub.id) { mutableStateOf(setOf(sub.id)) }
    var expandedTopicIds by remember { mutableStateOf(setOf<String>()) }
    var selectedDifficulties by remember { mutableStateOf(setOf("Easy", "Medium", "Hard")) }
    var searchQuery by remember { mutableStateOf("") }

    // Dynamically retrieve questions for any selected subtopic
    val questionsBySubtopic = remember(topics, selectedSubtopicIds) {
        val map = mutableMapOf<String, List<GateQuestion>>()
        val allSub = topics.flatMap { it.subtopics }
        selectedSubtopicIds.forEach { id ->
            val subtopicObj = allSub.find { it.id == id }
            if (subtopicObj != null) {
                map[id] = viewModel.getOrGenerateQuestionsForSubtopic(subtopicObj)
            }
        }
        map
    }

    val allSelectedQuestions = remember(questionsBySubtopic) {
        questionsBySubtopic.values.flatten()
    }

    val filteredQuestions = remember(allSelectedQuestions, selectedDifficulties) {
        allSelectedQuestions.filter { question ->
            selectedDifficulties.contains(question.difficulty)
        }
    }

    val searchedQuestions = remember(filteredQuestions, searchQuery) {
        if (searchQuery.isBlank()) {
            filteredQuestions
        } else {
            filteredQuestions.filter {
                it.questionText.contains(searchQuery, ignoreCase = true) ||
                it.year.toString().contains(searchQuery) ||
                it.difficulty.contains(searchQuery, ignoreCase = true)
            }
        }
    }

    var selectedQuestionIds by remember(filteredQuestions) {
        mutableStateOf(filteredQuestions.map { it.id }.toSet())
    }

    if (isConfiguring) {
        val toAttemptCount = filteredQuestions.count { selectedQuestionIds.contains(it.id) }
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 88.dp)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
            // Dashboard Header & Stats
            item {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = "Config",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Practice Study Session Configurator",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.ExtraBold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "Divide and select your preferred topics and levels of difficulty. Each subtopic is set with exactly 100 high-yield questions.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        // Visual Stats Grid
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Card(
                                modifier = Modifier.weight(1f),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                            ) {
                                Column(
                                    modifier = Modifier.padding(12.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "${allSelectedQuestions.size}",
                                        style = MaterialTheme.typography.titleLarge,
                                        fontWeight = FontWeight.ExtraBold,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                    Text(
                                        text = "Matched Pools",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }

                            Card(
                                modifier = Modifier.weight(1f),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                            ) {
                                Column(
                                    modifier = Modifier.padding(12.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "${filteredQuestions.size}",
                                        style = MaterialTheme.typography.titleLarge,
                                        fontWeight = FontWeight.ExtraBold,
                                        color = TechGoldXP
                                    )
                                    Text(
                                        text = "After Filters",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }

                            Card(
                                modifier = Modifier.weight(1f),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                            ) {
                                Column(
                                    modifier = Modifier.padding(12.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    val count = filteredQuestions.count { selectedQuestionIds.contains(it.id) }
                                    Text(
                                        text = "$count",
                                        style = MaterialTheme.typography.titleLarge,
                                        fontWeight = FontWeight.ExtraBold,
                                        color = CorrectGreenText
                                    )
                                    Text(
                                        text = "To Attempt",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // FILTER: Difficulty Group
            item {
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = "Level of Difficulty",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            listOf("Easy", "Medium", "Hard").forEach { level ->
                                val isSelected = selectedDifficulties.contains(level)
                                val qCount = allSelectedQuestions.count { it.difficulty == level }
                                FilterChip(
                                    selected = isSelected,
                                    onClick = {
                                        selectedDifficulties = if (isSelected) {
                                            selectedDifficulties - level
                                        } else {
                                            selectedDifficulties + level
                                        }
                                    },
                                    label = { Text("$level ($qCount Qs)") },
                                    modifier = Modifier.weight(1f).testTag("filter_chip_$level")
                                )
                            }
                        }
                    }
                }
            }

            // TOPICS & SUBTOPICS REPERTOIRE
            item {
                Text(
                    text = "Divide Questions by Topics & Subtopics (100 Qs each)",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
            }

            items(topics) { topic ->
                val isExpanded = expandedTopicIds.contains(topic.id)
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
                ) {
                    Column {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    expandedTopicIds = if (isExpanded) {
                                        expandedTopicIds - topic.id
                                    } else {
                                        expandedTopicIds + topic.id
                                    }
                                }
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = topic.name,
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "${topic.subtopics.size} Subtopics available",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            Icon(
                                imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                                contentDescription = if (isExpanded) "Collapse" else "Expand"
                            )
                        }

                        if (isExpanded) {
                            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                topic.subtopics.forEach { s ->
                                    val isSubSelected = selectedSubtopicIds.contains(s.id)
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clip(RoundedCornerShape(8.dp))
                                            .clickable {
                                                selectedSubtopicIds = if (isSubSelected) {
                                                    selectedSubtopicIds - s.id
                                                } else {
                                                    selectedSubtopicIds + s.id
                                                }
                                            }
                                            .padding(8.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Checkbox(
                                            checked = isSubSelected,
                                            onCheckedChange = {
                                                selectedSubtopicIds = if (isSubSelected) {
                                                    selectedSubtopicIds - s.id
                                                } else {
                                                    selectedSubtopicIds + s.id
                                                }
                                            }
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Column(modifier = Modifier.weight(1f)) {
                                            Text(
                                                text = s.name,
                                                style = MaterialTheme.typography.bodyMedium,
                                                fontWeight = if (isSubSelected) FontWeight.Bold else FontWeight.Normal
                                            )
                                            Text(
                                                text = "Contains 100 questions",
                                                style = MaterialTheme.typography.labelSmall,
                                                color = MaterialTheme.colorScheme.primary
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // QUESTION CUSTOM SELECTOR HEADER
            item {
                Column(modifier = Modifier.padding(horizontal = 4.dp)) {
                    Text(
                        text = "Select Specific Questions to Attempt (${searchedQuestions.size} visible)",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        placeholder = { Text("Search questions by keyword/year...") },
                        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            onClick = {
                                selectedQuestionIds = selectedQuestionIds + searchedQuestions.map { it.id }.toSet()
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondaryContainer, contentColor = MaterialTheme.colorScheme.onSecondaryContainer),
                            modifier = Modifier.weight(1f).height(38.dp),
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Text("Select All", style = MaterialTheme.typography.labelMedium)
                        }
                        OutlinedButton(
                            onClick = {
                                selectedQuestionIds = selectedQuestionIds - searchedQuestions.map { it.id }.toSet()
                            },
                            modifier = Modifier.weight(1f).height(38.dp),
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Text("Deselect All", style = MaterialTheme.typography.labelMedium)
                        }
                    }
                }
            }

            // INDIVIDUAL SEARCHED QUESTIONS LIST
            if (searchedQuestions.isEmpty()) {
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.2f))
                    ) {
                        Text(
                            text = "No questions match your selection criteria or search terms.",
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.fillMaxWidth().padding(24.dp)
                        )
                    }
                }
            } else {
                items(searchedQuestions) { question ->
                    val isChecked = selectedQuestionIds.contains(question.id)
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                selectedQuestionIds = if (isChecked) {
                                    selectedQuestionIds - question.id
                                } else {
                                    selectedQuestionIds + question.id
                                }
                            },
                        colors = CardDefaults.cardColors(
                            containerColor = if (isChecked) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surface
                        ),
                        border = BorderStroke(1.dp, if (isChecked) MaterialTheme.colorScheme.primary.copy(alpha = 0.5f) else MaterialTheme.colorScheme.outlineVariant)
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Checkbox(
                                checked = isChecked,
                                onCheckedChange = {
                                    selectedQuestionIds = if (isChecked) {
                                        selectedQuestionIds - question.id
                                    } else {
                                        selectedQuestionIds + question.id
                                    }
                                }
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "GATE ${question.year} • ${question.questionType.name}",
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                    AssistChip(
                                        onClick = {},
                                        label = { Text(question.difficulty) },
                                        colors = AssistChipDefaults.assistChipColors(
                                            containerColor = if (question.difficulty == "Easy") CorrectGreenBg else if (question.difficulty == "Medium") TechGoldXP.copy(alpha = 0.15f) else WrongRedBg,
                                            labelColor = if (question.difficulty == "Easy") CorrectGreenText else if (question.difficulty == "Medium") TechGoldXP else WrongRedText
                                        ),
                                        modifier = Modifier.height(20.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = question.questionText,
                                    style = MaterialTheme.typography.bodyMedium,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }
                }
            }
            
            // Close LazyColumn
            }

            // Sticky Bottom Launch Button Panel
            Surface(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth(),
                tonalElevation = 8.dp,
                shadowElevation = 16.dp,
                color = MaterialTheme.colorScheme.surface
            ) {
                Box(
                    modifier = Modifier
                        .padding(16.dp)
                        .navigationBarsPadding()
                ) {
                    Button(
                        onClick = {
                            val questionsToAttempt = filteredQuestions.filter { selectedQuestionIds.contains(it.id) }
                            viewModel.startCustomAttempt(questionsToAttempt)
                        },
                        enabled = toAttemptCount > 0,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .testTag("launch_practice_session_btn"),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(imageVector = Icons.Default.Launch, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Launch Study Attempt ($toAttemptCount Qs) 🚀",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
    } else {
        // --- ACTIVE INTERACTIVE WORKSPACE VIEW ---
        if (customAttemptQuestions.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Default.Bolt,
                        contentDescription = "AI Generate",
                        tint = TechGoldXP,
                        modifier = Modifier.size(64.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "No questions selected for this attempt.",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = { viewModel.exitAttemptConfiguration() },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                    ) {
                        Text("Configure Setup")
                    }
                }
            }
            return
        }

        val activeQuestion = customAttemptQuestions[currentIdx]

        // Bookmark state
        val bookmarkedQuestions by viewModel.bookmarkedQuestions.collectAsState()
        val isBookmarked = bookmarkedQuestions.any { it.questionId == activeQuestion.id }

        // Interactivity inputs
        val selectedMcq by viewModel.selectedMcqOption.collectAsState()
        val selectedMsq by viewModel.selectedMsqOptions.collectAsState()
        val natInput by viewModel.natAnswerInput.collectAsState()
        val isSubmitted by viewModel.isAnswerSubmitted.collectAsState()
        val isCorrect by viewModel.isAnswerCorrect.collectAsState()
        val aiFeedback by viewModel.aiResultFeedback.collectAsState()
        val isLoadingAiFeedback by viewModel.isLoadingAiFeedback.collectAsState()

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Question count HUD & Bookmark & Exit option
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Question ${currentIdx + 1} of ${customAttemptQuestions.size}",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Active customized session",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(
                            onClick = { viewModel.exitAttemptConfiguration() },
                            modifier = Modifier.testTag("reconfigure_floating_btn")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = "Configure Setup",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                        IconButton(
                            onClick = { viewModel.toggleBookmark(activeQuestion, !isBookmarked) },
                            modifier = Modifier.testTag("bookmark_toggle_btn")
                        ) {
                            Icon(
                                imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                                contentDescription = "Bookmark question",
                                tint = if (isBookmarked) TechGoldXP else MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }

            // Question box card
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f))
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "GATE ${activeQuestion.year}",
                                style = MaterialTheme.typography.titleSmall,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold
                            )
                            AssistChip(
                                onClick = {},
                                label = { Text("${activeQuestion.questionType.name} • ${activeQuestion.difficulty}") }
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = activeQuestion.questionText,
                            style = MaterialTheme.typography.bodyLarge,
                            lineHeight = 22.sp
                        )
                    }
                }
            }

            // Render options depending on MCQ, MSQ or NAT
            item {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    if (activeQuestion.questionType == QuestionType.MCQ) {
                        activeQuestion.options?.forEachIndexed { index, option ->
                            val isSelected = selectedMcq == index
                            val borderCol = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                            val bgCol = if (isSelected) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f) else MaterialTheme.colorScheme.surface

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(bgCol, RoundedCornerShape(12.dp))
                                    .border(1.dp, borderCol, RoundedCornerShape(12.dp))
                                    .clickable(enabled = !isSubmitted) { viewModel.selectMcqOption(index) }
                                    .padding(14.dp)
                                    .testTag("mcq_option_$index"),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = isSelected,
                                    onClick = { if (!isSubmitted) viewModel.selectMcqOption(index) },
                                    enabled = !isSubmitted
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(text = option, style = MaterialTheme.typography.bodyMedium)
                            }
                        }
                    } else if (activeQuestion.questionType == QuestionType.MSQ) {
                        activeQuestion.options?.forEachIndexed { index, option ->
                            val isSelected = selectedMsq.contains(index)
                            val borderCol = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                            val bgCol = if (isSelected) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f) else MaterialTheme.colorScheme.surface

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(bgCol, RoundedCornerShape(12.dp))
                                    .border(1.dp, borderCol, RoundedCornerShape(12.dp))
                                    .clickable(enabled = !isSubmitted) { viewModel.toggleMsqOption(index) }
                                    .padding(14.dp)
                                    .testTag("msq_option_$index"),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    checked = isSelected,
                                    onCheckedChange = { if (!isSubmitted) viewModel.toggleMsqOption(index) },
                                    enabled = !isSubmitted
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(text = option, style = MaterialTheme.typography.bodyMedium)
                            }
                        }
                    } else if (activeQuestion.questionType == QuestionType.NAT) {
                        OutlinedTextField(
                            value = natInput,
                            onValueChange = { if (!isSubmitted) viewModel.setNatAnswerInput(it) },
                            label = { Text("Enter Numeric Answer") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("nat_input_field"),
                            enabled = !isSubmitted,
                            singleLine = true
                        )
                    }
                }
            }

            // Submit action or Results presentation
            item {
                if (!isSubmitted) {
                    val isButtonEnabled = when (activeQuestion.questionType) {
                        QuestionType.MCQ -> selectedMcq != null
                        QuestionType.MSQ -> selectedMsq.isNotEmpty()
                        QuestionType.NAT -> natInput.isNotBlank()
                    }

                    Button(
                        onClick = { viewModel.submitAnswer(activeQuestion) },
                        enabled = isButtonEnabled,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp)
                            .testTag("submit_ans_button"),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Submit Answer", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    }
                } else {
                    val bannerBg = if (isCorrect == true) CorrectGreenBg else WrongRedBg
                    val bannerBorder = if (isCorrect == true) CorrectGreenBorder else WrongRedBorder
                    val bannerText = if (isCorrect == true) CorrectGreenText else WrongRedText
                    val title = if (isCorrect == true) "Correct Option! +20 XP 🔥" else "Incorrect Attempt"

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(bannerBg, RoundedCornerShape(16.dp))
                            .border(1.dp, bannerBorder, RoundedCornerShape(16.dp))
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = title,
                                color = bannerText,
                                fontWeight = FontWeight.ExtraBold,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Icon(
                                imageVector = if (isCorrect == true) Icons.Default.CheckCircle else Icons.Default.Cancel,
                                contentDescription = "Result Icon",
                                tint = bannerText
                            )
                        }

                        Text(
                            text = "Official Answer key range: " + when (activeQuestion.questionType) {
                                QuestionType.MCQ -> activeQuestion.correctOptions?.firstOrNull()?.let { activeQuestion.options?.getOrNull(it) } ?: ""
                                QuestionType.MSQ -> activeQuestion.correctOptions?.joinToString { activeQuestion.options?.getOrNull(it) ?: "" } ?: ""
                                QuestionType.NAT -> activeQuestion.correctNumericalRange?.toString() ?: ""
                            },
                            fontWeight = FontWeight.Bold,
                            color = bannerText
                        )

                        HorizontalDivider(color = bannerBorder.copy(alpha = 0.5f))

                        Text(
                            text = "Step-By-Step Solution:",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(text = activeQuestion.explanation, style = MaterialTheme.typography.bodyMedium)

                        Text(
                            text = "⚡ Formulations Applied: ${activeQuestion.formulasUsed}",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "💡 Study Shortcut: ${activeQuestion.shortcutTricks}",
                            style = MaterialTheme.typography.bodySmall,
                            color = TechGoldXP,
                            fontWeight = FontWeight.Bold
                        )

                        // AI doubt analyzer loader
                        if (isLoadingAiFeedback) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth().padding(top = 10.dp)
                            ) {
                                CircularProgressIndicator(modifier = Modifier.size(20.dp), strokeWidth = 2.dp)
                                Spacer(modifier = Modifier.width(10.dp))
                                Text("Analyzing your concept mistake in real-time...", fontSize = 12.sp)
                            }
                        } else if (aiFeedback.isNotEmpty()) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(12.dp))
                                    .padding(12.dp)
                            ) {
                                Column {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            imageVector = Icons.Default.SmartToy,
                                            contentDescription = "AI",
                                            tint = MaterialTheme.colorScheme.primary,
                                            modifier = Modifier.size(18.dp)
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(
                                            text = "AI Mind Explainer Insights:",
                                            fontWeight = FontWeight.Bold,
                                            style = MaterialTheme.typography.labelMedium
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(6.dp))
                                    Text(
                                        text = aiFeedback,
                                        style = MaterialTheme.typography.bodySmall,
                                        lineHeight = 16.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Navigation controls
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedButton(
                        onClick = { viewModel.prevQuestion() },
                        enabled = currentIdx > 0,
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Previous")
                    }

                    Button(
                        onClick = { viewModel.nextQuestion(customAttemptQuestions) },
                        enabled = currentIdx < customAttemptQuestions.size - 1,
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Next Question")
                    }
                }
            }
        }
    }
}

// --- TAB CONTENT: ACTIVE REAL-TIME CHATBOT INSIDE SYLLABUS CONCEPTS ---
@Composable
fun AiDoubtSupportTabContent(subtopicId: String, viewModel: GateViewModel) {
    val messages by viewModel.chatMessages.collectAsState()
    val isChatLoading by viewModel.isChatLoading.collectAsState()

    var textInput by remember { mutableStateOf("") }

    val quickDoubts = listOf(
        "Solve a numerical problem step-by-step",
        "Explain standard formulas of this topic",
        "Give me a high-reward shortcut trick"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Chat list area
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            if (messages.isEmpty()) {
                // Friendly tips
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.SmartToy,
                        contentDescription = "AI Assistant robot",
                        tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.4f),
                        modifier = Modifier.size(64.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "I am your GATE AI Doubt Solver!",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Ask me to solve formulas, derive equations, or verify calculations step-by-step.",
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                        fontSize = 13.sp,
                        modifier = Modifier.padding(horizontal = 24.dp)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // Dynamic prompts buttons
                    Text(
                        text = "Tapping quick starters:",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    quickDoubts.forEach { doubt ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .clickable { viewModel.sendChatMessage(subtopicId, doubt) },
                            shape = RoundedCornerShape(8.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                        ) {
                            Text(
                                text = doubt,
                                modifier = Modifier.padding(10.dp),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(messages) { msg ->
                        val isUser = msg.role == "user"
                        val alignment = if (isUser) Alignment.End else Alignment.Start
                        val bubbleColor = if (isUser) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant
                        val textCol = if (isUser) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant

                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = alignment
                        ) {
                            Box(
                                modifier = Modifier
                                    .background(
                                        bubbleColor,
                                        RoundedCornerShape(
                                            topStart = 16.dp,
                                            topEnd = 16.dp,
                                            bottomStart = if (isUser) 16.dp else 4.dp,
                                            bottomEnd = if (isUser) 4.dp else 16.dp
                                        )
                                    )
                                    .padding(12.dp)
                            ) {
                                Text(
                                    text = msg.text,
                                    color = textCol,
                                    fontSize = 14.sp,
                                    lineHeight = 18.sp
                                )
                            }
                        }
                    }

                    if (isChatLoading) {
                        item {
                            Row(
                                modifier = Modifier
                                    .background(
                                        MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                                        RoundedCornerShape(12.dp)
                                    )
                                    .padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                CircularProgressIndicator(modifier = Modifier.size(16.dp), strokeWidth = 2.dp)
                                Spacer(modifier = Modifier.width(10.dp))
                                Text("Aspirant Assistant is solving...", fontSize = 12.sp)
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Input row
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = textInput,
                onValueChange = { textInput = it },
                label = { Text("Ask doubt instantly...") },
                modifier = Modifier
                    .weight(1f)
                    .testTag("ai_chat_input"),
                maxLines = 3,
                singleLine = false
            )
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(
                onClick = {
                    if (textInput.isNotBlank()) {
                        viewModel.sendChatMessage(subtopicId, textInput)
                        textInput = ""
                    }
                },
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary, CircleShape)
                    .testTag("ai_chat_send_btn")
            ) {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = "Send prompt",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

// --- BOOKMARKS & SAVED SCREEN ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarksListScreen(
    viewModel: GateViewModel,
    onBack: () -> Unit
) {
    val list by viewModel.bookmarkedQuestions.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Saved Bookmarks", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack, modifier = Modifier.testTag("back_button")) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Go back")
                    }
                }
            )
        }
    ) { innerPadding ->
        if (list.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.BookmarkBorder,
                        contentDescription = "Empty Bookmarks",
                        tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
                        modifier = Modifier.size(72.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Starred practice questions will appear here for quick revision.",
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(list) { item ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Bookmarked Concept",
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.Bold
                                )
                                IconButton(
                                    onClick = {
                                        // Simple fake question object for convenience deletion mapping
                                        val mockQuestion = GateQuestion(
                                            id = item.questionId,
                                            subjectId = item.subjectId,
                                            topicId = item.topicId,
                                            subtopicId = item.subtopicId,
                                            year = 2024,
                                            questionText = item.questionText,
                                            questionType = QuestionType.valueOf(item.questionType),
                                            explanation = item.explanation,
                                            formulasUsed = "",
                                            shortcutTricks = "",
                                            relatedConcepts = "",
                                            difficulty = "Medium"
                                        )
                                        viewModel.toggleBookmark(mockQuestion, false)
                                    }
                                ) {
                                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Remove")
                                }
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = item.questionText, style = MaterialTheme.typography.bodyLarge)
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = "Correct Method Strategy:",
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.labelMedium
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = item.explanation, style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }
        }
    }
}

// --- MISTAKES NOTEBOOK SCREEN ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MistakesNotebookScreen(
    viewModel: GateViewModel,
    onBack: () -> Unit
) {
    val list by viewModel.mistakeNotebookQuestions.collectAsState()
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Aspirant Mistakes Compendium", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack, modifier = Modifier.testTag("back_button")) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Go back")
                    }
                }
            )
        }
    ) { innerPadding ->
        if (list.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.CheckCircleOutline,
                        contentDescription = "Zero mistakes",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(72.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Splendid job! Zero incorrect attempts stored. Tackle practice questions to identify gaps.",
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(list) { item ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Stored Mistake Detail",
                                    color = Color(0xFFCF1322),
                                    fontWeight = FontWeight.Bold
                                )
                                IconButton(
                                    onClick = { scope.launch { viewModel.repository.removeMistake(item.questionId) } }
                                ) {
                                    Icon(imageVector = Icons.Default.Check, contentDescription = "Mastered")
                                }
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = item.questionText, style = MaterialTheme.typography.bodyLarge)
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = "How to patch concept:",
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = item.explanation, style = MaterialTheme.typography.bodySmall)

                            Spacer(modifier = Modifier.height(10.dp))
                            // User trial notes
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(TechGoldXP.copy(alpha = 0.1f), RoundedCornerShape(8.dp))
                                    .padding(8.dp)
                            ) {
                                Text(
                                    text = "Attempt Log: ${item.userNotes}",
                                    style = MaterialTheme.typography.bodySmall,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

// --- CBT MOCK TEST SYSTEM PLATFORM SCREEN (REAL EXAM GRAPHIC) ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CbtMockTestPlatformScreen(
    viewModel: GateViewModel,
    onBack: () -> Unit
) {
    val questions by viewModel.cbtQuestions.collectAsState()
    val userAnswers by viewModel.cbtUserAnswers.collectAsState()
    val finalScore by viewModel.cbtFinalScore.collectAsState()
    val timerText by viewModel.cbtTimerSeconds.collectAsState()
    val isRunning by viewModel.isCbtRunning.collectAsState()
    val selectedCbtSubjectId by viewModel.cbtSelectedSubjectId.collectAsState()
    val cbtSubjectScores by viewModel.cbtSubjectScores.collectAsState()

    var activeCbtIdx by remember { mutableIntStateOf(0) }
    var selectedSubjectId by remember { mutableStateOf<String?>(null) }
    var reviewFilter by remember { mutableStateOf("All") }

    if (!isRunning && finalScore == null) {
        // Welcoming starting landing sheet
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("GATE CBT Exam Terminal", fontWeight = FontWeight.Bold) },
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                        }
                    }
                )
            }
        ) { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    Icon(
                        imageVector = Icons.Default.Task,
                        contentDescription = "Ready test",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(64.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Official Computer Based Test (CBT)",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.ExtraBold,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Take a high-fidelity mock test of 50 Questions for 50 Marks. Real GATE pattern negative marking (-0.33 for wrong MCQs, 0 for MSQs/NATs). Standard Duration: 90 Minutes.",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    
                    Text(
                        text = "Choose Your CBT Stream Mode:",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    // All Subjects Option card
                    val isAllSelected = selectedSubjectId == null
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .clickable { selectedSubjectId = null },
                        border = androidx.compose.foundation.BorderStroke(
                            2.dp, 
                            if (isAllSelected) MaterialTheme.colorScheme.primary else Color.Transparent
                        ),
                        colors = CardDefaults.cardColors(
                            containerColor = if (isAllSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant
                        )
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(imageVector = Icons.Default.MenuBook, contentDescription = "All Subjects", tint = MaterialTheme.colorScheme.primary)
                            Spacer(modifier = Modifier.width(16.dp))
                            Column {
                                Text(
                                    text = "Complete Syllabus Mock Test", 
                                    fontWeight = FontWeight.Bold, 
                                    color = if (isAllSelected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "Mix of questions from all general, math, and core engineering subjects",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f)
                                )
                            }
                        }
                    }
                }

                // Dynamic list of other subjects
                items(viewModel.repository.subjects) { subject ->
                    val isSelected = selectedSubjectId == subject.id
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .clickable { selectedSubjectId = subject.id },
                        border = androidx.compose.foundation.BorderStroke(
                            2.dp, 
                            if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent
                        ),
                        colors = CardDefaults.cardColors(
                            containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant
                        )
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(imageVector = Icons.Default.Book, contentDescription = "Subject Mock Test", tint = MaterialTheme.colorScheme.primary)
                            Spacer(modifier = Modifier.width(16.dp))
                            Column {
                                Text(
                                    text = subject.name, 
                                    fontWeight = FontWeight.Bold, 
                                    color = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "Dedicated subject-wise test with 50 targeted questions",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f)
                                )
                            }
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(
                        onClick = { viewModel.launchMockTest(selectedSubjectId) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .testTag("launch_cbt_test_btn"),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = if (selectedSubjectId == null) "START COMPLETE SYLLABUS CBT (50 Q)" else "START SUBJECT-WISE CBT (50 Q)",
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
        return
    }

    if (finalScore != null) {
        // CBT Scoreboard detailed results & predicted rank
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("CBT Scorecard Analysis", fontWeight = FontWeight.Bold) },
                    navigationIcon = {
                        IconButton(onClick = { viewModel.launchMockTest(selectedCbtSubjectId) }) {
                            Icon(imageVector = Icons.Default.Refresh, contentDescription = "Retry")
                        }
                    }
                )
            }
        ) { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Predictive ranking formula matching 50 marks total
                val rankText = when {
                    finalScore!! >= 40.0 -> "Predicted Rank: AIR 1 - 50 (Exemplary Mastery) 🏆"
                    finalScore!! >= 30.0 -> "Predicted Rank: AIR 51 - 250 (Excellent Prep) 🚀"
                    finalScore!! >= 20.0 -> "Predicted Rank: AIR 251 - 1000 (Very Good) ⚡"
                    finalScore!! >= 15.0 -> "Predicted Rank: AIR 1001 - 3000 (Qualified) 🎓"
                    else -> "Qualified (AIR 3000+). Needs more rigorous study. 📚"
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "EXAM WORKSPACE COMPLETED",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Card(
                        shape = RoundedCornerShape(24.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                    ) {
                        Column(
                            modifier = Modifier.padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "Final GATE Score", fontSize = 16.sp, color = MaterialTheme.colorScheme.onPrimaryContainer)
                            val displayScore = if (finalScore!! < 0.0) 0.0 else finalScore!!
                            Text(
                                text = String.format("%.2f", displayScore),
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 64.sp,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(text = "from 50.0 marks possible", fontSize = 12.sp, color = MaterialTheme.colorScheme.onPrimaryContainer)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(TechGoldXP.copy(alpha = 0.12f), RoundedCornerShape(12.dp))
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = rankText,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleMedium,
                            color = TechGoldXP,
                            textAlign = TextAlign.Center
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                    
                    Text(
                        text = "Subject-Wise Detailed Breakdown",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)
                    )
                }

                // Render subject scores detail cards
                if (cbtSubjectScores.isEmpty()) {
                    item {
                        Text(
                            text = "No subject breakdown details compiled for this test.",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(vertical = 12.dp)
                        )
                    }
                } else {
                    items(cbtSubjectScores) { detail ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = detail.subjectName,
                                        fontWeight = FontWeight.ExtraBold,
                                        style = MaterialTheme.typography.bodyLarge,
                                        modifier = Modifier.weight(1f)
                                    )
                                    Text(
                                        text = String.format("%.2f / %d", detail.subjectScore, detail.totalQuestions),
                                        fontWeight = FontWeight.ExtraBold,
                                        color = MaterialTheme.colorScheme.primary,
                                        style = MaterialTheme.typography.bodyLarge
                                    )
                                }
                                Spacer(modifier = Modifier.height(6.dp))
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "Correct: ${detail.correctQuestions} | Wrong MCQs: ${detail.wrongMcqs}",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f)
                                    )
                                    val acc = if (detail.totalQuestions > 0) (detail.correctQuestions.toDouble() / detail.totalQuestions * 100).toInt() else 0
                                    Text(
                                        text = "Accuracy: $acc%",
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }
                        }
                    }
                }

                // Question-by-Question CBT Review Header & Filters
                item {
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = "Question-by-Question CBT Review",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = "Review every problem with detailed step-by-step AI generated explanations, formulas, and shortcut tricks.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f),
                        modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)
                    )

                    // Tab-like Filter Buttons
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        listOf("All", "Correct", "Incorrect", "Unattempted").forEach { filterType ->
                            val isSelected = reviewFilter == filterType
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .background(
                                        color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                                        shape = RoundedCornerShape(12.dp)
                                    )
                                    .clickable { reviewFilter = filterType }
                                    .padding(vertical = 8.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = filterType,
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }

                // Filtering the review questions pool
                val filteredReviewedQuestions = questions.mapIndexed { idx, q ->
                    val userAns = userAnswers[q.id]
                    val isAttempted = userAns != null && userAns.trim().isNotEmpty()
                    val isCorrect = isAttempted && viewModel.evaluateAnswerCorrectness(q, userAns!!)
                    Triple(q, idx, Pair(userAns, isAttempted to isCorrect))
                }.filter { (_, _, info) ->
                    val (_, status) = info
                    val (isAttempted, isCorrect) = status
                    when (reviewFilter) {
                        "Correct" -> isAttempted && isCorrect
                        "Incorrect" -> isAttempted && !isCorrect
                        "Unattempted" -> !isAttempted
                        else -> true
                    }
                }

                if (filteredReviewedQuestions.isEmpty()) {
                    item {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 12.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                        ) {
                            Text(
                                text = "No questions match the filter: $reviewFilter",
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(24.dp),
                                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                            )
                        }
                    }
                } else {
                    items(filteredReviewedQuestions) { (q, idx, info) ->
                        val (ans, status) = info
                        val (isAttempted, isCorrect) = status
                        ReviewedQuestionCard(
                            question = q,
                            indexInTest = idx,
                            userAns = ans,
                            isAttempted = isAttempted,
                            isCorrect = isCorrect
                        )
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(32.dp))
                    Button(
                        onClick = onBack,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp)
                    ) {
                        Text("Return to Hub Dashboard")
                    }
                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        }
        return
    }

    // Active CBT test workspace HUD
    val curQ = questions.getOrNull(activeCbtIdx) ?: return
    val chosenOptionRep = userAnswers[curQ.id] ?: ""

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("CBT Active Simulator", fontWeight = FontWeight.Bold) },
                actions = {
                    // Modern count timer display
                    val mins = timerText / 60
                    val secs = timerText % 60
                    AssistChip(
                        onClick = {},
                        label = { Text(text = String.format("Time: %2d:%02d", mins, secs)) }
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            // CBT Question Navigator list row
            androidx.compose.foundation.lazy.LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsIndexed(questions) { idx, q ->
                    val isCurrent = idx == activeCbtIdx
                    val isAnswered = userAnswers.containsKey(q.id)
                    val bg = when {
                        isCurrent -> MaterialTheme.colorScheme.primary
                        isAnswered -> MaterialTheme.colorScheme.secondary
                        else -> MaterialTheme.colorScheme.surfaceVariant
                    }
                    val textCol = if (isCurrent || isAnswered) Color.White else MaterialTheme.colorScheme.onSurfaceVariant

                    Box(
                        modifier = Modifier
                            .size(width = 44.dp, height = 36.dp)
                            .background(bg, RoundedCornerShape(8.dp))
                            .clickable { activeCbtIdx = idx },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "${idx + 1}", fontWeight = FontWeight.Bold, color = textCol)
                    }
                }
            }

            // Question Box Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                LazyColumn(modifier = Modifier.padding(16.dp)) {
                    item {
                        Text(
                            text = "Question Type: ${curQ.questionType.name}",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = curQ.questionText, style = MaterialTheme.typography.bodyLarge)
                        Spacer(modifier = Modifier.height(20.dp))
                    }

                    if (curQ.questionType == QuestionType.MCQ || curQ.questionType == QuestionType.MSQ) {
                        itemsIndexed(curQ.options ?: emptyList()) { optIdx, opt ->
                            val isSelected = if (curQ.questionType == QuestionType.MCQ) {
                                chosenOptionRep == optIdx.toString()
                            } else {
                                chosenOptionRep.split(",").contains(optIdx.toString())
                            }
                            val bg = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant
                            val borderCol = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp)
                                    .background(bg, RoundedCornerShape(8.dp))
                                    .border(1.dp, borderCol, RoundedCornerShape(8.dp))
                                    .clickable {
                                        if (curQ.questionType == QuestionType.MCQ) {
                                            viewModel.setCbtAnswer(curQ.id, optIdx.toString())
                                        } else {
                                            val currentSet = chosenOptionRep.split(",").filter { it.isNotEmpty() }.toSet()
                                            val updated = if (currentSet.contains(optIdx.toString())) {
                                                currentSet - optIdx.toString()
                                            } else {
                                                currentSet + optIdx.toString()
                                            }
                                            viewModel.setCbtAnswer(curQ.id, updated.joinToString(","))
                                        }
                                    }
                                    .padding(12.dp)
                            ) {
                                Text(text = opt)
                            }
                        }
                    } else if (curQ.questionType == QuestionType.NAT) {
                        item {
                            OutlinedTextField(
                                value = chosenOptionRep,
                                onValueChange = { viewModel.setCbtAnswer(curQ.id, it) },
                                label = { Text("Student Numerical Answer Representation") },
                                modifier = Modifier.fillMaxWidth(),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Navigation and submitting exam row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(
                    onClick = { if (activeCbtIdx > 0) activeCbtIdx-- },
                    enabled = activeCbtIdx > 0
                ) {
                    Text("Prev")
                }

                Button(
                    onClick = { viewModel.submitCbtTest() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFCF1322))
                ) {
                    Text("SUBMIT COMPLETED EXAM", fontWeight = FontWeight.Bold)
                }

                OutlinedButton(
                    onClick = { if (activeCbtIdx < questions.size - 1) activeCbtIdx++ },
                    enabled = activeCbtIdx < questions.size - 1
                ) {
                    Text("Next")
                }
            }
        }
    }
}

@Composable
fun ReviewedQuestionCard(
    question: GateQuestion,
    indexInTest: Int,
    userAns: String?,
    isAttempted: Boolean,
    isCorrect: Boolean
) {
    var expanded by remember { mutableStateOf(false) }

    val statusColor = when {
        !isAttempted -> Color.Gray
        isCorrect -> Color(0xFF2E7D32) // Forest Green
        else -> Color(0xFFC62828) // Strong Red
    }

    val containerBgColor = when {
        !isAttempted -> MaterialTheme.colorScheme.surface
        isCorrect -> Color(0xFFE8F5E9) // Very light success green
        else -> Color(0xFFFFEBEE) // Very light error red
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { expanded = !expanded },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (expanded) containerBgColor else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
        ),
        border = androidx.compose.foundation.BorderStroke(
            width = 1.dp,
            color = if (expanded) statusColor else Color.Transparent
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header: Title, Pill & Status Icon
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Question ${indexInTest + 1}",
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        // Difficulty Pill
                        Box(
                            modifier = Modifier
                                .background(
                                    color = when (question.difficulty) {
                                        "Easy" -> Color(0xFFE8F5E9)
                                        "Medium" -> Color(0xFFFFF3E0)
                                        else -> Color(0xFFFFEBEE)
                                    },
                                    shape = RoundedCornerShape(100.dp)
                                )
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = question.difficulty ?: "Medium",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = when (question.difficulty) {
                                    "Easy" -> Color(0xFF2E7D32)
                                    "Medium" -> Color(0xFFE65100)
                                    else -> Color(0xFFC62828)
                                }
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "GATE ${question.year ?: 2024} • ${question.questionType}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Status Pill
                    Box(
                        modifier = Modifier
                            .background(statusColor.copy(alpha = 0.15f), RoundedCornerShape(12.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = when {
                                !isAttempted -> "Unattempted"
                                isCorrect -> "Correct"
                                else -> "Incorrect"
                            },
                            color = statusColor,
                            fontWeight = FontWeight.ExtraBold,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }

                    Icon(
                        imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                        contentDescription = "Expand/Collapse",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // Expanded step-by-step content
            if (expanded) {
                Spacer(modifier = Modifier.height(12.dp))
                HorizontalDivider(color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.15f))
                Spacer(modifier = Modifier.height(12.dp))

                // Question Statement
                Text(
                    text = question.questionText,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                // Options Review (MCQ/MSQ Only)
                if ((question.questionType == QuestionType.MCQ || question.questionType == QuestionType.MSQ) && !question.options.isNullOrEmpty()) {
                    Text(
                        text = "Options Review:",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(bottom = 6.dp)
                    )
                    question.options!!.forEachIndexed { optIdx, optionText ->
                        val isThisOptionCorrect = question.correctOptions?.contains(optIdx) == true
                        val isThisOptionSelected = when (question.questionType) {
                            QuestionType.MCQ -> userAns == optIdx.toString()
                            QuestionType.MSQ -> userAns?.split(",")?.mapNotNull { it.trim().toIntOrNull() }?.contains(optIdx) == true
                            else -> false
                        }

                        // Background determine
                        val optBg = when {
                            isThisOptionCorrect -> Color(0xFFE8F5E9) // light green
                            isThisOptionSelected && !isThisOptionCorrect -> Color(0xFFFFEBEE) // light red
                            else -> MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
                        }
                        val optBorderColor = when {
                            isThisOptionCorrect -> Color(0xFF2E7D32)
                            isThisOptionSelected && !isThisOptionCorrect -> Color(0xFFC62828)
                            else -> Color.Transparent
                        }

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            shape = RoundedCornerShape(8.dp),
                            colors = CardDefaults.cardColors(containerColor = optBg),
                            border = androidx.compose.foundation.BorderStroke(1.dp, optBorderColor)
                        ) {
                            Row(
                                modifier = Modifier.padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(24.dp)
                                        .background(
                                            color = when {
                                                isThisOptionCorrect -> Color(0xFF2E7D32)
                                                isThisOptionSelected && !isThisOptionCorrect -> Color(0xFFC62828)
                                                else -> MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                                            },
                                            shape = CircleShape
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = ('A' + optIdx).toString(),
                                        color = if (isThisOptionCorrect || (isThisOptionSelected && !isThisOptionCorrect)) Color.White else MaterialTheme.colorScheme.primary,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 12.sp
                                    )
                                }
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(
                                    text = optionText,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.weight(1f)
                                )
                                // Right-side badge
                                if (isThisOptionCorrect) {
                                    Icon(
                                        imageVector = Icons.Default.CheckCircle,
                                        contentDescription = "Correct Choice",
                                        tint = Color(0xFF2E7D32),
                                        modifier = Modifier.size(20.dp)
                                    )
                                } else if (isThisOptionSelected) {
                                    Icon(
                                        imageVector = Icons.Default.Cancel,
                                        contentDescription = "Incorrect choice selected",
                                        tint = Color(0xFFC62828),
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                }

                // Student Response vs Expected Section
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
                            RoundedCornerShape(8.dp)
                        )
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Your Submitted Answer",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f)
                        )
                        Text(
                            text = if (!isAttempted) {
                                "Not Attempted"
                            } else {
                                when (question.questionType) {
                                    QuestionType.MCQ -> {
                                        val idx = userAns?.toIntOrNull()
                                        if (idx != null) "Option ${('A' + idx)}" else userAns ?: "N/A"
                                    }
                                    QuestionType.MSQ -> {
                                        val indices = userAns?.split(",")?.mapNotNull { it.trim().toIntOrNull() } ?: emptyList()
                                        if (indices.isNotEmpty()) "Options " + indices.map { ('A' + it) }.joinToString(", ") else "N/A"
                                    }
                                    QuestionType.NAT -> userAns ?: "N/A"
                                }
                            },
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.bodyMedium,
                            color = if (isAttempted && isCorrect) Color(0xFF2E7D32) else if (isAttempted) Color(0xFFC62828) else Color.Gray
                        )
                    }

                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            text = "Correct Key",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f)
                        )
                        Text(
                            text = when (question.questionType) {
                                QuestionType.MCQ -> {
                                    val idx = question.correctOptions?.firstOrNull() ?: -1
                                    "Option ${('A' + idx)}"
                                }
                                QuestionType.MSQ -> {
                                    val indices = question.correctOptions ?: emptyList()
                                    "Options " + indices.map { ('A' + it) }.joinToString(", ")
                                }
                                QuestionType.NAT -> {
                                    val range = question.correctNumericalRange
                                    if (range != null) "Range [${range.start} to ${range.endInclusive}]" else "N/A"
                                }
                            },
                            fontWeight = FontWeight.ExtraBold,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color(0xFF2E7D32)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // STEP-BY-STEP AI EXPLANATION CONTAINER
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.35f)
                    ),
                    border = androidx.compose.foundation.BorderStroke(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
                    )
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        // AI Header Badge
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.15f), RoundedCornerShape(100.dp))
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = "AI Powered",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "STUDY CO-PILOT • STEP-BY-STEP EXPLANATION",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        // STEP 1: Understand Objective
                        Text(
                            text = "Step 1: Understand Objective & Scope",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Text(
                            text = "To analyze this problem under ${question.relatedConcepts ?: "engineering and scientific principles"}, we identify the key concepts and attributes associated with \"${question.difficulty ?: "Medium"}\" rated GATE objectives.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.85f),
                            modifier = Modifier.padding(top = 2.dp, bottom = 12.dp)
                        )

                        // STEP 2: Formula Setup
                        if (!question.formulasUsed.isNullOrBlank()) {
                            Text(
                                text = "Step 2: Physics & Mathematical Formulas",
                                style = MaterialTheme.typography.labelLarge,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 4.dp, bottom = 12.dp)
                                    .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.6f), RoundedCornerShape(8.dp))
                                    .border(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.1f), RoundedCornerShape(8.dp))
                                    .padding(8.dp)
                            ) {
                                Text(
                                    text = question.formulasUsed!!,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        }

                        // STEP 3: Systematic Derivation
                        Text(
                            text = "Step 3: Systematic Multi-Stage Math Derivation",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Text(
                            text = question.explanation ?: "Substitute values into state formulas and solve step-by-step.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.85f),
                            modifier = Modifier.padding(top = 2.dp, bottom = 12.dp)
                        )

                        // STEP 4: Shortcut Tricks
                        if (!question.shortcutTricks.isNullOrBlank()) {
                            Text(
                                text = "Step 4: AI Pro-Tip & Shortcut Strategy",
                                style = MaterialTheme.typography.labelLarge,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 4.dp)
                                    .background(Color(0xFFFFFAEB), RoundedCornerShape(8.dp)) // subtle gold callout
                                    .border(1.dp, Color(0xFFFBC02D).copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                                    .padding(8.dp),
                                verticalAlignment = Alignment.Top
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Info,
                                    contentDescription = "Pro Tip",
                                    tint = Color(0xFFFBC02D),
                                    modifier = Modifier.size(16.dp).padding(top = 2.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = question.shortcutTricks!!,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF5D4037)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
