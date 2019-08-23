import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;Package com.ngonimahachi.convertit;



public class RawTextReader
{
    /* ********************************************************************
     * Read raw text file resource...
     *
     * source: http://stackoverflow.com/questions/4087674/
     * android-read-text-raw-resource-file
     */

    public static String read(Context context, int resId)
    {
        InputStream stream = context.getResources().openRawResource(resId);
        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader buff = new BufferedReader(reader);

        String line;
        StringBuilder text = new StringBuilder();

        try
        {
            while ((line = buff.readLine()) != null)
                text.append(line).append("\n");
        }
        catch (Exception e)
        {
            return "";
        }

        return text.toString();
    }
}
