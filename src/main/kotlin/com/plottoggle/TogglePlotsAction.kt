package com.plottoggle

import com.intellij.icons.AllIcons
import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.ToggleAction
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project

/**
 * Toolbar toggle that controls PyCharm's "Show plots in tool window" setting
 * (Settings > Tools > Python Plots).
 *
 * The setting lives in `com.intellij.python.scientific.PySciApplicationComponent`,
 * a PersistentStateComponent backed by pySciSettings.xml.
 *
 * Accessed via reflection at compile time because the scientific content module's
 * classes are not on the Gradle compile classpath. The v2 `<dependencies>` block
 * in plugin.xml grants runtime classloader access to the module.
 */
class TogglePlotsAction : ToggleAction(
    "Plots in Tool Window",
    "Toggle PyCharm's 'Show plots in tool window' setting",
    AllIcons.Actions.Show
), DumbAware {

    companion object {
        private const val SERVICE_FQN =
            "com.intellij.python.scientific.PySciApplicationComponent"
    }

    private fun service(): Any? = try {
        Class.forName(SERVICE_FQN).getMethod("getInstance").invoke(null)
    } catch (_: Exception) {
        null
    }

    override fun isSelected(e: AnActionEvent): Boolean {
        val svc = service() ?: return false
        return try {
            svc.javaClass.getMethod("isMatplotlibInToolwindow").invoke(svc) as Boolean
        } catch (_: Exception) {
            false
        }
    }

    override fun setSelected(e: AnActionEvent, state: Boolean) {
        val svc = service() ?: return
        val project = e.project ?: return
        try {
            svc.javaClass.getMethod(
                "setMatplotlibInToolwindow",
                Project::class.java,
                Boolean::class.javaPrimitiveType
            ).invoke(svc, project, state)
        } catch (_: Exception) {
            // scientific module unavailable
        }
    }

    override fun update(e: AnActionEvent) {
        super.update(e)
        e.presentation.isEnabledAndVisible = service() != null && e.project != null
    }

    override fun getActionUpdateThread(): ActionUpdateThread =
        ActionUpdateThread.BGT
}
