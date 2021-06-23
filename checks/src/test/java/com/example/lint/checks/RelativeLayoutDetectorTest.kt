package com.example.lint.checks

import com.android.tools.lint.checks.infrastructure.TestFiles.java
import com.android.tools.lint.checks.infrastructure.TestFiles.xml
import com.android.tools.lint.checks.infrastructure.TestLintTask.lint
import org.junit.Test

@Suppress("UnstableApiUsage")
class RelativeLayoutDetectorTest {
    @Test
    fun testUseOfRelativeLayout() {
        lint().files(
            xml(
                "/res/layout/test.xml",
                """<?xml version="1.0" encoding="utf-8"?>
                            <RelativeLayout
                                xmlns:android="http://schemas.android.com/apk/res/android"
                                xmlns:tools="http://schemas.android.com/tools"
                                android:layout_width="match_parent"
                                android:layout_height="match_parent"
                                tools:context=".MainActivity">
                            </RelativeLayout>
                    """
            )
        )
            .issues(RelativeLayoutDetector.ISSUE)
            .run()
            .expectContains("This <RelativeLayout> should be replaced with a <ConstraintLayout> [RelativeLayout]")
            .expectErrorCount(1)
            .expectWarningCount(0)
    }
}
