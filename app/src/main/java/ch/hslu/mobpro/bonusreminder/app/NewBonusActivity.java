package ch.hslu.mobpro.bonusreminder.app;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Jonas on 07.05.2015.
 */
public class NewBonusActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_new_bonus);


        Button button = (Button) findViewById(R.id.new_bonus_confirm);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText textField = (EditText) findViewById(R.id.new_bonus_textfield);
                String newBonus = textField.getText().toString();

                final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(NewBonusActivity.this);
                final SharedPreferences.Editor editor = prefs.edit();
                Set<String> boni = new HashSet<String>();
                boni = prefs.getStringSet("boni", boni);
                boni.add(newBonus);

                editor.putStringSet("boni", boni);
                editor.apply();

                finish();
            }
        });
    }
}
