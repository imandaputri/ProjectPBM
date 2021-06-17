package com.example.looting;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Daftar extends AppCompatActivity {
    DataBaseHelper dataBaseHelper;
    Button daftar;
    EditText nama, email, nohp, katasandi, ulangsandi;
    TextView masukdaridaftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daftar);

        dataBaseHelper = new DataBaseHelper(this, "Looting.db", null, 1);

        nama = (EditText) findViewById(R.id.txt_nama);
        email = (EditText) findViewById(R.id.txt_email_daftar);
        nohp = (EditText) findViewById(R.id.txt_nohp);
        katasandi = (EditText) findViewById(R.id.txt_katasandi_daftar);
        ulangsandi = (EditText) findViewById(R.id.txt_ulangsandi);
        daftar = (Button) findViewById(R.id.btn_daftar);
        masukdaridaftar = (TextView) findViewById(R.id.txt_masuk_daridaftar);

        //login
        masukdaridaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent masukIntent = new Intent(Daftar.this, Masuk.class);
                startActivity(masukIntent);
                finish();
            }
        });

        //register
        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strNama = nama.getText().toString();
                String strEmail = email.getText().toString();
                String strNohp = nohp.getText().toString();
                String strKatasandi = katasandi.getText().toString();
                String strUlangsandi = ulangsandi.getText().toString();

                if (strKatasandi.equals(strUlangsandi)) {
                    Boolean daftar = dataBaseHelper.insertUser(strEmail, strKatasandi);
                    if (daftar == true) {
                        Toast.makeText(getApplicationContext(), "Daftar Berhasil", Toast.LENGTH_SHORT).show();
                        Intent berandaIntent = new Intent(Daftar.this, Beranda.class);

                        startActivity(berandaIntent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Daftar Gagal", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Password Tidak Cocok", Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(Daftar.this, Profil.class);
                intent.putExtra("keyname", strNama);
                intent.putExtra("keyemail", strEmail);
                intent.putExtra("keynohp", strNohp);
                startActivity(intent);

            }
        });
    }
}