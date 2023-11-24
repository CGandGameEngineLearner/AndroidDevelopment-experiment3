package com.example.experiment3;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.tabs.TabLayout;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

public class ConsultActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ScrollView scrollView;

    protected Button buttonNotice,buttonWarning,buttonDirect;
    protected TextView textViewContent;

    private void init()
    {
        buttonNotice=findViewById(R.id.buttonNotice);
        buttonWarning=findViewById(R.id.buttonWarning);
        buttonDirect=findViewById(R.id.buttonDirect);
        textViewContent=findViewById(R.id.textViewContent);

        buttonNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewContent.setText(R.string.notice);
            }
        });

        buttonWarning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewContent.setText(R.string.warning);
            }
        });

        buttonDirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewContent.setText(R.string.direct);
            }
        });
    }






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult);
        init();


    }
}