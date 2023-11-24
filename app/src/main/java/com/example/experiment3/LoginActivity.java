package com.example.experiment3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioGroup;


public class LoginActivity extends AppCompatActivity {

    protected EditText mNameOrMail,mPassword;

    protected Button mLogin;

    protected TextView mLoginState;

    protected RadioGroup mRadioGroup;


    private void init()
    {
        mNameOrMail=findViewById(R.id.editViewNameOrMail);
        mPassword=findViewById(R.id.editViewPassword);
        mLogin=findViewById(R.id.buttonLogin);
        mRadioGroup=findViewById(R.id.radioGroup);
        mLoginState=findViewById(R.id.textViewLoginState);
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    void setLoginState(int state)
    {
        if(state==1)
        {
            mLoginState.setHint("登陆成功");
        }
        else if(state==0)
        {
            mLoginState.setHint("登陆失败");
        }
        else {
            mLoginState.setHint("");
        }
    }

    private void login()
    {
        int selectedRadioButtonId = mRadioGroup.getCheckedRadioButtonId();
        String NameOrMail=mNameOrMail.getText().toString();
        String Password=mPassword.getText().toString();
        if (selectedRadioButtonId==R.id.nameButton)
        {
            if(
                    NameOrMail.equals("zhangsan")
                    &&Password.equals("123456")
            )
            {
                setLoginState(1);
            }
            else {
                setLoginState(0);
            }
        }
        else if(selectedRadioButtonId==R.id.emalButton)
        {
            if(
                    NameOrMail.equals("zhangsan@163.com")
                            &&Password.equals("12345678")
            )
            {
                setLoginState(1);
            }
            else {
                setLoginState(0);
            }
        }
        else
        {
            Toast.makeText(this, "您没有选择登录方式", Toast.LENGTH_SHORT).show();
        }
    }



}
