package com.example.looting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Masuk extends AppCompatActivity{
    DataBaseHelper dataBaseHelper;
    Button masuk;
    EditText email, katasandi;
    TextView daftardarimasuk;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.masuk);

        dataBaseHelper = new DataBaseHelper(this, "Looting.db", null, 1);

        email = (EditText)findViewById(R.id.txt_email);
        katasandi = (EditText)findViewById(R.id.txt_katasandi);
        masuk = (Button)findViewById(R.id.btn_masuk);
        daftardarimasuk = (TextView) findViewById(R.id.txt_daftar_darimasuk);

        //register
        daftardarimasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent daftarIntent = new Intent(Masuk.this, Daftar.class);
                startActivity(daftarIntent);
                finish();
            }
        });

        //login
        masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strEmail = email.getText().toString();
                String strKatasandi = katasandi.getText().toString();

                Boolean login = dataBaseHelper.checkLogin(strEmail, strKatasandi);

                if (login == true) {
                    Boolean updateSession = dataBaseHelper.upgradeSession("ada", 1);
                    if (updateSession == true) {
                        Toast.makeText(getApplicationContext(), "Berhasil Masuk", Toast.LENGTH_SHORT).show();
                        Intent berandaIntent = new Intent(Masuk.this, Beranda.class);
                        startActivity(berandaIntent);
                        finish();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Masuk Gagal", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}