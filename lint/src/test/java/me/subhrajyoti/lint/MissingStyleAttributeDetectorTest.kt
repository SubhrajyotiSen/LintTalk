import com.android.tools.lint.checks.infrastructure.LintDetectorTest
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Issue
import me.subhrajyoti.lint.MissingStyleAttributeDetector
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class DeprecatedCustomViewDetectorTest : LintDetectorTest() {

    override fun getIssues(): MutableList<Issue> =
        mutableListOf(MissingStyleAttributeDetector.ISSUE)

    override fun getDetector(): Detector = MissingStyleAttributeDetector()

    @Test
    fun `check no lint error`() {
        lint()
            .files(
                xml(
                    "res/layout/layout.xml",
                    """
                        <TextView android:layout_width="match_parent"
                        android:layout_height="match_parent"
                        android:id="@+id/textSpacerNoButtons"
                        style="@style/TextAppearance.AppCompat"
                        xmlns:android="http://schemas.android.com/apk/res/android" />
                    """
                )
            )
            .run()
            .expectClean()
    }

    @Test
    fun `check exactly 1 lint error`() {
        lint()
            .files(
                xml(
                    "res/layout/layout.xml",
                    """
<TextView android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:id="@+id/textSpacerNoButtons"
    xmlns:android="http://schemas.android.com/apk/res/android" />
                        """
                )
            )
            .run()
            .expectErrorCount(1)
            .expect(
                """
res/layout/layout.xml:2: Error: Add style to TextView in order to provide consistent design [MissingStyleAttribute]
<TextView android:layout_width="match_parent"
^
1 errors, 0 warnings
            """
            )
    }
}
