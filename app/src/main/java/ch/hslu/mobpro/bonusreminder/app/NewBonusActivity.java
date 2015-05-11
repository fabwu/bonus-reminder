package ch.hslu.mobpro.bonusreminder.app;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;

import java.util.HashSet;
import java.util.Set;

public class NewBonusActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_new_bonus);
    }

    public void saveBonus(View view) {
        EditText textField = (EditText) findViewById(R.id.new_bonus_textfield);
        String newBonus = textField.getText().toString();

        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(NewBonusActivity.this);
        final SharedPreferences.Editor editor = prefs.edit();
        Set<String> boni = new HashSet<String>();
        boni = prefs.getStringSet(MainActivity.BONUS_KEY, boni);
        boni.add(newBonus);

        editor.putStringSet(MainActivity.BONUS_KEY, boni);
        editor.apply();

        finish();
    }
}
