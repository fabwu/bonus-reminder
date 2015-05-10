package ch.hslu.mobpro.bonusreminder.app;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Jonas on 07.05.2015.
 */
public class NewAccessPointActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_new_access_point);

        final String bonusName = getIntent().getStringExtra("selected_bonus");

        Button button = (Button) findViewById(R.id.new_access_point_confirm);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText textField = (EditText) findViewById(R.id.new_access_point_textfield);
                String newAccessPoint = textField.getText().toString();

                final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(NewAccessPointActivity.this);
                final SharedPreferences.Editor editor = prefs.edit();
                editor.putString(bonusName, newAccessPoint);
                editor.apply();

                finish();
            }
        });

    }


}
