package com.example.looting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SlashScreenKetiga extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slash_screen_ketiga);

        button = (Button) findViewById(R.id.btn_mulai);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMasuk();
            }
        });
    }
    public void openMasuk(){
        Intent intent = new Intent(this, Masuk.class);
        startActivity(intent);
    }
}