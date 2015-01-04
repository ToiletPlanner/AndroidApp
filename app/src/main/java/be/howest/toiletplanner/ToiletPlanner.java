package be.howest.toiletplanner;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ToiletPlanner extends ListActivity implements Observer{

    private ServiceHandler s;

    private ProgressDialog pDialog;
    // URL to get contacts JSON
    private final String url = "http://toiletplanner-calie.rhcloud.com/api/allroomstatus";

    // JSON Node names
    private final String TAG_Name = Constants.TAG_NAME.toString();
    private final String TAG_LastUsed = Constants.TAG_LASTUSED.toString();
    private final String TAG_State = Constants.TAG_STATE.toString();

    // toilets JSONArray
    JSONArray toilets = null;

    // Hashmap for ListView
    ArrayList<HashMap<String, String>> toiletList;

    Handler h = new Handler();
    int delay = 2500; //milliseconds


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toilet_planner);

        toiletList = new ArrayList<HashMap<String, String>>();

        h.postDelayed(new Runnable(){
            public void run(){
                s = new ServiceHandler(ToiletPlanner.this,url);
                /*
                toiletList.clear();
                new GetToilets().execute();
                */
                h.postDelayed(this, delay);
            }
        }, delay);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toilet_planner, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void getData() throws JSONException {
        toiletList.clear();

        JSONArray jsonArray = s.getJsonOb();
        for (int n = 0 ; n < jsonArray.length(); n++) {

            JSONObject jsonObj = jsonArray.getJSONObject(n);

            String id = jsonObj.getString(TAG_Name);
            String state;
            if (jsonObj.getString(TAG_State).equals("0")) {
                state = "occupied";
            } else {
                state = "free";
            }

            String lastAccessed = jsonObj.getString(TAG_LastUsed);

            HashMap<String, String> toilet = new HashMap<String, String>();

            toilet.put(TAG_Name, id);
            toilet.put(TAG_LastUsed, lastAccessed);
            toilet.put(TAG_State, state);
            toiletList.add(toilet);
        }

        MySimpleAdapter adapter = new MySimpleAdapter(ToiletPlanner.this,toiletList);
        setListAdapter(adapter);
    }
}
