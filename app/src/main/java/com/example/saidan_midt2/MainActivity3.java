package com.example.saidan_midt2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        EditText data = (EditText) findViewById(R.id.data);
        Button input = (Button) findViewById(R.id.input);
        Button all_data = (Button) findViewById(R.id.all_data);

        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (name.equals("") || surname.equals("") || id.equals("")) {
                    Toast.makeText(MainActivity3.this,
                            "Fields are empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(name.equals("a") || surname.equals("a") || id.equals("a")) {
                    Toast.makeText(MainActivity3.this,
                            "Fields are deleted", Toast.LENGTH_SHORT).show();
                    return;
                }

                Toasty.error(getBaseContext(), "All record are deleted!", Toast.LENGTH_SHORT, true).show();
            }
        });

    }
}