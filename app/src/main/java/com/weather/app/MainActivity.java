package com.weather.app;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Button show_weather_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        show_weather_button = findViewById(R.id.show_weather_button);

        View.OnClickListener show_weather_onclick_listener = new View.OnClickListener() {
            public void onClick(View v) {
                if(isNetworkConnected()) {
                    EditText latitude_input = findViewById(R.id.latitude_input);
                    EditText longitude_input = findViewById(R.id.longitude_input);
                    String latitude_input_value = latitude_input.getText().toString();
                    String longitude_input_value = longitude_input.getText().toString();

                    if (latitude_input_value.length() != 0 && longitude_input_value.length() != 0) {
                        Intent i = new Intent(getApplicationContext(), weather.class);
                        i.putExtra("latitude", latitude_input_value);
                        i.putExtra("longitude", longitude_input_value);
                        startActivity(i);
                    }else{
                        Snackbar snackbar = Snackbar.make(findViewById(R.id.main_layout),
                                "Enter latitude and longitude",
                                Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                }else{
                    Snackbar snackbar = Snackbar.make(findViewById(R.id.main_layout),
                            "No internet connection",
                            Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }
        };
        show_weather_button.setOnClickListener(show_weather_onclick_listener);
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
}
