package com.example.user.androidpontoon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Setup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
    }

    public static void setup() {
        GameState gameState = new GameState();
        gameState.singleplayerSetup();
    }
}
