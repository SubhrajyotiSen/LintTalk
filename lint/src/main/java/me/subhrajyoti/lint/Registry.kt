package me.subhrajyoti.lint

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.CURRENT_API
import com.android.tools.lint.detector.api.Issue
import org.jetbrains.annotations.NotNull

@Suppress("UnstableApiUsage")
class Registry : IssueRegistry() {

    override val api: Int = CURRENT_API

    @get:NotNull
    override val issues: List<Issue>
        get() = listOf(MissingStyleAttributeDetector.ISSUE, MaterialSnackbarUsageDetector.ISSUE)
}