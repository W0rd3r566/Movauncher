package org.arkikeskus.launcher.feature.settings

import android.app.Application
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertDoesNotExist
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [35], application = Application::class, qualifiers = "en")
class SourceCodeSectionTest {
    @get:Rule val compose = createComposeRule()
    private val resources = RuntimeEnvironment.getApplication().resources

    @Test fun footerHasOneSourceLinkAndNoSponsorButtons() {
        compose.setContent { SourceCodeSection(onOpen = {}) }
        compose.onNodeWithText(resources.getString(R.string.settings_source_code_title)).assertIsDisplayed()
        compose.onNodeWithText(resources.getString(R.string.settings_source_code_desc)).assertIsDisplayed()
        compose.onNodeWithText("Buy me a coffee").assertDoesNotExist()
        compose.onNodeWithText("PayPal").assertDoesNotExist()
    }

    @Test fun footerTapOpensOneLink() {
        var opens = 0
        compose.setContent { SourceCodeSection(onOpen = { opens++ }) }
        compose.onNodeWithText(resources.getString(R.string.settings_source_code_title)).performClick()
        assertThat(opens).isEqualTo(1)
    }
}
