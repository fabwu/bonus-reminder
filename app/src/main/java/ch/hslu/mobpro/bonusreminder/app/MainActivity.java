package ch.hslu.mobpro.bonusreminder.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class MainActivity extends Activity {
    public static final String BONUS_KEY = "boni";

    private SharedPreferences prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fillBonusList();
    }

    @Override
    protected void onResume() {
        super.onResume();

        fillBonusList();
    }

    public void startNewBonusActivity(View view) {
        Intent intent = new Intent(MainActivity.this, NewBonusActivity.class);
        startActivity(intent);
    }


    private void fillBonusList() {
        List<String> bonusList = new ArrayList<String>(getBoni());
        ListAdapter listAdapter = getListAdapter(bonusList);
        setListView(listAdapter);
    }

    private Set<String> getBoni() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Set<String> boni = new HashSet<String>();
        boni = prefs.getStringSet(BONUS_KEY, boni);
        return boni;
    }

    private ListAdapter getListAdapter(List<String> list) {
        return new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_expandable_list_item_1, list) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textView = (TextView) view.findViewById(android.R.id.text1);
                textView.setTextColor(Color.BLACK);
                return view;
            }
        };
    }

    private void setListView(ListAdapter listAdapter) {
        ListView listView = (ListView) findViewById(R.id.boni_list);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedBonus = (String) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(MainActivity.this, EditBonusActivity.class);
                intent.putExtra("selected_bonus", selectedBonus);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.start_scan) {
            startWifiScan();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void startWifiScan() {
        WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        wifiManager.startScan();
    }

}
