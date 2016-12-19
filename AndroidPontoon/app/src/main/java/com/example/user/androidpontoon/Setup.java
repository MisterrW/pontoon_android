package com.example.user.androidpontoon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class Setup extends AppCompatActivity {

    TextView mainText;
    TextView hand;
    EditText enterName;
    Button goButton;
    Button twistButton;
    Button stickButton;
    Button restartButton;
    GameState gameState;
    TextView dealerHand;

    ArrayList<ImageView> playerHand;
    ImageView playerHand1;
    ImageView playerHand2;
    ImageView playerHand3;
    ImageView playerHand4;
    ImageView playerHand5;

    ImageView testCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        twistButton = (Button)findViewById(R.id.twist_button);
        stickButton = (Button)findViewById(R.id.stick_button);
        restartButton = (Button)findViewById(R.id.restart_button);
        restartButton.setVisibility(View.INVISIBLE);
        gameState = new GameState(Setup.this);
        String text = gameState.getMainText();
        mainText = (TextView)findViewById(R.id.main_text);
//        hand = (TextView)findViewById(R.id.hand);
//        hand.setVisibility(View.GONE);
        dealerHand = (TextView)findViewById(R.id.dealer_hand);
//        enterName = (EditText)findViewById(R.id.enter_name);
        mainText.setText(text);
//        enterName.setVisibility(View.GONE);
        dealerHand.setVisibility(View.GONE);

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

        gameState.singleplayerSetup();

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
    }

    public void getPlayerChoice() {
        twistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    gameState.setPlayerChoice("twist");
            }
        });
        stickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameState.setPlayerChoice("stick");
            }
        });
    }

    public void showToast(String toToast) {
        Toast.makeText(Setup.this, toToast, Toast.LENGTH_SHORT).show();
    }

    public void setPlayerHandImages(ArrayList<Card> hand){
        for (int i=0; i<hand.size(); i++){
            playerHand.get(i).setImageResource(getCardResID(hand.get(i)));
        }
    }

    public void setDealerHand(String text){
        dealerHand.setText(text);
    }

    public void endGameScreen(){
        twistButton.setVisibility(View.GONE);
        stickButton.setVisibility(View.GONE);
//        enterName.setVisibility(View.GONE);
        dealerHand.setVisibility(View.VISIBLE);
        restartButton.setVisibility(View.VISIBLE);
    }
    public void setMainText(String text){
        mainText.setText(text);
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