package tech.arnav.githubtrending.ui


import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.squareup.moshi.Moshi
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import tech.arnav.githubtrending.R
import tech.arnav.lib.trendinggithub.models.Repository

@LargeTest
@RunWith(AndroidJUnit4::class)
class RepositoryDetailsActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(RepositoryDetailsActivity::class.java)

    val moshi = Moshi.Builder().build()

    @Test
    fun repositoryDetailsActivityTest() {

        val repository = moshi.adapter<Repository>(Repository::class.java).fromJson(
            """
            {
                "author": "naptha",
                "name": "tesseract.js",
                "avatar": "https://github.com/naptha.png",
                "url": "https://github.com/naptha/tesseract.js",
                "description": "Pure Javascript OCR for more than 100 Languages 📖🎉🖥",
                "language": "JavaScript",
                "languageColor": "#f1e05a",
                "stars": 19186,
                "forks": 1299,
                "currentPeriodStars": 671,
                "builtBy": [
                  {
                    "username": "jeromewu",
                    "href": "https://github.com/jeromewu",
                    "avatar": "https://avatars3.githubusercontent.com/u/5723124"
                  },
                  {
                    "username": "bijection",
                    "href": "https://github.com/bijection",
                    "avatar": "https://avatars3.githubusercontent.com/u/8824442"
                  },
                  {
                    "username": "antimatter15",
                    "href": "https://github.com/antimatter15",
                    "avatar": "https://avatars2.githubusercontent.com/u/30054"
                  },
                  {
                    "username": "loderunner",
                    "href": "https://github.com/loderunner",
                    "avatar": "https://avatars0.githubusercontent.com/u/731497"
                  }
                ]
              }
        """.trimIndent()
        )

        mActivityTestRule.launchActivity(Intent().apply {
            putExtra(RepositoryDetailsActivity.BUNDLE_KEY_REPO, repository)
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
