package com.example.user.androidpontoon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.google.gson.Gson;

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
        Dealer dealer = new Dealer("Dealer", new Hand(), new Deck());
        Gambler gambler = new Gambler("Player", new Hand());
        gambler.setFunds(200);
        dealer.setFunds(200);

        dealer.fillDeck();
        dealer.shuffleCards();

        Gson gson = new Gson();
        String dealerJson = gson.toJson(dealer);
        String gamblerJson = gson.toJson(gambler);

        Intent intent = new Intent(this, Setup.class);
        intent.putExtra("dealer", dealerJson);
        intent.putExtra("gambler", gamblerJson);
        startActivity(intent);
    }

}
