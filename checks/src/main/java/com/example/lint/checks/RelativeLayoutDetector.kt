package com.example.lint.checks

import com.android.SdkConstants.RELATIVE_LAYOUT
import com.android.resources.ResourceFolderType
import com.android.tools.lint.detector.api.*
import org.w3c.dom.Element

@Suppress("UnstableApiUsage")
class RelativeLayoutDetector: ResourceXmlDetector()  {

    override fun getApplicableElements(): Collection<String>? {
        return setOf(RELATIVE_LAYOUT)
    }

    override fun appliesTo(folderType: ResourceFolderType): Boolean {
        return folderType == ResourceFolderType.LAYOUT
    }

    override fun visitElement(context: XmlContext, element: Element) {
        context.report(
            issue = RelativeLayoutDetector.ISSUE,
            scope = element,
            location = context.getElementLocation(element),
            message = "This `<RelativeLayout>` should be replaced with a `<ConstraintLayout>`"
        )
    }

    companion object {
        val ISSUE = Issue.create(
            id = "RelativeLayout",
            briefDescription = "Detects usages of <RelativeLayout>",
            explanation = "Developers should use <ConstraintLayout> instead of <RelativeLayout>",
            category = Category.CORRECTNESS,
            severity = Severity.FATAL,
            implementation = Implementation(
                RelativeLayoutDetector::class.java,
                Scope.RESOURCE_FILE_SCOPE
            )
        )
    }
}