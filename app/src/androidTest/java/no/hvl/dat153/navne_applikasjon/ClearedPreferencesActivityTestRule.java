package no.hvl.dat153.navne_applikasjon;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

public class ClearedPreferencesActivityTestRule<T extends Activity> extends ActivityTestRule<T> {

    public ClearedPreferencesActivityTestRule(Class<T> activityClass) {
        super(activityClass);
    }

    @Override
    protected void beforeActivityLaunched() {
        super.beforeActivityLaunched();

        // clear preferences in target context before starting test.
        Context context = InstrumentationRegistry.getTargetContext();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPreferences.edit().clear().apply();
    }

}