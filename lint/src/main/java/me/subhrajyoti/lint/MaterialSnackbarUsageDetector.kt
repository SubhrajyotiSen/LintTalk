package me.subhrajyoti.lint

import com.android.tools.lint.detector.api.*
import com.intellij.psi.PsiMethod
import org.jetbrains.uast.UCallExpression

@Suppress("UnstableApiUsage")
class MaterialSnackbarUsageDetector : Detector(), SourceCodeScanner {
    companion object {
        private const val EXPLANATION = "Use TickerTape Custom Snackbar Instead"
        const val DESCRIPTION = "Using Native Snackbar Not Allowed"

        val ISSUE = Issue.create(
            "customSnackbarIssue",
            DESCRIPTION,
            EXPLANATION,
            Category.CORRECTNESS,
            5,
            Severity.ERROR,
            Implementation(
                MaterialSnackbarUsageDetector::class.java,
                Scope.JAVA_FILE_SCOPE
            )
        )
    }

    override fun getApplicableMethodNames() = listOf("make")

    override fun visitMethodCall(context: JavaContext, node: UCallExpression, method: PsiMethod) {
        if (context.evaluator.isMemberInClass(
                method,
                "com.google.android.material.snackbar.Snackbar"
            )) {
            reportUsage(context, node)
        }
    }

    private fun reportUsage(
        context: JavaContext,
        node: UCallExpression
    ) {
        context.report(ISSUE, node, context.getLocation(node), DESCRIPTION)
    }

}