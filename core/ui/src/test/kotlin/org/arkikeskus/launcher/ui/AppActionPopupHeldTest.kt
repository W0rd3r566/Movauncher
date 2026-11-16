package org.arkikeskus.launcher.ui

import android.app.Application
import android.os.Process
import android.view.WindowManager
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.IntOffset
import com.google.common.truth.Truth.assertThat
import org.arkikeskus.launcher.model.AppItem
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Issue #12: a second finger must land in the popup, while action rows remain inert
 * until the finger holding the icon lifts.
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [35], application = Application::class, qualifiers = "en")
class AppActionPopupHeldTest {
    @get:Rule val compose = createComposeRule()

    private val app = AppItem("com.example", "Main", Process.myUserHandle(), 0L, "Example")
    private var held by mutableStateOf(false)
    private var picked = 0

    private fun show() {
        compose.setContent {
            AppActionPopup(
                app = app,
                anchor = IntOffset(200, 200),
                preferAbove = false,
                actions = listOf(PopupAction("App info") { picked++ }),
                onDismiss = {},
                held = held,
            )
        }
        compose.waitUntil(5_000) {
            compose.onAllNodesWithText("App info").fetchSemanticsNodes().isNotEmpty()
        }
    }

    /**
     * Assert the exact flags used to create the PopupProperties passed to Popup.
     * ShadowWindowManagerImpl's live PopupLayout is not safe to inspect from
     * a host-side Robolectric test (non-deterministic CalledFromWrongThreadException).
     */
    @Test fun theMenuWindowTakesASecondFinger() {
        assertThat(ACTION_POPUP_WINDOW_FLAGS and WindowManager.LayoutParams.FLAG_SPLIT_TOUCH).isNotEqualTo(0)
        assertThat(ACTION_POPUP_WINDOW_FLAGS and WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH).isNotEqualTo(0)
        assertThat(ACTION_POPUP_WINDOW_FLAGS and WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE).isEqualTo(0)
    }

    @Test fun whileTheIconIsHeldARowDoesNothing() {
        compose.runOnIdle { held = true }
        show()

        compose.onNodeWithText("App info").performClick()
        compose.waitForIdle()
        assertThat(picked).isEqualTo(0)

        compose.runOnIdle { held = false }
        compose.waitForIdle()
        compose.onNodeWithText("App info").performClick()
        assertThat(picked).isEqualTo(1)
    }
}
