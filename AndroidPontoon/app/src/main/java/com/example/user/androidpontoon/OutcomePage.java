package com.example.user.androidpontoon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by User on 19/12/2016.
 */
public class OutcomePage extends AppCompatActivity {
    Gambler gambler;
    Dealer dealer;
    Button restartButton;

    TextView playerScore;
    TextView dealerScore;

    ArrayList<ImageView> playerHand;
    ImageView playerHand1;
    ImageView playerHand2;
    ImageView playerHand3;
    ImageView playerHand4;
    ImageView playerHand5;

    ArrayList<ImageView> dealerHand;
    ImageView dealerHand1;
    ImageView dealerHand2;
    ImageView dealerHand3;
    ImageView dealerHand4;
    ImageView dealerHand5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outcome_page);

        Intent intent = getIntent();
        Gson gson = new Gson();

        String dealerJson = intent.getStringExtra("dealer");
        String gamblerJson = intent.getStringExtra("gambler");

        Dealer dealer = gson.fromJson(dealerJson, Dealer.class);
        Gambler gambler = gson.fromJson(gamblerJson, Gambler.class);

        String pScore = Integer.toString(gambler.getScore());
        String dScore = Integer.toString(dealer.getScore());

        playerScore = (TextView)findViewById(R.id.player_score);
        dealerScore = (TextView)findViewById(R.id.dealer_score);

        playerScore.setText(pScore);
        dealerScore.setText(dScore);

        playerHand = new ArrayList<ImageView>();
        playerHand1 = (ImageView)findViewById(R.id.player_hand_1);
        playerHand2 = (ImageView)findViewById(R.id.player_hand_2);
        playerHand3 = (ImageView)findViewById(R.id.player_hand_3);
        playerHand4 = (ImageView)findViewById(R.id.player_hand_4);
        playerHand5 = (ImageView)findViewById(R.id.player_hand_5);
        playerHand.add(playerHand1);
        playerHand.add(playerHand2);
        playerHand.add(playerHand3);
        playerHand.add(playerHand4);
        playerHand.add(playerHand5);

        dealerHand = new ArrayList<ImageView>();
        dealerHand1 = (ImageView)findViewById(R.id.dealer_hand_1);
        dealerHand2 = (ImageView)findViewById(R.id.dealer_hand_2);
        dealerHand3 = (ImageView)findViewById(R.id.dealer_hand_3);
        dealerHand4 = (ImageView)findViewById(R.id.dealer_hand_4);
        dealerHand5 = (ImageView)findViewById(R.id.dealer_hand_5);
        dealerHand.add(dealerHand1);
        dealerHand.add(dealerHand2);
        dealerHand.add(dealerHand3);
        dealerHand.add(dealerHand4);
        dealerHand.add(dealerHand5);

        setHandImages(gambler, dealer);

        restartButton = (Button)findViewById(R.id.restart_button);

        if(gambler.getWinner() == true) {
            restartButton.setBackgroundResource(R.drawable.winbutton_01);
        } else {
            restartButton.setBackgroundResource(R.drawable.losebutton_01);
        }
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OutcomePage.this, Home.class);
                finish();
                startActivity(intent);
            }
        });
    }

    public void setHandImages(Gambler gambler, Dealer dealer){

        ArrayList<Card> dealersHand = dealer.showHand();
        ArrayList<Card> gamblersHand = gambler.showHand();

        for (int i=0; i<gamblersHand.size(); i++){
            playerHand.get(i).setImageResource(getCardResID(gamblersHand.get(i)));
        }

        for (int x=0; x<dealersHand.size(); x++){
            dealerHand.get(x).setImageResource(getCardResID(dealersHand.get(x)));
        }
    }

    public int getCardResID (Card card) {

        HashMap<CardValue, String> valueLookUp = new HashMap<CardValue, String>();
        valueLookUp.put(CardValue.TWO, "two");
        valueLookUp.put(CardValue.THREE, "three");
        valueLookUp.put(CardValue.FOUR, "four");
        valueLookUp.put(CardValue.FIVE, "five");
        valueLookUp.put(CardValue.SIX, "six");
        valueLookUp.put(CardValue.SEVEN, "seven");
        valueLookUp.put(CardValue.EIGHT, "eight");
        valueLookUp.put(CardValue.NINE, "nine");
        valueLookUp.put(CardValue.TEN, "ten");
        valueLookUp.put(CardValue.JACK, "jack");
        valueLookUp.put(CardValue.QUEEN, "queen");
        valueLookUp.put(CardValue.KING, "king");
        valueLookUp.put(CardValue.ACE, "ace");

        HashMap<CardSuit, String> suitLookUp = new HashMap<CardSuit, String>();
        suitLookUp.put(CardSuit.HEARTS, "heart");
        suitLookUp.put(CardSuit.DIAMONDS, "dia");
        suitLookUp.put(CardSuit.CLUBS, "club");
        suitLookUp.put(CardSuit.SPADES, "spade");

        String cardIDString = String.format(valueLookUp.get(card.getValue()) + suitLookUp.get(card.getSuit()) + "_01");

        int cardID = getResources().getIdentifier(cardIDString , "drawable", getPackageName());
        return cardID;
    }
}
