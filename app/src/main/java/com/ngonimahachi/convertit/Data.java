import android.annotation.SuppressLint;
import android.os.AsyncTask;

import org.xml.sax.Parser;

import java.util.List;
import java.util.Map;
// Data class
public class Data
{
    private static Data instance;

    private Map<String, Double> map;
    private List<Integer> list;

    private TaskCallbacks callbacks;
    private boolean parsing;

    // Constructor
    private Data()
    {
    }

    // Get instance
    public static Data getInstance(TaskCallbacks callbacks)
    {
        if (instance == null)
            instance = new Data();

        instance.callbacks = callbacks;
        return instance;
    }

    // Get list
    public List<Integer> getList()
    {
        return list;
    }

    // Set list
    public void setList(List<Integer> list)
    {
        this.list = list;
    }

    // Get map
    public Map<String, Double> getMap()
    {
        return map;
    }

    // Set map
    public void setMap(Map<String, Double> map)
    {
        this.map = map;
    }

    // Start parse task
    protected void startParseTask(String url)
    {
        ParseTask parseTask = new ParseTask();
        parseTask.execute(url);
    }

    // TaskCallbacks interface
    interface TaskCallbacks
    {
        void onProgressUpdate(String... date);

        void onPostExecute(Map<String, Double> map);
    }

    // ParseTask class
    @SuppressLint("StaticFieldLeak")
    protected class ParseTask
            extends AsyncTask<String, String, Map<String, Double>>
    {
        String latest;

        // The system calls this to perform work in a worker thread
        // and delivers it the parameters given to AsyncTask.execute()
        @Override
        protected Map<String, Double> doInBackground(String... urls)
        {
            // Get a parser
            Parser parser = new Parser();

            // Start the parser and report progress with the date
            if (parser.startParser(urls[0]))
                publishProgress(parser.getDate());

            // Return the map
            return parser.getMap();
        }

        // On progress update
        @Override
        protected void onProgressUpdate(String... date)
        {
            if (callbacks != null)
                callbacks.onProgressUpdate(date);
        }

        // The system calls this to perform work in the UI thread and
        // delivers the result from doInBackground()
        @Override
        protected void onPostExecute(Map<String, Double> map)
        {
            if (callbacks != null)
                callbacks.onPostExecute(map);
        }
    }
}
