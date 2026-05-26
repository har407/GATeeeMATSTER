package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.*
import com.example.ui.*
import com.example.ui.theme.*

sealed class Screen {
    object Dashboard : Screen()
    data class SubjectDetail(val subject: Subject) : Screen()
    data class ActiveStudy(val subjectId: String, val topicId: String, val subtopicId: String) : Screen()
    object Bookmarks : Screen()
    object Mistakes : Screen()
    object MockCbt : Screen()
}

class MainActivity : ComponentActivity() {

    private val viewModel: GateViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MyApplicationTheme {
                val config = LocalConfiguration.current
                val isTablet = config.screenWidthDp >= 600

                var currentScreen by remember { mutableStateOf<Screen>(Screen.Dashboard) }

                // Gracefully handle hardware & system gesture back presses
                BackHandler(enabled = currentScreen != Screen.Dashboard) {
                    when (val screen = currentScreen) {
                        is Screen.SubjectDetail -> {
                            currentScreen = Screen.Dashboard
                        }
                        is Screen.ActiveStudy -> {
                            val subject = viewModel.repository.getSubjectById(screen.subjectId)
                            if (subject != null) {
                                currentScreen = Screen.SubjectDetail(subject)
                            } else {
                                currentScreen = Screen.Dashboard
                            }
                        }
                        Screen.Bookmarks, Screen.Mistakes, Screen.MockCbt -> {
                            currentScreen = Screen.Dashboard
                        }
                        Screen.Dashboard -> {
                            // Let the system handle default exit action
                        }
                    }
                }

                // Safe area drawing container
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                        .windowInsetsPadding(WindowInsets.statusBars),
                    bottomBar = {
                        // On compact mobile, show standard elegant custom Bottom Navigation
                        if (!isTablet && currentScreen == Screen.Dashboard) {
                            NavigationBar(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .navigationBarsPadding(),
                                containerColor = MaterialTheme.colorScheme.surface
                            ) {
                                NavigationBarItem(
                                    selected = currentScreen is Screen.Dashboard,
                                    onClick = { currentScreen = Screen.Dashboard },
                                    icon = { Icon(Icons.Default.Home, contentDescription = "Dashboard") },
                                    label = { Text("Hub", fontWeight = FontWeight.Bold) },
                                    modifier = Modifier.testTag("nav_hub")
                                )
                                NavigationBarItem(
                                    selected = currentScreen is Screen.Bookmarks,
                                    onClick = { currentScreen = Screen.Bookmarks },
                                    icon = { Icon(Icons.Default.Bookmark, contentDescription = "Bookmarks") },
                                    label = { Text("Starred", fontWeight = FontWeight.Bold) },
                                    modifier = Modifier.testTag("nav_starred")
                                )
                                NavigationBarItem(
                                    selected = currentScreen is Screen.Mistakes,
                                    onClick = { currentScreen = Screen.Mistakes },
                                    icon = { Icon(Icons.Default.HistoryEdu, contentDescription = "Mistakes") },
                                    label = { Text("Mistakes", fontWeight = FontWeight.Bold) },
                                    modifier = Modifier.testTag("nav_mistakes")
                                )
                                NavigationBarItem(
                                    selected = currentScreen is Screen.MockCbt,
                                    onClick = { currentScreen = Screen.MockCbt },
                                    icon = { Icon(Icons.Default.Task, contentDescription = "CBT Test") },
                                    label = { Text("Mock", fontWeight = FontWeight.Bold) },
                                    modifier = Modifier.testTag("nav_mock")
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = innerPadding.calculateBottomPadding())
                    ) {
                        // On Expanded Tablet screen canonical layout, display Navigation Rail on left!
                        if (isTablet) {
                            NavigationRail(
                                containerColor = MaterialTheme.colorScheme.surface,
                                header = {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        modifier = Modifier.padding(top = 16.dp, bottom = 24.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.School,
                                            contentDescription = "Logo",
                                            tint = MaterialTheme.colorScheme.primary,
                                            modifier = Modifier.size(36.dp)
                                        )
                                        Spacer(modifier = Modifier.height(8.dp))
                                        Text(
                                            text = "GATE Prep",
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.ExtraBold,
                                            color = MaterialTheme.colorScheme.primary
                                        )
                                    }
                                }
                            ) {
                                NavigationRailItem(
                                    selected = currentScreen is Screen.Dashboard,
                                    onClick = { currentScreen = Screen.Dashboard },
                                    icon = { Icon(Icons.Default.Home, contentDescription = "Dashboard") },
                                    label = { Text("Study Hub") },
                                    modifier = Modifier.testTag("rail_hub")
                                )
                                NavigationRailItem(
                                    selected = currentScreen is Screen.Bookmarks,
                                    onClick = { currentScreen = Screen.Bookmarks },
                                    icon = { Icon(Icons.Default.Bookmark, contentDescription = "Bookmarks") },
                                    label = { Text("Starred") },
                                    modifier = Modifier.testTag("rail_starred")
                                )
                                NavigationRailItem(
                                    selected = currentScreen is Screen.Mistakes,
                                    onClick = { currentScreen = Screen.Mistakes },
                                    icon = { Icon(Icons.Default.HistoryEdu, contentDescription = "Mistakes") },
                                    label = { Text("Mistakes") },
                                    modifier = Modifier.testTag("rail_mistakes")
                                )
                                NavigationRailItem(
                                    selected = currentScreen is Screen.MockCbt,
                                    onClick = { currentScreen = Screen.MockCbt },
                                    icon = { Icon(Icons.Default.Task, contentDescription = "CBT Test") },
                                    label = { Text("CBT Mock") },
                                    modifier = Modifier.testTag("rail_mock")
                                )
                            }
                        }

                        // Content Pane
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                        ) {
                            AnimatedContent(
                                targetState = currentScreen,
                                transitionSpec = {
                                    fadeIn(animationSpec = tween(220)) togetherWith fadeOut(animationSpec = tween(220))
                                },
                                label = "ScreenTransition"
                            ) { target ->
                                when (target) {
                                    is Screen.Dashboard -> {
                                        GateDashboardScreen(
                                            viewModel = viewModel,
                                            onSubjectClick = { currentScreen = Screen.SubjectDetail(it) },
                                            onNavigateToBookmarks = { currentScreen = Screen.Bookmarks },
                                            onNavigateToMistakes = { currentScreen = Screen.Mistakes },
                                            onStartMockTest = { currentScreen = Screen.MockCbt }
                                        )
                                    }
                                    is Screen.SubjectDetail -> {
                                        SubjectDetailScreen(
                                            subject = target.subject,
                                            onBack = { currentScreen = Screen.Dashboard },
                                            viewModel = viewModel,
                                            onSubtopicClick = { _, sub ->
                                                currentScreen = Screen.ActiveStudy(
                                                    subjectId = target.subject.id,
                                                    topicId = sub.topicId,
                                                    subtopicId = sub.id
                                                )
                                            }
                                        )
                                    }
                                    is Screen.ActiveStudy -> {
                                        ActiveStudyWorkspaceScreen(
                                            subjectId = target.subjectId,
                                            topicId = target.topicId,
                                            subtopicId = target.subtopicId,
                                            viewModel = viewModel,
                                            onBack = {
                                                // Find subject to return to details screen
                                                val subject = viewModel.repository.getSubjectById(target.subjectId)
                                                if (subject != null) {
                                                    currentScreen = Screen.SubjectDetail(subject)
                                                } else {
                                                    currentScreen = Screen.Dashboard
                                                }
                                            }
                                        )
                                    }
                                    is Screen.Bookmarks -> {
                                        BookmarksListScreen(
                                            viewModel = viewModel,
                                            onBack = { currentScreen = Screen.Dashboard }
                                        )
                                    }
                                    is Screen.Mistakes -> {
                                        MistakesNotebookScreen(
                                            viewModel = viewModel,
                                            onBack = { currentScreen = Screen.Dashboard }
                                        )
                                    }
                                    is Screen.MockCbt -> {
                                        CbtMockTestPlatformScreen(
                                            viewModel = viewModel,
                                            onBack = { currentScreen = Screen.Dashboard }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
