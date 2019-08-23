Package com.ngonimahachi.convertit;

// HelpActivity class
public class HelpActivity extends Activity
{
    // On create
    @Override
    @SuppressWarnings("deprecation")
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // Get preferences
        SharedPreferences preferences =
                PreferenceManager.getDefaultSharedPreferences(this);

        boolean theme = preferences.getBoolean(Main.PREF_DARK, true);

        if (!theme)
            setTheme(R.style.AppLightTheme);

        setContentView(R.layout.help);

        TextView view = findViewById(R.id.help);
        String text = RawTextReader.read(this, R.raw.help);
        if (view != null)
        {
            view.setMovementMethod(LinkMovementMethod.getInstance());
            view.setText(Html.fromHtml(text));
        }

        // Enable back navigation on action bar
        ActionBar actionBar = getActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);
    }

    // On options item selected
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Get id
        int id = item.getItemId();
        switch (id)
        {
            // Home
            case android.R.id.home:
                finish();
                break;

            default:
                return false;
        }

        return true;
    }
}
