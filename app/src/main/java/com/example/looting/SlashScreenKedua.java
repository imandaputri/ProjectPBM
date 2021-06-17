package com.example.looting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SlashScreenKedua extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slash_screen_kedua);

        button = (Button) findViewById(R.id.btn_next2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSlashScreenKetiga();
            }
        });
    }

    public void openSlashScreenKetiga(){
        Intent intent = new Intent(this, SlashScreenKetiga.class);
        startActivity(intent);
    }
}