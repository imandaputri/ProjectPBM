package com.example.looting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Pengaduan extends AppCompatActivity {
    private Button kirim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pengaduan);

        kirim = (Button) findViewById(R.id.btn_kirim);
        kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBeranda();
            }
        });
    }
    public void openBeranda(){
        Toast.makeText(getApplicationContext(), "Berhasil dikirim", Toast.LENGTH_SHORT).show();
        Intent berandaIntent = new Intent(this, Beranda.class);
        startActivity(berandaIntent);
    }
}