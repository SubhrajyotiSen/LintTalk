package me.subhrajyoti.lint

import com.android.SdkConstants
import com.android.annotations.Nullable
import com.android.resources.ResourceFolderType
import com.android.tools.lint.detector.api.*
import org.w3c.dom.Element

@Suppress("UnstableApiUsage")
class MissingStyleAttributeDetector : ResourceXmlDetector() {

    companion object {
        val ISSUE: Issue = Issue.create(
            "MissingStyleAttribute",
            "style attribute is missing",
            "We should use style to style a TextView " +
                    "in order to provide consistent design",
            Category.CORRECTNESS,
            5,
            Severity.ERROR,
            Implementation(
                MissingStyleAttributeDetector::class.java,
                Scope.RESOURCE_FILE_SCOPE
            )
        )
    }

    @Nullable
    override fun getApplicableElements(): Collection<String>? {
        return listOf(SdkConstants.TEXT_VIEW)
    }

    override fun visitElement(context: XmlContext, element: Element) {
        if (!element.hasAttribute(SdkConstants.ATTR_STYLE)) {
            context.report(
                ISSUE,
                element,
                context.getLocation(element),
                "Add style to TextView in order to provide consistent design"
            )
        }
    }

    // Only XML files
    override fun appliesTo(folderType: ResourceFolderType): Boolean {
        return folderType == ResourceFolderType.LAYOUT
    }
}