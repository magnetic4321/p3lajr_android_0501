package com.edwardsebastianekasaputra.ajr.role.manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.edwardsebastianekasaputra.ajr.LoginActivity;
import com.edwardsebastianekasaputra.ajr.R;
import com.edwardsebastianekasaputra.ajr.auth.UserPreferences;
import com.edwardsebastianekasaputra.ajr.models.User;

import org.json.JSONException;

import java.io.Serializable;

public class ManagerActivity extends AppCompatActivity implements Serializable {

    private User user;
    private UserPreferences userPreferences;

    TextView tvName, tvEmail;
    Button btnLogout, btnLaporanTransaksi,
            btnLaporanPendapatan, btnLaporanTopDriver,
            btnLaporanTopCustomer, btnLaporanPerformaDriver,
            btnLaporanMobil;
    EditText etTahun, etBulan;

    private static String URL_LAPORAN = "http://192.168.1.8:8000/api/transaksis/laporan";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        userPreferences = new UserPreferences(ManagerActivity.this);
        user = userPreferences.getUserLogin();

        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        btnLogout = findViewById(R.id.btnLogout);
        etTahun = findViewById(R.id.etTahun);
        etBulan = findViewById(R.id.etBulan);
        btnLaporanMobil = findViewById(R.id.btnLaporanMobil);
        btnLaporanTransaksi = findViewById(R.id.btnLaporanTransaksi);
        btnLaporanTopDriver = findViewById(R.id.btnLaporanTopDriver);
        btnLaporanTopCustomer = findViewById(R.id.btnLaporanTopCustomer);
        btnLaporanPerformaDriver = findViewById(R.id.btnLaporanPerformaDriver);

        getUser();

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userPreferences.logout();
                Toast.makeText(ManagerActivity.this, "We're waiting for your back", Toast.LENGTH_SHORT);
                checkLogin();
            }
        });

        btnLaporanMobil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tahun = etTahun.getText().toString().trim();
                String bulan = etBulan.getText().toString().trim();
                Uri uri = Uri.parse("http://192.168.1.8:8000/api/transaksis/laporan-mobil/" + tahun + '/' + bulan);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        btnLaporanTransaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tahun = etTahun.getText().toString().trim();
                String bulan = etBulan.getText().toString().trim();
                Uri uri = Uri.parse("http://192.168.1.8:8000/api/transaksis/laporan/" + tahun + '/' + bulan);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        btnLaporanTopDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tahun = etTahun.getText().toString().trim();
                String bulan = etBulan.getText().toString().trim();
                Uri uri = Uri.parse("http://192.168.1.8:8000/api/transaksis/laporan-driver/" + tahun + '/' + bulan);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        btnLaporanTopCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tahun = etTahun.getText().toString().trim();
                String bulan = etBulan.getText().toString().trim();
                Uri uri = Uri.parse("http://192.168.1.3:8000/api/transaksis/laporan-customer/" + tahun + '/' + bulan);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        btnLaporanPerformaDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tahun = etTahun.getText().toString().trim();
                String bulan = etBulan.getText().toString().trim();
                Uri uri = Uri.parse("http://192.168.1.8:8000/api/transaksis/laporan-performa/" + tahun + '/' + bulan);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }

    private void getUser() {
        tvName.setText("NAME : " + user.getNama());
        tvEmail.setText("EMAIL : " + user.getEmail());
    }

    private void checkLogin(){
        /* check if user login */
        if(!userPreferences.checkLogin()){
            startActivity(new Intent(ManagerActivity.this, LoginActivity.class));
        }
    }

//    private void logout() {
//        Intent intent = new Intent(ManagerActivity.this, LoginActivity.class);
//        startActivity(intent);
//
//        finish();
//    }
}