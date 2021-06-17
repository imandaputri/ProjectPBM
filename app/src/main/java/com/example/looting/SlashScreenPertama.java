package com.example.looting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SlashScreenPertama extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slash_screen_pertama);

        button = (Button) findViewById(R.id.btn_next);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               openSlashScreenKedua();
            }
        });
    }

    public void openSlashScreenKedua(){
        Intent intent = new Intent(this, SlashScreenKedua.class);
        startActivity(intent);
    }
}