package com.example.newsaplication.UIAplikasi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.newsaplication.Connection.ConnectionClass;
import com.example.newsaplication.R;
import com.google.android.material.textfield.TextInputEditText;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginPage extends AppCompatActivity {

    TextInputEditText usernamelogin,passwordlogin;
    CardView buttonlogin;

    Connection con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        getSupportActionBar().hide();

        usernamelogin = (TextInputEditText) findViewById(R.id.username);
        passwordlogin = (TextInputEditText) findViewById(R.id.password);
        buttonlogin = (CardView) findViewById(R.id.login);

        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new checkLogin().execute("");
            }
        });

    }

    public void todashboard(View view) {
        Intent intent = new Intent(LoginPage.this,DashboardPage.class);
        startActivity(intent);
        finish();
    }

    public class checkLogin extends AsyncTask<String, String, String>{

        String z = null;
        Boolean isSuccess = false;


        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(String s) {

        }

        @Override
        protected String doInBackground(String... strings) {
            String Username = usernamelogin.getText().toString();
            String Password = passwordlogin.getText().toString();

            // Memeriksa apakah nilai inputan kosong
            if (TextUtils.isEmpty(Username)) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        usernamelogin.setError("Masukan Username");
                    }
                });
                return "Username kosong";
            }

            if (TextUtils.isEmpty(Password)) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       passwordlogin.setError("Masukan Password");
                    }
                });
                return "Password kosong";
            }

            ConnectionClass connectionClass = new ConnectionClass();
            Connection con = connectionClass.Conn(ConnectionClass.un, ConnectionClass.pass, ConnectionClass.db, ConnectionClass.ip);
            if (con == null) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(LoginPage.this, "Check Internet Connection", Toast.LENGTH_LONG).show();
                    }
                });
                z = "On Internet Connection";
            } else {
                try {
                        String sql = "SELECT * FROM pengguna WHERE email = '" + usernamelogin.getText() + "' AND password = '" + passwordlogin.getText() + "' ";
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(sql);

                        if (rs.next()) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(LoginPage.this, "Login Success", Toast.LENGTH_LONG).show();
                                }
                            });
                            z = "Success";

                            Intent intent = new Intent(LoginPage.this, DashboardPage.class);
                            intent.putExtra("USERNAME", rs.getString("username"));
                            startActivity(intent);
                            finish();
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(LoginPage.this, "Check email or password", Toast.LENGTH_LONG).show();
                                }
                            });

                            usernamelogin.setText("");
                            passwordlogin.setText("");
                        }
                    } catch(Exception e){
                        isSuccess = false;
                        Log.e("SQL Error : ", e.getMessage());
                    }
                }
                return z;
            }
    }


    public static class AnimasiSplashScreen extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_animasi_splash_screen);
            getSupportActionBar().hide();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(AnimasiSplashScreen.this, Onboarding_Page.class);
                    startActivity(intent);
                    finish();
                }
            },4000);
        }
    }
}