package com.example.user.androidpontoon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

/**
 * Created by User on 21/12/2016.
 */
public class LosePage extends AppCompatActivity{
    Button playButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lose_page);
    }

    public void startNewGame(View view) {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }
}
