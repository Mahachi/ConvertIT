import android.content.Context;
import android.icu.text.DateFormat;
import android.preference.DialogPreference;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.ngonimahachi.convertit.BuildConfig;
import com.ngonimahachi.convertit.R;Package.com.ngonimahachi.convertit;


// AboutPreference class
public class AboutPreference extends DialogPreference
{
    // Constructor
    public AboutPreference(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    // On bind dialog view
    @Override
    protected void onBindDialogView(View view)
    {
        super.onBindDialogView(view);

        // Get version text view
        TextView version = view.findViewById(R.id.about);

        // Set version in text view
        if (version != null)
        {
            String v = (String) version.getText();
            String s = String.format(v, BuildConfig.VERSION_NAME);
            version.setText(s);
        }

        // Get built text view
        TextView built = view.findViewById(R.id.built);

        // Set built date in text view
        if (built != null)
        {
            String d = (String) built.getText();
            DateFormat dateFormat = DateFormat.getDateTimeInstance();
            String s =
                    String.format(d, dateFormat.format(BuildConfig.BUILT));
            built.setText(s);
        }

        // Get copyright text view
        TextView copyright = view.findViewById(R.id.copyright);

        // Set movement method
        if (copyright != null)
            copyright.setMovementMethod(LinkMovementMethod.getInstance());

        // Get licence text view
        TextView licence = view.findViewById(R.id.licence);

        // Set movement method
        if (licence != null)
            licence.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
