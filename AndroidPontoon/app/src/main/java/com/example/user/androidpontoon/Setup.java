package com.example.user.androidpontoon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class Setup extends AppCompatActivity {
    int stake = 0;
    TextView mainText;
    TextView hand;
    EditText enterName;
    Button goButton;
    Button twistButton;
    Button stickButton;
    LinearLayout betView;
    LinearLayout playView;
    SeekBar betAmountSeek;
    Button betButton;
    TextView betAmountInfo;

    GameState gameState;
    TextView dealerHand;
    TextView funds;
    String dealerHandText;

    ArrayList<ImageView> playerHand;
    ImageView playerHand1;
    ImageView playerHand2;
    ImageView playerHand3;
    ImageView playerHand4;
    ImageView playerHand5;
    ImageView holeCard;
    Gambler gambler;
    Dealer dealer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        twistButton = (Button)findViewById(R.id.twist_button);
        stickButton = (Button)findViewById(R.id.stick_button);

        gameState = new GameState(Setup.this);
        String text = gameState.getMainText();
        mainText = (TextView)findViewById(R.id.main_text);
        dealerHand = (TextView)findViewById(R.id.dealer_hand);
        mainText.setText(text);
        dealerHand.setVisibility(View.GONE);

        betView = (LinearLayout)findViewById(R.id.bet_view);
        playView = (LinearLayout)findViewById(R.id.play_view);
        playView.setVisibility(View.GONE);

        betAmountSeek = (SeekBar)findViewById(R.id.bet_amount_seek);
        betButton = (Button)findViewById(R.id.bet_button);
        betAmountInfo = (TextView)findViewById(R.id.bet_amount_info);

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
        holeCard = (ImageView)findViewById(R.id.hole_card);

        Intent intent = getIntent();
        Gson gson = new Gson();

        String dealerJson = intent.getStringExtra("dealer");
        String gamblerJson = intent.getStringExtra("gambler");

        dealer = gson.fromJson(dealerJson, Dealer.class);
        gambler = gson.fromJson(gamblerJson, Gambler.class);
        gameState.singleplayerContinue(gambler, dealer);
    }

    public void getPlayerChoice() {
        if (stake==0) {
            String playerFunds = String.format("" + gambler.getFunds());
            funds = (TextView)findViewById(R.id.funds);
            mainText.setVisibility(View.INVISIBLE);
            funds.setText(playerFunds);
            betAmountInfo.setText(String.format("" + (betAmountSeek.getProgress()+5)));
//            betAmountSeek.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    betAmountInfo.setText(String.format("" + (betAmountSeek.getProgress() + 5)));
//                    updateBetAmountInfo();
//                }
//            });

            betAmountSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress,
                                              boolean fromUser) {
                    betAmountInfo.setText(String.valueOf(progress+5));
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }
            });




            playView.setVisibility(View.GONE);
            betView.setVisibility(View.VISIBLE);
            betButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    stake = (betAmountSeek.getProgress()+5);
                    getPlayerChoice();
                }
            });
        }
        else {
            mainText.setText(gameState.getMainText());
            betView.setVisibility(View.GONE);
            playView.setVisibility(View.VISIBLE);
//            twistButton.setVisibility(View.VISIBLE);
//            stickButton.setVisibility(View.VISIBLE);

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
    }

    public void showToast(String toToast) {
        Toast.makeText(Setup.this, toToast, Toast.LENGTH_SHORT).show();
    }

    public void setPlayerHandImages(ArrayList<Card> hand){
        for (int i=0; i<hand.size(); i++){
            playerHand.get(i).setImageResource(getCardResID(hand.get(i)));
        }
    }

    public void setHoleCardImage(Card card){
        holeCard.setImageResource(getCardResID(card));
    }

    public void setDealerHand(String text){
        dealerHandText = text;
        dealerHand.setText(text);
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

    public void endGameScreen(CardPlayer gambler, CardPlayer dealer){
        mainText.setVisibility(View.INVISIBLE);
        this.gambler = (Gambler)gambler;
        this.dealer = (Dealer)dealer;
        outcomePage(null);
    }

    public void outcomePage(View view) {
        Gson gson = new Gson();
        String dealerEndJson = gson.toJson(this.dealer);
        String gamblerEndJson = gson.toJson(this.gambler);
        String stakeJson = gson.toJson(this.stake);
        Intent intent2 = new Intent(Setup.this, OutcomePage.class);
        intent2.putExtra("dealer", dealerEndJson);
        intent2.putExtra("gambler", gamblerEndJson);
        intent2.putExtra("stake", stakeJson);
        startActivity(intent2);
    }
}