package com.example.ex1;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button myButton;
    EditText inPoid;
    String[] items= {"KG to PND","PND to KG"};
    TextView result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.Poids);

        inPoid = findViewById(R.id.inPoids);
        myButton = findViewById(R.id.convert);
        Spinner spin = (Spinner) findViewById(R.id.mySpinner);
        spin.setOnItemSelectedListener(this);
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
    }

    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        myButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Double poids = new Double(inPoid.getText().toString());

                Double res;

                if(position == 0)
                    res = poids * 2.2;
                else
                    res = (poids / 2.2);

                result.setText(res.toString());




            }
        }

        );
    }

    public void onNothingSelected(AdapterView<?> parent) {

    }





    }
