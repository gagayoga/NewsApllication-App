package com.example.newsaplication.UIAplikasi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.newsaplication.R;

public class Onboarding_Page extends AppCompatActivity {

    CardView loginn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding_page);
        getSupportActionBar().hide();

        loginn = (CardView) findViewById(R.id.loginn);
        loginn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Onboarding_Page.this, LoginPage.class);
                startActivity(intent);
                finish();
            }
        });
    }
}