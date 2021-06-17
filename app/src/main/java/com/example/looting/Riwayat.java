package com.example.looting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Riwayat extends AppCompatActivity {
    private Button kembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.riwayat);

        kembali = (Button) findViewById(R.id.btn_kembali);
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