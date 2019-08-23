Package com.ngonimahachi.convertit;
import android.app.ActionBar;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;

import com.ngonimahachi.convertit.BuildConfig;
import com.ngonimahachi.convertit.R;

// SettingsFragment class
@SuppressWarnings("deprecation")
public class SettingsFragment extends android.preference.PreferenceFragment
        implements SharedPreferences.OnSharedPreferenceChangeListener
{
    // On create
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);

        SharedPreferences preferences =
                PreferenceManager.getDefaultSharedPreferences(getActivity());

        ListPreference preference =
                (ListPreference) findPreference(Main.PREF_DIGITS);

        // Set summary to be the user-description for the selected value
        preference.setSummary(preference.getEntry());

        // Get about summary
        Preference about = findPreference(Main.PREF_ABOUT);
        String sum = (String) about.getSummary();

        // Set version in text view
        String s = String.format(sum, BuildConfig.VERSION_NAME);
        about.setSummary(s);
    }

    // on Resume
    @Override
    public void onResume()
    {
        super.onResume();
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    // on Pause
    @Override
    public void onPause()
    {
        super.onPause();
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    // On preference tree click
    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen,
                                         Preference preference)
    {
        boolean result =
                super.onPreferenceTreeClick(preferenceScreen, preference);

        // Set home as up
        if (preference instanceof PreferenceScreen)
        {
            Dialog dialog = ((PreferenceScreen) preference).getDialog();
            ActionBar actionBar = dialog.getActionBar();
            actionBar.setDisplayHomeAsUpEnabled(false);
        }

        return result;
    }

    // On shared preference changed
    @Override
    public void onSharedPreferenceChanged(SharedPreferences preferences,
                                          String key)
    {
        if (key.equals(Main.PREF_DIGITS))
        {
            ListPreference preference = (ListPreference) findPreference(key);

            // Set summary to be the user-description for the selected value
            preference.setSummary(preference.getEntry());
        }

        if (key.equals(Main.PREF_DARK))
        {
            if (Build.VERSION.SDK_INT != Main.VERSION_M)
                getActivity().recreate();
        }
    }
}
