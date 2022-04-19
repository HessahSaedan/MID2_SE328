package com.example.saidan_midt2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class MainActivity2 extends AppCompatActivity {
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        EditText name = (EditText) findViewById(R.id.name);
        EditText id = (EditText) findViewById(R.id.id);
        EditText surname = (EditText) findViewById(R.id.surname);
        TextView empty = (TextView) findViewById(R.id.empty);

        Button insert = (Button) findViewById(R.id.insert);
        Button nextt = (Button) findViewById(R.id.nextt);
        Button back = (Button) findViewById(R.id.back);

        myDB = new DatabaseHelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = Name.getText().toString();
                surname = surname.getText().toString();
                id = nationalid.getText().toString();


                if (name.equals("") || surname.equals("") || id.equals("")) {
                    Toast.makeText(MainActivity2.this,
                            "Fields are empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!myDB.addData(name, surname, id))
                    Toast.makeText(MainActivity2.this,
                            "Insertion Failed", Toast.LENGTH_SHORT).show();
                else {
                    Toast.makeText(MainActivity2.this,
                            "Insertion Success", Toast.LENGTH_SHORT).show();
                }

                Toasty.success(getBaseContext(), "Success.", Toast.LENGTH_SHORT, true).show();

            }

        });

        nextt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this, MainActivity3.class));
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this, MainActivity.class));
            }
        });

    }
}