package com.example.coursework2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

public class ResultActivity extends AppCompatActivity {

    private TextView res;
    private TextView code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent=getIntent();
        String msg=intent.getStringExtra("info");
        res = (TextView) this.findViewById(R.id.tvRes);
        res.setText(msg);

        final int min = 100000;
        final int max = 999999;
        int r = new Random().nextInt((max - min) + 1) + min;

        String c="NL"+r;
        code = (TextView) this.findViewById(R.id.tvCode);
        code.setText(c);

    }
}