package org.arkikeskus.launcher.feature.settings

import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [35], application = Application::class, qualifiers = "en")
class SourceCodeLinkTest {
    private class Recorder(base: Context) : ContextWrapper(base) {
        val started = mutableListOf<Intent>()
        override fun startActivity(intent: Intent) { started.add(intent) }
    }

    @Test fun opensTheMovauncherRepositoryInABrowser() {
        val context = Recorder(RuntimeEnvironment.getApplication())
        openMovauncherSource(context)
        val intent = context.started.single()
        assertThat(intent.action).isEqualTo(Intent.ACTION_VIEW)
        assertThat(intent.dataString).isEqualTo("https://github.com/W0rd3r566/Movauncher")
        assertThat(intent.`package`).isNull()
    }
}
