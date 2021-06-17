package com.example.looting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Notifikasi extends AppCompatActivity {

    private Button kembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifikasi);

        kembali = (Button) findViewById(R.id.btn_kembali3);
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBeranda();
            }
        });
    }
    public void openBeranda(){
        Intent intent = new Intent(this, Beranda.class);
        startActivity(intent);
    }
}