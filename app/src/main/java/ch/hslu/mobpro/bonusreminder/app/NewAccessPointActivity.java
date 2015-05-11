package ch.hslu.mobpro.bonusreminder.app;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;

public class NewAccessPointActivity extends Activity {

    private String bonusName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_new_access_point);

        bonusName = getIntent().getStringExtra("selected_bonus");
    }

    public void saveAccessPoint(View view) {
        EditText textField = (EditText) findViewById(R.id.new_access_point_textfield);
        String newAccessPoint = textField.getText().toString();

        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(NewAccessPointActivity.this);
        final SharedPreferences.Editor editor = prefs.edit();
        editor.putString(bonusName, newAccessPoint);
        editor.apply();

        finish();
    }


}
