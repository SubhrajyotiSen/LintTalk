package me.subhrajyoti.lint

import com.android.annotations.Nullable
import com.android.resources.ResourceFolderType
import com.android.tools.lint.detector.api.*
import org.w3c.dom.Element

@Suppress("UnstableApiUsage")
class SpaceDetector : ResourceXmlDetector() {

    companion object {
        val ISSUE: Issue = Issue.create(
            "Missing Space",
            "Don't use deprecated Space",
            "Don't use deprecated Space",
            Category.CORRECTNESS,
            5,
            Severity.ERROR,
            Implementation(
                SpaceDetector::class.java,
                Scope.RESOURCE_FILE_SCOPE
            )
        )
    }

    @Nullable
    override fun getApplicableElements(): Collection<String>? {
        return listOf("androidx.legacy.widget.Space")
    }

    override fun visitElement(context: XmlContext, element: Element) {
        context.report(
            ISSUE,
            element,
            context.getLocation(element),
            "Replace with android.widget.Space"
        )
    }

    // Only XML files
    override fun appliesTo(folderType: ResourceFolderType): Boolean {
        return folderType == ResourceFolderType.LAYOUT
    }
}