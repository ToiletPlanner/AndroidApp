package be.howest.toiletplanner;

/**
 * Created by Dylan on 3/12/2014.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MySimpleAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final ArrayList<HashMap<String, String>> values;

    private final String TAG_Name = Constants.TAG_NAME.toString();
    private final String TAG_LastUsed = Constants.TAG_LASTUSED.toString();
    private final String TAG_State = Constants.TAG_STATE.toString();

    public MySimpleAdapter(Context context, ArrayList values) {
        super(context, R.layout.list_item, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item, parent, false);

        TextView name = (TextView) rowView.findViewById(R.id.name);
        TextView lastaccess = (TextView) rowView.findViewById(R.id.lastAccess);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);

        name.setText(values.get(position).get(TAG_Name));
        lastaccess.setText(values.get(position).get(TAG_LastUsed));


        if (values.get(position).get(TAG_State).equals("free"))
        {
            imageView.setImageResource(R.drawable.ic_green);
        }
        else
        {
            imageView.setImageResource(R.drawable.ic_red);
        }

        return rowView;
    }
}