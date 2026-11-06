package com.xcedy.launcher.ui

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

private val tabs = listOf("Главная", "Моды", "Ресурспаки", "Сборки")

// TODO: replace with versions from imported APKs (step 3)
private val sampleVersions = listOf("26.33", "1.21.120", "1.21.100")

@Composable
fun LauncherApp(onLaunch: () -> Boolean) {
    LauncherTheme {
        var tab by remember { mutableIntStateOf(0) }
        var dialog by remember { mutableStateOf<String?>(null) }

        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            Row(Modifier.fillMaxSize().systemBarsPadding().padding(12.dp)) {
                Column(Modifier.weight(1f)) {
                    TabRow(selectedTabIndex = tab, containerColor = MaterialTheme.colorScheme.surface) {
                        tabs.forEachIndexed { i, name ->
                            Tab(selected = tab == i, onClick = { tab = i }, text = { Text(name) })
                        }
                    }
                    Spacer(Modifier.height(16.dp))
                    when (tab) {
                        0 -> HomeScreen(onLaunch, onAddAccount = { dialog = "account" })
                        else -> Placeholder(tabs[tab])
                    }
                }
                Spacer(Modifier.width(12.dp))
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedButton(onClick = { dialog = "about" }) { Text("О лаунчере") }
                    OutlinedButton(onClick = { dialog = "settings" }) { Text("Настройки") }
                }
            }
        }

        when (dialog) {
            "account" -> AlertDialog(
                onDismissRequest = { dialog = null },
                title = { Text("Добавить аккаунт") },
                text = { Text("Выбери тип входа") },
                confirmButton = { TextButton(onClick = { dialog = null }) { Text("Xbox") } },
                dismissButton = { TextButton(onClick = { dialog = null }) { Text("Офлайн") } },
            )
            "about" -> InfoDialog("О лаунчере", "Xcedy Launcher 0.1.0") { dialog = null }
            "settings" -> InfoDialog("Настройки", "Скоро здесь будут настройки") { dialog = null }
        }
    }
}

@Composable
private fun HomeScreen(onLaunch: () -> Boolean, onAddAccount: () -> Unit) {
    val context = LocalContext.current
    var version by remember { mutableStateOf(sampleVersions.first()) }
    var menu by remember { mutableStateOf(false) }

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        OutlinedButton(onClick = onAddAccount) { Text("+ Аккаунт") }
        Box {
            OutlinedButton(onClick = { menu = true }) { Text("Версия: $version") }
            DropdownMenu(expanded = menu, onDismissRequest = { menu = false }) {
                sampleVersions.forEach { v ->
                    DropdownMenuItem(text = { Text(v) }, onClick = { version = v; menu = false })
                }
            }
        }
        Button(
            onClick = {
                if (!onLaunch()) {
                    Toast.makeText(context, "Minecraft не установлен", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.height(56.dp),
        ) { Text("Запуск") }
    }
}

@Composable
private fun Placeholder(name: String) {
    Box(Modifier.fillMaxWidth().padding(24.dp), contentAlignment = Alignment.Center) {
        Text("$name — скоро")
    }
}

@Composable
private fun InfoDialog(title: String, text: String, onClose: () -> Unit) {
    AlertDialog(
        onDismissRequest = onClose,
        title = { Text(title) },
        text = { Text(text) },
        confirmButton = { TextButton(onClick = onClose) { Text("Ок") } },
    )
}
