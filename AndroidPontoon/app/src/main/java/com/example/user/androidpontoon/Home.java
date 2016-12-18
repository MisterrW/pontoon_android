package com.example.user.androidpontoon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by User on 18/12/2016.
 */
public class Home extends AppCompatActivity {
    Button playButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void playGame(View view) {
            Intent intent = new Intent(this, Setup.class);
            startActivity(intent);
    }

}
