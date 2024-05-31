package com.example.fptest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import android.content.Context;
import android.widget.ImageButton;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.fptest", appContext.getPackageName());
    }

    @Test
    public void testAppResources() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        String appName = appContext.getString(R.string.app_name);
        assertEquals("Vote.Inc", appName);
    }

    @Test
    public void testButtonsPresence() {
        try (ActivityScenario<HomePage> scenario = ActivityScenario.launch(HomePage.class)) {
            scenario.onActivity(activity -> {
                ImageButton profileButton = activity.findViewById(R.id.prof_btn);
                ImageButton scanButton = activity.findViewById(R.id.scan_btn);
                ImageButton livepolButton = activity.findViewById(R.id.livepol_btn);

                assertNotNull(profileButton);
                assertNotNull(scanButton);
                assertNotNull(livepolButton);
            });
        }
    }


}