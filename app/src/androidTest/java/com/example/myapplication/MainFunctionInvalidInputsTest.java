package com.example.myapplication;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainFunctionInvalidInputsTest {

    @Rule
    public ActivityTestRule<HelpIntroActivity> mActivityTestRule = new ActivityTestRule<>(HelpIntroActivity.class);

    @Test
    public void mainFunctionInvalidInputsTest() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.nextButton), withText("NEXT"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.nextButton), withText("NEXT"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.getStartedButton), withText("Go"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.searchText1),
                        childAtPosition(
                                allOf(withId(R.id.pickTV),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        textInputEditText.perform(replaceText("hahah"), closeSoftKeyboard());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.searchButton), withText("Search"),
                        childAtPosition(
                                allOf(withId(R.id.pickTV),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                2),
                        isDisplayed()));
        appCompatButton4.perform(click());

        ViewInteraction textInputEditText2 = onView(
                allOf(withId(R.id.searchText1),
                        childAtPosition(
                                allOf(withId(R.id.pickTV),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        textInputEditText2.perform(click());

        ViewInteraction textInputEditText3 = onView(
                allOf(withId(R.id.searchText1),
                        childAtPosition(
                                allOf(withId(R.id.pickTV),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        textInputEditText3.perform(replaceText("prze< 350"), closeSoftKeyboard());

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.searchButton), withText("Search"),
                        childAtPosition(
                                allOf(withId(R.id.pickTV),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                2),
                        isDisplayed()));
        appCompatButton5.perform(click());

        ViewInteraction textInputEditText4 = onView(
                allOf(withId(R.id.searchText1),
                        childAtPosition(
                                allOf(withId(R.id.pickTV),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        textInputEditText4.perform(click());

        ViewInteraction textInputEditText5 = onView(
                allOf(withId(R.id.searchText1),
                        childAtPosition(
                                allOf(withId(R.id.pickTV),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        textInputEditText5.perform(replaceText("ciity;"), closeSoftKeyboard());

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(R.id.searchButton), withText("Search"),
                        childAtPosition(
                                allOf(withId(R.id.pickTV),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                2),
                        isDisplayed()));
        appCompatButton6.perform(click());

        ViewInteraction textInputEditText6 = onView(
                allOf(withId(R.id.searchText1),
                        childAtPosition(
                                allOf(withId(R.id.pickTV),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        textInputEditText6.perform(click());

        ViewInteraction textInputEditText7 = onView(
                allOf(withId(R.id.searchText1),
                        childAtPosition(
                                allOf(withId(R.id.pickTV),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        textInputEditText7.perform(replaceText("bell; price < 500"), closeSoftKeyboard());

        ViewInteraction appCompatButton7 = onView(
                allOf(withId(R.id.searchButton), withText("Search"),
                        childAtPosition(
                                allOf(withId(R.id.pickTV),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                2),
                        isDisplayed()));
        appCompatButton7.perform(click());

        ViewInteraction textInputEditText8 = onView(
                allOf(withId(R.id.searchText1),
                        childAtPosition(
                                allOf(withId(R.id.pickTV),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        textInputEditText8.perform(click());

        ViewInteraction textInputEditText9 = onView(
                allOf(withId(R.id.searchText1),
                        childAtPosition(
                                allOf(withId(R.id.pickTV),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        textInputEditText9.perform(replaceText("City; bathroom > 5"), closeSoftKeyboard());

        ViewInteraction appCompatButton8 = onView(
                allOf(withId(R.id.searchButton), withText("Search"),
                        childAtPosition(
                                allOf(withId(R.id.pickTV),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                2),
                        isDisplayed()));
        appCompatButton8.perform(click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
