package com.example.looting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Beranda extends AppCompatActivity {
    DataBaseHelper dataBaseHelper;
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beranda);

        dataBaseHelper = new DataBaseHelper(this, "Looting.db", null, 1);

        Boolean checkSession = dataBaseHelper.checkSession("ada");
        if (checkSession == false) {
            Intent masukIntent = new Intent(Beranda.this, Masuk.class);
            startActivity(masukIntent);
            finish();
        }

        //Halaman Tambah Barang
        RelativeLayout addbarang = (RelativeLayout)findViewById(R.id.tambahbarang);
        addbarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Beranda.this, TambahBarang.class);
                startActivity(intent);
            }
        });

        //Halaman Semua Barang
        RelativeLayout temuananda = (RelativeLayout)findViewById(R.id.temuananda);
        temuananda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Beranda.this, SemuaBarang.class);
                startActivity(intent);
            }
        });

        //Halaman Riwayat
        RelativeLayout riwayat = (RelativeLayout)findViewById(R.id.riwayatt);
        riwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Beranda.this, Riwayat.class);
                startActivity(intent);
            }
        });

        //Halaman Notifikasi
        RelativeLayout notifikasi = (RelativeLayout)findViewById(R.id.notifikasi_beranda);
        notifikasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Beranda.this, Notifikasi.class);
                startActivity(intent);
            }
        });

        //Halaman Pengaduan
        RelativeLayout pengaduan = (RelativeLayout)findViewById(R.id.pengaduan);
        pengaduan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pengaduanIntent = new Intent(Beranda.this, Pengaduan.class);
                startActivity(pengaduanIntent);
            }
        });

        //Halaman Tentang Aplikasi
        RelativeLayout lihatlainnya = (RelativeLayout)findViewById(R.id.lihatlainnya);
        lihatlainnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Beranda.this, InformasiApplikasi.class);
                startActivity(intent);
            }
        });
        
        //Initialize And Assign Variable
        bottomNavigationView = findViewById(R.id.bottom_nav);
        
        //Set Beranda Selected
        bottomNavigationView.setSelectedItemId(R.id.menu_home);
        
        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.menu_home:
                        return true;

                    case R.id.menu_cari:
                        startActivity(new Intent(getApplicationContext()
                                ,Pencarian.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.menu_user:
                        startActivity(new Intent(getApplicationContext()
                        ,Profil.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });



    }
}