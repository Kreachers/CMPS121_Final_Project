package justin.travis.devin.finalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final RadioButton radio0 = findViewById(R.id.radio_First);
        final RadioButton radio1 = findViewById(R.id.radio_Second);
        RadioButton radio2 = findViewById(R.id.radio_Third);
        final EditText custom_message = findViewById(R.id.EditText_Custom);

//        custom_message.setOnClickListener(new EditText.On;

        final String[] message = {(String) radio0.getText()};

        RadioGroup rg = findViewById(R.id.radioGroup);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_First:
                        message[0] = (String) radio0.getText();
                        Log.d("Settings Activity", "First button selected. Message: " + message[0]);

                        break;
                    case R.id.radio_Second:
                        message[0] = (String) radio1.getText();
                        Log.d("Settings Activity", "Second button selected. Message: " + message[0]);

                        break;
                    case R.id.radio_Third:
                        message[0] = custom_message.getText().toString();
                        Log.d("Settings Activity", "Third button selected. Message: " + message[0]);

                        break;
                }
                Log.d("Settings Activity", "Final message: " + message[0]);
            }
        });
    }
}
