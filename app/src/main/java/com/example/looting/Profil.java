package com.example.looting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Profil extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    private TextView nama, email, nohp;
    private Button keluar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profil);

        nama = (TextView)findViewById(R.id.text_nama);
        email = (TextView)findViewById(R.id.text_email);
        nohp = (TextView)findViewById(R.id.text_nomorhp);

        String strNama = getIntent().getStringExtra("keyname");
        String strEmail = getIntent().getStringExtra("keyemail");
        String strNohp = getIntent().getStringExtra("keynohp");

        nama.setText(strNama);
        email.setText(strEmail);
        nohp.setText(strNohp);

        //Keluar
        keluar = (Button) findViewById(R.id.btn_logout);
        keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMasuk();
            }
            public void openMasuk(){
                Intent intent = new Intent(Profil.this, Masuk.class);
                startActivity(intent);
            }
        });



        //Initialize And Assign Variable
        bottomNavigationView = findViewById(R.id.bottom_nav);

        //Set Beranda Selected
        bottomNavigationView.setSelectedItemId(R.id.menu_user);

        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.menu_home:
                        startActivity(new Intent(getApplicationContext()
                                ,Beranda.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.menu_cari:
                        startActivity(new Intent(getApplicationContext()
                                ,Pencarian.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.menu_user:
                        return true;
                }
                return false;
            }
        });
    }
}