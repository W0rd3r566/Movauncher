package org.arkikeskus.launcher

import com.google.common.truth.Truth.assertWithMessage
import org.junit.Test
import java.io.File

/**
 * The splash background is dark for the Movauncher light monogram before the launcher's
 * stored theme can be read; both resource modes must keep legible contrast.
 */
class SplashColourTest {
    private fun splashColour(values: String): String? {
        val file = File("src/main/res/$values/colors.xml")
        if (!file.isFile) return null
        return Regex("""<color name="splash_background">(#[0-9A-Fa-f]{6,8})</color>""").find(file.readText())?.groupValues?.get(1)
    }

    @Test fun theSplashKeepsTheMovauncherIconReadableInBothModes() {
        assertWithMessage("light").that(splashColour("values")).isEqualTo("#182320")
        assertWithMessage("dark").that(splashColour("values-night")).isEqualTo("#000000")
    }
}
