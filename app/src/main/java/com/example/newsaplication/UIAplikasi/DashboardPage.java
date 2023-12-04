package com.example.newsaplication.UIAplikasi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newsaplication.ConnectAPI.AdapterNewsLokal;
import com.example.newsaplication.ConnectAPI.ModelNewsLokal;
import com.example.newsaplication.Connection.ConnectionClass;
import com.example.newsaplication.R;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DashboardPage extends AppCompatActivity {
    Connection con;
    RecyclerView rv;
    AdapterNewsLokal adapter;
    ArrayList<ModelNewsLokal> news;
    CardView football,basketball;
    TextView username_user;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_page);
        getSupportActionBar().hide();

        rv = findViewById(R.id.recyle_view);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setHasFixedSize(true);

        news = new ArrayList<>();
        getData();
        adapter = new AdapterNewsLokal(this,news);
        rv.setAdapter(adapter);

        username_user = (TextView) findViewById(R.id.username_user);
        Intent intent = getIntent();
        String username = intent.getStringExtra("USERNAME");
        username_user.setText(username);

        football = (CardView) findViewById(R.id.football);
        football.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardPage.this,FootballNews.class);
                startActivity(intent);
            }
        });

        basketball =(CardView) findViewById(R.id.basketnews);
        basketball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(DashboardPage.this, BasketNews.class);
                startActivity(intent1);
            }
        });
    }
    private void getData() {
        // Buat koneksi ke database
        ConnectionClass connectionClass = new ConnectionClass();
        Connection con = connectionClass.Conn(ConnectionClass.un, ConnectionClass.pass, ConnectionClass.db, ConnectionClass.ip);
        if (con == null) {
            Toast.makeText(this, "Gagal terhubung ke database", Toast.LENGTH_SHORT).show();
        }

        // Query untuk mengambil data dari database
        String query = "SELECT * FROM NewsSports";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                ModelNewsLokal News = new ModelNewsLokal();
                News.setIdberita(rs.getInt("idberita"));
                News.setJudulberita(rs.getString("judulberita"));
                News.setDeskripsiberita(rs.getString("deskripsiberita"));
                News.setIsiberita(rs.getString("isiberita"));
                News.setPenulis(rs.getString("penulis"));
                news.add(News);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(this, "Gagal mengambil data dari database", Toast.LENGTH_SHORT).show();
        }
    }
}