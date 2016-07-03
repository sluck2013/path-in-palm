package com.yansong_wang.pathinpalm;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withResourceName;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MapFragmentTest {

    @Rule
    public ActivityTestRule<MainActivity> activity = new ActivityTestRule<>(MainActivity.class, true, true);

    @Before
    public void setUp() {
        activity.getActivity()
                .getFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_main_content, new MapFragment())
                .commit();
    }

    @Test
    public void shouldInitialize() {
        onView(withId(R.id.img_view_map)).check(matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testTapOptionsMenu() {
        whenOpenOptionsMenu();
        whenClickShowWeekdayDaytime();
        thenWeekDayDaytimeMapIsShown();
        whenOpenOptionsMenu();
        whenClickShowWeekend();
        whenOpenOptionsMenu();
        whenClickShowAll();
    }

    private void whenOpenOptionsMenu() {
        openActionBarOverflowOrOptionsMenu(activity.getActivity().getApplicationContext());
    }

    private void whenClickShowWeekdayDaytime() {
        onView(withText(R.string.options_menu_item_map_weekday)).perform(click());
    }

    private void thenWeekDayDaytimeMapIsShown() {
        //onView(withId(R.id.img_view_map)).check(matches(withResourceName("weekday")));
    }

    private void whenClickShowWeekend() {
        onView(withText(R.string.options_menu_item_map_weekend)).perform(click());
    }

    private void whenClickShowAll() {
        onView(withText(R.string.options_menu_item_map_show_all)).perform(click());
    }
}
