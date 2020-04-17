package com.ext.teamformation.Activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.ext.teamformation.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StartUpActivity extends AppCompatActivity {

    @BindView(R.id.lnLogin)
    LinearLayout lnLogin;
    @BindView(R.id.lnSignUp)
    LinearLayout lnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up);
        ButterKnife.bind(this);

        this.setListeners();
    }


    private void setListeners() {
        lnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StartUpActivity.this, LoginActivity.class);
                startActivity(i);

            }
        });

        lnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StartUpActivity.this, RegisterActivity.class);
                startActivity(i);

            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
