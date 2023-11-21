package com.example.experiment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    protected Button buttonUserLogin,buttonPersonalCenter,buttonLogin,buttonDetails,buttonChangePassword,buttonRegister,buttonQQ;


    protected void initButtons()
    {
        buttonUserLogin =findViewById(R.id.buttonUserLogin);
        buttonPersonalCenter=findViewById(R.id.buttonPersonalCenter);
        buttonLogin=findViewById(R.id.buttonLogin);
        buttonQQ=findViewById(R.id.buttonQQ);
        buttonDetails=findViewById(R.id.buttonDetails);
        buttonRegister=findViewById(R.id.buttonRegister);
        buttonChangePassword=findViewById(R.id.buttonChangePassword);

        // 设置按钮点击事件
        buttonUserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 创建并启动跳转到 EmailActivity 的 Intent
                Intent emailIntent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(emailIntent);
                Log.d("MainActivity","LoginActivity");
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
