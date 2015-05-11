package ch.hslu.mobpro.bonusreminder.app;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Jonas on 07.05.2015.
 */
public class EditBonusActivity extends Activity {
    String bonusName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_edit_bonus);

        bonusName = getIntent().getStringExtra("selected_bonus");
        TextView bonusTitle = (TextView) findViewById(R.id.selected_bonus_name);
        bonusTitle.setText(bonusName);

        Button button = (Button) findViewById(R.id.add_access_point);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditBonusActivity.this, NewAccessPointActivity.class);
                intent.putExtra("selected_bonus", bonusName);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        Set<String> accessPoints = getAccessPoints();

        List<String> list = new ArrayList<String>(accessPoints);
        ListAdapter adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_expandable_list_item_1, list) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textView = (TextView) view.findViewById(android.R.id.text1);
                textView.setTextColor(Color.BLACK);
                return view;
            }
        };

        ListView listView = (ListView) findViewById(R.id.access_point_list);
        listView.setAdapter(adapter);
    }

    private Set<String> getAccessPoints() {
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editor = prefs.edit();
        Set<String> accessPoints = new HashSet<String>();
        String accessPoint = prefs.getString(bonusName, "");
        accessPoints.add(accessPoint);
        return accessPoints;
    }
}
