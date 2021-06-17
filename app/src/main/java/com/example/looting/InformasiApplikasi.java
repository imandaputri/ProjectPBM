package com.example.looting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InformasiApplikasi extends AppCompatActivity {
    private Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.informasi_applikasi);

        ok = (Button) findViewById(R.id.btn_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBeranda();
            }
        });
    }
    public void openBeranda(){
        Intent berandaIntent = new Intent(this, Beranda.class);
        startActivity(berandaIntent);
    }
}