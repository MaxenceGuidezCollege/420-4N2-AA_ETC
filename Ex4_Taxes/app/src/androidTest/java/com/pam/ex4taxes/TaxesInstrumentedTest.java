package com.pam.ex4taxes;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertEquals;
import androidx.test.uiautomator.UiDevice;

import android.content.Context;
import android.os.RemoteException;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Rule;
import org.junit.Test;

public class TaxesInstrumentedTest {
    @Rule
    public ActivityScenarioRule rule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void useAppContext() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.pam.ex4taxes", appContext.getPackageName());
    }
    @Test
    public void hasInitialValue() {
        onView(withId(R.id.montant)).check(matches(withText("10")));
    }

//    TEST DISPLAY
    @Test
    public void displayCorrectly() {

        onView(withId(R.id.montant)).perform(replaceText("1"));
        onView(withId(R.id.button)).perform(click());

        onView(withId(R.id.tps)).check(matches(withText("0.05")));
        onView(withId(R.id.tvq)).check(matches(withText("0.1")));
        onView(withId(R.id.total)).check(matches(withText("1.15")));
    }

//    TEST CLICK BUTTON
    @Test
    public void valueChangeOnlyAfterClickOnButton1() {
        onView(withId(R.id.montant)).perform(replaceText("100"));

        onView(withId(R.id.tps)).check(matches(withText("0.5")));
        onView(withId(R.id.tvq)).check(matches(withText("0.98")));
        onView(withId(R.id.total)).check(matches(withText("11.48")));
    }
    @Test
    public void valueChangeOnlyAfterClickOnButton2() {
        onView(withId(R.id.montant)).perform(replaceText("100"));
        onView(withId(R.id.button)).perform(click());

        onView(withId(R.id.tps)).check(matches(withText("5.0")));
        onView(withId(R.id.tvq)).check(matches(withText("9.75")));
        onView(withId(R.id.total)).check(matches(withText("114.75")));
    }

//    TEST ROTATION
    @Test
    public void keepValueFromPortToLand() throws RemoteException, InterruptedException {
        UiDevice.getInstance(getInstrumentation()).setOrientationNatural();
        Thread.sleep(500);

        onView(withId(R.id.montant)).perform(replaceText("100"));
        onView(withId(R.id.button)).perform(click());

        UiDevice.getInstance(getInstrumentation()).setOrientationLeft();
        Thread.sleep(500);

        onView(withId(R.id.montant)).check(matches(withText("100")));
        onView(withId(R.id.tps)).check(matches(withText("5.0")));
        onView(withId(R.id.tvq)).check(matches(withText("9.75")));
        onView(withId(R.id.total)).check(matches(withText("114.75")));
    }
    @Test
    public void keepValueFromLandToPort() throws RemoteException, InterruptedException {
        UiDevice.getInstance(getInstrumentation()).setOrientationLeft();
        Thread.sleep(500);

        onView(withId(R.id.montant)).perform(replaceText("100"));
        onView(withId(R.id.button)).perform(click());

        UiDevice.getInstance(getInstrumentation()).setOrientationNatural();
        Thread.sleep(500);

        onView(withId(R.id.tps)).check(matches(withText("5.0")));
        onView(withId(R.id.tvq)).check(matches(withText("9.75")));
        onView(withId(R.id.total)).check(matches(withText("114.75")));
    }
}
