package com.example.saidan_midt2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    String weatherWebserviceURL = "http://api.openweathermap.org/data/2.5/weather?q=london&appid=542820dd8576a2f2e18ab895a07b8cda&units=metric"+"&appid=542820dd8576a2f2e18ab895a07b8cda&units=metric";;
    ImageView weatherBackground;

    TextView temperature, humidity;

    JSONObject jsonObj;

    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        temperature = (TextView) findViewById(R.id.temperature);
        humidity = (TextView) findViewById(R.id.humidity);
        weatherBackground = (ImageView) findViewById(R.id.weatherbackground);
        Button bttn = (Button) findViewById(R.id.bttnDate);
        Button next = (Button) findViewById(R.id.next);
        Calendar c = Calendar.getInstance();
        DateFormat fmtDate = DateFormat.getDateInstance();
        TextView reservation = (TextView) findViewById(R.id.Reservation);
        TextView view = (TextView) findViewById(R.id.View);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                url = "https://api.openweathermap.org/data/2.5/weather?q="+ spinner.getSelectedItem().toString() +"&appid=a6db16468be1247fe3c436fe88426379&units=metric";
                weather(url);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(android.widget.DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {
                c.set(Calendar.YEAR, year);
                c.set(Calendar.MONTH, monthOfYear);
                c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                reservation.setText("Your reservation is "+
                        fmtDate.format(c.getTime()));
            }
        };

        bttn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity.this, d,
                        c.get(Calendar.YEAR),
                        c.get(Calendar.MONTH),
                        c.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity2.class));
            }
        });

    }

    public void weather(String url)
    {
        JsonObjectRequest jsonObj =
                new JsonObjectRequest(Request.Method.GET,
                        url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("hissah", "Response received");
                        Log.d("hissah",  response.toString());
                        try {
                            JSONObject jsonMain = response.getJSONObject("main");
                            JSONObject jsonSystem = response.getJSONObject("sys");

                            double temp = jsonMain.getDouble("temp");
                            Log.d("hissah-temp", String.valueOf(temp));
                            temperature.setText(String.valueOf(temp));

                            double humid = jsonMain.getDouble("humidity");
                            Log.d("hissah-hum", String.valueOf(humid));
                            humidity.setText("Humidity: " + String.valueOf(humid));

                            /* sub categories as JSON arrays */
                            JSONArray jsonArray = response.getJSONArray("weather");
                            for (int i=0; i<jsonArray.length();i++){
                                Log.d("hissah-array",jsonArray.getString(i));
                                JSONObject oneObject = jsonArray.getJSONObject(i);
                                String weather =
                                        oneObject.getString("main");
                                Log.d("hissah-w",weather);

                                if(weather.equals("humid"))
                                {
                                    Glide.with(MainActivity.this)
                                            .load("https://i.picsum.photos/id/866/536/354.jpg?hmac=tGofDTV7tl2rprappPzKFiZ9vDh5MKj39oa2D--gqhA")
                                            .into(weatherBackground);
                                }else if (weather.equals("Clear"))
                                {
                                    Glide.with(MainActivity.this)
                                            .load("https://images.pexels.com/photos/281260/pexels-photo-281260.jpeg?auto=compress&cs=tinysrgb&dpr=3&h=750&w=1260")
                                            .into(weatherBackground);
                                }else if (weather.equals("Rainy"))
                                {
                                    Glide.with(MainActivity.this)
                                            .load("https://images.pexels.com/photos/125510/pexels-photo-125510.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1")
                                            .into(weatherBackground);
                                }else
                                    Glide.with(MainActivity.this)
                                            .load("https://images.pexels.com/photos/1118873/pexels-photo-1118873.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1")
                                            .into(weatherBackground);
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("Receive Error", e.toString());
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("hissah", "Error retrieving URL");
                    }


                });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObj);

    }
}