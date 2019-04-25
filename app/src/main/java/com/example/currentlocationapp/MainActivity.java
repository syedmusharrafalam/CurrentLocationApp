package com.example.currentlocationapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
  EditText editText1,editText2;
          Button btnText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);

        btnText = findViewById(R.id.button);


        btnText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,LatLngActivity.class                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     ));
            }
        });

    }




}
