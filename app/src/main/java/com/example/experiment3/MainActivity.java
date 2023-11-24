package com.example.experiment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.SafeBrowsingResponse;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    protected Button buttonUserLogin,buttonSignUp,buttonSanXingDui,buttonConsult;


    protected void initButtons()
    {
        buttonUserLogin =findViewById(R.id.buttonUserLogin);
        buttonSignUp=findViewById(R.id.buttonSignUp);
        buttonSanXingDui=findViewById(R.id.buttonSanxingdui);

        buttonConsult=findViewById(R.id.buttonConsult);

        buttonConsult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentConsult = new Intent(MainActivity.this,ConsultActivity.class);
                startActivity(intentConsult);
                Log.d("MainActivity","ConsultActivity");
            }
        });

        buttonUserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentLogin = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intentLogin);
                Log.d("MainActivity","LoginActivity");
            }
        });
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSignUp = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intentSignUp);
                Log.d("MainActivity","SignUpActivity");
            }
        });
        buttonSanXingDui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSanxingdui = new Intent(MainActivity.this, SanxingduiActivity.class);
                startActivity(intentSanxingdui);
                Log.d("MainActivity","SanxingduiActivity");
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initButtons();
    }
}
