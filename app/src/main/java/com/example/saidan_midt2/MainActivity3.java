package com.example.saidan_midt2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class MainActivity3 extends AppCompatActivity {
    String id_val,name_val;
    int num_val;
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

        all_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id_val = id.getText().toString();
                name_val = name.getText().toString();
                num_val = Integer.parseInt(num.getText().toString());
                db.addData(id_val,name_val,num_val);

                Cursor cur = myData.ViewEmployee();
                StringBuffer buffer = new StringBuffer();
                while(cur.moveToNext())
                {
                    buffer.append("id: " + cur.getString(0)+ "\n");
                    buffer.append("Name: " + cur.getString(1)+ "\n");
                    buffer.append("Salary: " + cur.getString(2)+ "\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity3.this);
                builder.setCancelable(true);  // a dialog box that can be closed
                builder.setTitle("All Employees");
                builder.setMessage(buffer.toString());
                builder.show();
                Toast.makeText(MainActivity3.this, "Successful View", Toast.LENGTH_LONG).show(); }});
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myData.DeleteEmployee(id_val);
                Toast.makeText(MainActivity3.this, "Successful Delete", Toast.LENGTH_LONG).show();
            }
        });
    }
