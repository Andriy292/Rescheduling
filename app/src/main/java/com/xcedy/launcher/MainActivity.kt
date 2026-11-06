package com.xcedy.launcher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.xcedy.launcher.ui.LauncherApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { LauncherApp(onLaunch = ::launchMinecraft) }
    }

    /** Opens the installed Minecraft Bedrock. Returns false if it is not installed. */
    private fun launchMinecraft(): Boolean {
        val intent = packageManager.getLaunchIntentForPackage("com.mojang.minecraftpe")
            ?: return false
        startActivity(intent)
        return true
    }
}
