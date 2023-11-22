package com.example.experiment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    protected Button buttonUserLogin,buttonSignUp,buttonLogin,buttonDetails,buttonChangePassword,buttonRegister,buttonQQ;


    protected void initButtons()
    {
        buttonUserLogin =findViewById(R.id.buttonUserLogin);
        buttonSignUp=findViewById(R.id.buttonSignUp);
        buttonLogin=findViewById(R.id.buttonLogin);
        buttonQQ=findViewById(R.id.buttonQQ);
        buttonDetails=findViewById(R.id.buttonDetails);

        buttonChangePassword=findViewById(R.id.buttonChangePassword);


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
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initButtons();
    }
}
