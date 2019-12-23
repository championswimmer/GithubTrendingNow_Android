package tech.arnav.githubtrending.ui


import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.internal.inject.InstrumentationContext
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import tech.arnav.githubtrending.AndroidTestUtils
import tech.arnav.githubtrending.R
import tech.arnav.lib.trendinggithub.models.Repository

@LargeTest
@RunWith(AndroidJUnit4::class)
class RepositoryDetailsActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(RepositoryDetailsActivity::class.java, false, false)

    @InstrumentationContext
    val context = InstrumentationRegistry.getInstrumentation().context

    val moshi = Moshi.Builder().build()

    @Test
    fun repositoryDetailsActivity_projectName_authorName_matches() {

        val repoList = moshi.adapter<List<Repository>>(
            Types.newParameterizedType(List::class.java, Repository::class.java)
        ).fromJson(
            AndroidTestUtils.readJson(context, "repositories.json")
        )

        mActivityTestRule.launchActivity(Intent().apply {
            putExtra(RepositoryDetailsActivity.BUNDLE_KEY_REPO, repoList!![1])
        })

        val textView = onView(
            allOf(
                withId(R.id.tvRepoName),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.FrameLayout::class.java),
                        1
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("naptha / tesseract.js")))

        val recyclerView = onView(
            allOf(
                withId(R.id.rvAuthors),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        recyclerView.check(matches(isDisplayed()))

        val textView2 = onView(
            allOf(
                withId(R.id.tvUsername),
                childAtPosition(
                    childAtPosition(
                        childAtPosition(
                            IsInstanceOf.instanceOf(RecyclerView::class.java),
                            0
                        ),
                        1
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("jeromewu")))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
