package com.example.user.androidpontoon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

//        goButton = (Button)findViewById(R.id.go_button);
        twistButton = (Button)findViewById(R.id.twist_button);
        stickButton = (Button)findViewById(R.id.stick_button);
        restartButton = (Button)findViewById(R.id.restart_button);

        gameState = new GameState(Setup.this);
        String text = gameState.getMainText();
        mainText = (TextView)findViewById(R.id.main_text);
        hand = (TextView)findViewById(R.id.hand);
        dealerHand = (TextView)findViewById(R.id.dealer_hand);
        enterName = (EditText)findViewById(R.id.enter_name);
        mainText.setText(text);

        gameState.singleplayerSetup();

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
//        playerHand.setText(text);
    }

//    public String getUserInput() {
//        userInput = "";
//        Boolean clicked = goButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mainText.setText(gameState.getMainText());
//                userInput = enterName.getText().toString();
//            }
//        });
//        return userInput;
//    }

    public void getPlayerChoice() {
//        goButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(enterName.getText().toString().toLowerCase().equals("twist")){
//                    gameState.setPlayerChoice("twist");
//                }
//                else if(enterName.getText().toString().toLowerCase().equals("stick")){
//                    gameState.setPlayerChoice("stick");
//                }
//            }
//        });
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

    public void setPlayerHand(String text){
       hand.setText(text);
    }

    public void setDealerHand(String text){
        dealerHand.setText(text);
    }

    public void endGameScreen(){
        twistButton.setVisibility(View.GONE);
        stickButton.setVisibility(View.GONE);
        enterName.setVisibility(View.GONE);
    }
    public void setMainText(String text){
        mainText.setText(text);
    }

}