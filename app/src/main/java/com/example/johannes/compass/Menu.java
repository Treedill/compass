package com.example.johannes.compass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by johannes on 2018-04-16.
 */

public class Menu extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button btnCompass = findViewById(R.id.buttonCompass);

        Button btnAccelerator = findViewById(R.id.buttonAccelerator);

        btnCompass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentCompass = new Intent(Menu.this, Compass.class);
                startActivity(intentCompass);
            }
        });

        btnAccelerator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentAccelerator = new Intent(Menu.this, Accelerator.class);
                startActivity(intentAccelerator);
            }
        });
    }




}

