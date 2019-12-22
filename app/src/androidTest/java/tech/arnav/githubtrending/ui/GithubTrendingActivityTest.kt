package tech.arnav.githubtrending.ui


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import tech.arnav.githubtrending.R

@LargeTest
@RunWith(AndroidJUnit4::class)
class GithubTrendingActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(GithubTrendingActivity::class.java)

    @Test
    fun githubTrendingActivityTest() {
        val frameLayout = onView(
            allOf(
                withId(R.id.menu_repositories), withContentDescription("Repositories"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.bottomNav),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        frameLayout.check(matches(isDisplayed()))

        val frameLayout2 = onView(
            allOf(
                withId(R.id.menu_developers), withContentDescription("Developers"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.bottomNav),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        frameLayout2.check(matches(isDisplayed()))

        val frameLayout3 = onView(
            allOf(
                withId(R.id.menu_languages), withContentDescription("Languages"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.bottomNav),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        frameLayout3.check(matches(isDisplayed()))

        val recyclerView = onView(
            allOf(
                withId(R.id.recyclerView),
                childAtPosition(
                    allOf(
                        withId(R.id.swipeRefreshLayout),
                        childAtPosition(
                            IsInstanceOf.instanceOf(android.widget.FrameLayout::class.java),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        recyclerView.check(matches(isDisplayed()))
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
