package justin.travis.devin.finalproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SettingsActivity extends AppCompatActivity {
    String TAG = "Settings Activity";

    final String[] message = new String[1];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final RadioButton radio0 = findViewById(R.id.radio_First);
        final RadioButton radio1 = findViewById(R.id.radio_Second);
        final RadioButton radio2 = findViewById(R.id.radio_Third);

        final EditText custom_message = findViewById(R.id.EditText_Custom);
//        custom_message.setText(message[0]);

        custom_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message[0] = String.valueOf(custom_message.getText());
                radio2.setChecked(true);
            }
        });

//        custom_message.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean b) {
//                if(!b){
//                    message[0] = String.valueOf(custom_message.getText());
//                    Log.d(TAG, "EditText has lost focus");
//                    Log.d(TAG, "Custom message: " + message[0]);
//                }
//            }
//        });

        RadioGroup rg = findViewById(R.id.radioGroup);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_First:
                        message[0] = (String) radio0.getText();
                        Log.d(TAG, "First button selected. Message: " + message[0]);

                        break;
                    case R.id.radio_Second:
                        message[0] = (String) radio1.getText();
                        Log.d(TAG, "Second button selected. Message: " + message[0]);

                        break;
                    case R.id.radio_Third:
                        message[0] = custom_message.getText().toString();
                        Log.d(TAG, "Third button selected. Message: " + message[0]);

                        break;
                }
            }
        });
    }

    @Override
    protected void onDestroy() {

        final RadioButton radio2 = findViewById(R.id.radio_Third);
        final EditText custom_message = findViewById(R.id.EditText_Custom);

        if(radio2.isChecked()) {
            message[0] = String.valueOf(custom_message.getText());
            Log.d(TAG, "radio2 is checked");
            Log.d(TAG, "Custom message: " + message[0]);
        }
        Log.d(TAG, "Message: " + message[0]);

        super.onDestroy();

    }
}
