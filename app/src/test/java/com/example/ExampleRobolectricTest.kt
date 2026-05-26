package com.example

import android.content.Context
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34], qualifiers = "w1000dp-h2000dp")
class ExampleRobolectricTest {

  @get:Rule
  val composeTestRule = createAndroidComposeRule<MainActivity>()

  @Test
  fun testE2EPracticeCustomizationFlow() {
    // 1. Verify we are on general dashboard
    composeTestRule.onNodeWithTag("welcome_streak_hud").assertExists()

    // 2. Click on general_aptitude subject card (fits onscreen due to w1000dp-h2000dp)
    composeTestRule.onNodeWithTag("subject_card_general_aptitude").performClick()

    // 3. Click on the first subtopic row
    composeTestRule.onNodeWithTag("subtopic_row_apt_verb_grammar_usage").performClick()

    // 4. Click on Practice Questions Tab (index 2)
    composeTestRule.onNodeWithTag("tab_button_2").performClick()

    // 5. Verify configuration view loads, then click Launch Practice Session
    composeTestRule.onNodeWithTag("launch_practice_session_btn").performClick()

    // 6. Check that active question is visible now (e.g. submit button exists)
    composeTestRule.onNodeWithTag("submit_ans_button").assertExists()
  }
}
