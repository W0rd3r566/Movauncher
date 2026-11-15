package org.arkikeskus.launcher.feature.settings

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import org.arkikeskus.launcher.ui.expressive.ExpressiveActionRow

internal const val MOVAUNCHER_SOURCE_URL = "https://github.com/W0rd3r566/Movauncher"

/** Open only the public Movauncher source repository; no payment or sponsorship links. */
internal fun openMovauncherSource(context: Context) = openLink(context, MOVAUNCHER_SOURCE_URL)

/** Compact footer in Settings with a single source-code action. */
@Composable
internal fun SourceCodeSection(onOpen: () -> Unit) {
    ExpressiveActionRow(
        label = stringResource(R.string.settings_source_code_title),
        description = stringResource(R.string.settings_source_code_desc),
    ) { onOpen() }
}
