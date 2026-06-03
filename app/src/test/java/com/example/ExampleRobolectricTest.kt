package com.example

import android.content.Context
import androidx.compose.ui.test.junit4.createEmptyComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34], qualifiers = "w1000dp-h2000dp")
class ExampleRobolectricTest {

  @get:Rule
  val composeTestRule = createEmptyComposeRule()

  @Test
  fun testE2EPracticeCustomizationFlow() {
    // 1. Pre-authenticate by setting SharedPreferences before the Activity launches
    val context = ApplicationProvider.getApplicationContext<Context>()
    val sharedPrefs = context.getSharedPreferences("gate_prep_prefs", Context.MODE_PRIVATE)
    sharedPrefs.edit()
      .putBoolean("is_authenticated", true)
      .putString("aspirant_name", "Test Aspirant")
      .commit()

    // 2. Launch the Activity scenario
    ActivityScenario.launch(MainActivity::class.java).use {
      // 3. Verify we are on general dashboard now that we are pre-authenticated
      composeTestRule.onNodeWithTag("welcome_streak_hud").assertExists()

      // 4. Click on engineering_math subject card (fits onscreen due to w1000dp-h2000dp)
      composeTestRule.onNodeWithTag("subject_card_engineering_math").performClick()

      // 5. Click on the first subtopic row
      composeTestRule.onNodeWithTag("subtopic_row_math_la_matrix_algebra").performClick()

      // 6. Click on Practice Questions Tab (index 2)
      composeTestRule.onNodeWithTag("tab_button_2").performClick()

      // 7. Verify configuration view loads, then click Launch Practice Session
      composeTestRule.onNodeWithTag("launch_practice_session_btn").performClick()

      // 8. Check that active question is visible now (e.g. submit button exists)
      composeTestRule.onNodeWithTag("submit_ans_button").assertExists()
    }
  }

  @Test
  fun testE2ECbtMockTestFlow() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val sharedPrefs = context.getSharedPreferences("gate_prep_prefs", Context.MODE_PRIVATE)
    sharedPrefs.edit()
      .putBoolean("is_authenticated", true)
      .putString("aspirant_name", "Test Aspirant")
      .commit()

    ActivityScenario.launch(MainActivity::class.java).use {
      // 1. Navigate to CBT mock screen
      composeTestRule.onNodeWithTag("rail_mock").performClick()

      // 2. Click on the Launch CBT Test button
      composeTestRule.onNodeWithTag("launch_cbt_test_btn").performClick()
    }
  }

  @Test
  fun testE2ELoginScreenFlow() {
    ActivityScenario.launch(MainActivity::class.java).use {
      // 1. Enter aspirant name
      composeTestRule.onNodeWithTag("name_input").performTextInput("Amit")
      // 2. Enter expected password matching: Name + " can do it"
      composeTestRule.onNodeWithTag("password_input").performTextInput("Amit can do it")
      // 3. Click authenticate button
      composeTestRule.onNodeWithTag("authenticate_button").performClick()
      // 4. Verify we are navigated to the hub dashboard
      composeTestRule.onNodeWithTag("welcome_streak_hud").assertExists()
    }
  }
}
