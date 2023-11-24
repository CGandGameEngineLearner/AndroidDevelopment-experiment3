package com.example.experiment3;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class SignUpActivity extends AppCompatActivity {
    protected EditText mDobEditText;
    protected EditText mEditTextUsername,mEditTextAccount,mEditTextPassword;
    protected CheckBox[] mCheckBoxesHobbies=new CheckBox[4];
    protected Button mButtonSignUp;
    protected RadioGroup mRadioGroupSex;

    private void init()
    {
        mDobEditText = findViewById(R.id.dobEditText);
        mButtonSignUp=findViewById(R.id.buttonRegister);
        mEditTextUsername=findViewById(R.id.editTextUsername);
        mEditTextAccount=findViewById(R.id.editTextAccount);
        mEditTextPassword=findViewById(R.id.editTextPassword);
        mCheckBoxesHobbies[0]=findViewById(R.id.cb_music);
        mCheckBoxesHobbies[1]=findViewById(R.id.cb_chess);
        mCheckBoxesHobbies[2]=findViewById(R.id.cb_calligraphy);
        mCheckBoxesHobbies[3]=findViewById(R.id.cb_painting);
        mRadioGroupSex=findViewById(R.id.radioGroupSex);
        // 设置点击事件，当用户点击文本框时显示日期选择对话框
        mDobEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

        mButtonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });
    }
    private void signUp()
    {
        StringBuilder text=new StringBuilder();
        text.append(mEditTextUsername.getText().toString()+"-");
        int SexSelectID=mRadioGroupSex.getCheckedRadioButtonId();
        Button SexButton=findViewById(SexSelectID);
        text.append(SexButton.getText().toString()+"-");
        for(int i=0;i<mCheckBoxesHobbies.length;i++)
        {
            if(mCheckBoxesHobbies[i].isChecked())
            {
                text.append(mCheckBoxesHobbies[i].getText().toString()+"-");
            }
        }
        text.append(mDobEditText.getText().toString());
        Log.d("SignUpActivity",text.toString());
        Toast.makeText(this, text.toString(), Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        init();
    }

    private void showDatePickerDialog() {
        // 获取当前日期
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // 创建日期选择对话框
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
                        // 在这里处理选择的日期
                        String selectedDate = selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDay;
                        mDobEditText.setText(selectedDate);
                    }
                },
                year,
                month,
                day
        );

        // 显示日期选择对话框
        datePickerDialog.show();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Log.d("LoginActivity","Back");

        mEditTextUsername.setText("");
        mEditTextPassword.setText("");
        mEditTextAccount.setText("");
        mDobEditText.setText("请选择日期");
        mRadioGroupSex.clearCheck();

        for(CheckBox hobbie:mCheckBoxesHobbies)
        {
            hobbie.setChecked(false);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setIcon(R.mipmap.exclamation);

        builder.setTitle("温馨提示");


        builder.setMessage("你确定要退出当前界面吗？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        finish();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });


        AlertDialog alertDialog =builder.create();
        alertDialog.show();
    }
}
