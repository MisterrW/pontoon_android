package com.example.user.androidpontoon;
import java.util.*;

public class WinCheck {
    private GameState gameState;
    private ArrayList<CardPlayer> allPlayers;
    private String outcome;
    private SinglePlayerGameManager singlePlayerGameManager;
    private Dealer dealer;
    private Gambler player;

    public WinCheck(SinglePlayerGameManager gameManager, GameState gameState, ArrayList<CardPlayer> allPlayers){
        this.gameState = gameState;
        this.allPlayers = allPlayers;
        this.outcome = new String("");
        this.singlePlayerGameManager = gameManager;
        this.player = (Gambler)allPlayers.get(0);
        this.dealer = (Dealer)allPlayers.get(1);
    }

    public void showAllHands() {
        for (CardPlayer player : allPlayers) {
            showHand(player);
        }
    }

    public void showHand(CardPlayer player) {
        gameState.showAnyHand(player);
    }

    public int calcScore(CardPlayer player) {
        int score = 0;
        for (Card card : player.showHand()) {
            CardValue value = card.getValue();
            int cardWorth;
            switch (value) {
                case TWO: cardWorth = 2;
                    break;
                case THREE: cardWorth = 3;
                    break;
                case FOUR: cardWorth = 4;
                    break;
                case FIVE: cardWorth = 5;
                    break;
                case SIX: cardWorth = 6;
                    break;
                case SEVEN: cardWorth = 7;
                    break;
                case EIGHT: cardWorth = 8;
                    break;
                case NINE: cardWorth = 9;
                    break;
                case TEN: cardWorth = 10;
                    break;
                case JACK: cardWorth = 10;
                    break;
                case QUEEN: cardWorth = 10;
                    break;
                case KING: cardWorth = 10;
                    break;
                case ACE: cardWorth = 11;
                    break;
                default: cardWorth = 0;
            }
            score += cardWorth;
        }
        player.setScore(score);

        for(Card card : player.showHand()) {
            if(card.getValue() == CardValue.ACE) {
                if(player.getScore() > 21) {
                    gameState.setToastText("Aces low!");
                    player.setScore(player.getScore()-10);
                }
            }
        }

        if (player.getScore() == 21 && player.showHand().size() == 2) {
            gameState.setToastText(player.getName() + " has a Pontoon! " + player.showHand().get(0).getName() + " and " + player.showHand().get(1).getName() + ".");
            player.setSpecialScore("pontoon");
        } else if (player.getScore() <= 21 && player.showHand().size() >= 5) {
            gameState.setToastText(player.getName() + " has a 5 card trick!");
            player.setSpecialScore("5 card trick");
        }

        return player.getScore();
    }

    public void bustCheck(CardPlayer player){
        if(calcScore(player) > 21) {
            showHand(player);
            player.setSpecialScore("bust");
            this.allPlayers.remove(player);
            winCheck();
        }
    }

    public void fakeBustCheck(CardPlayer player){
        if(calcScore(player) > 21) {
            showHand(player);
            player.setSpecialScore("bust");
            this.allPlayers.remove(player);
            fakeWinCheck();
        }
    }

    public void winCheck() {
        for (CardPlayer player : allPlayers) {
            calcScore(player);
        }
        winCheckSpecialScore();
        gameState.setMainText(this.outcome);
        endGame();
    }

    public String fakeWinCheck() {
        for (CardPlayer player : allPlayers) {
            calcScore(player);
        }

        winCheckSpecialScore();
        return this.outcome;
//        gameState.setMainText(this.outcome);
//        endGame();
    }

    public void endGame(){
        gameState.endGame(player, dealer);
        gameState.showDealerHand(dealer);
        gameState.showHand(player);
    }

    public void winCheckSpecialScore() {
        if (this.allPlayers.size() == 2) {
            if (this.allPlayers.get(0).getSpecialScore().equals("pontoon") && !this.allPlayers.get(1).getSpecialScore().equals("pontoon"))
            {
                this.outcome = String.format(this.allPlayers.get(0).getName() + " wins with pontoon!");
                this.allPlayers.get(0).setWinner(true);
                return;
            }
            else if (this.allPlayers.get(1).getSpecialScore().equals("pontoon") && !this.allPlayers.get(0).getSpecialScore().equals("pontoon"))
            {
                this.outcome = String.format(this.allPlayers.get(1).getName() + " wins with pontoon!");
                this.allPlayers.get(1).setWinner(true);
                return;
            }
            else if (this.allPlayers.get(0).getSpecialScore().equals("pontoon") && this.allPlayers.get(1).getSpecialScore().equals("pontoon"))
            {
                this.outcome = String.format("Wow, two pontoons! Draw!");
                return;
            }
            else if (this.allPlayers.get(0).getSpecialScore().equals("5 card trick") && !this.allPlayers.get(1).getSpecialScore().equals("5 card trick"))
            {
                this.outcome = String.format(this.allPlayers.get(0).getName() + " wins with 5 card trick!");
                this.allPlayers.get(0).setWinner(true);
                return;
            }
            else if (this.allPlayers.get(1).getSpecialScore().equals("5 card trick") && !this.allPlayers.get(0).getSpecialScore().equals("5 card trick"))
            {
                this.outcome = String.format(this.allPlayers.get(1).getName() + " wins with 5 card trick!");
                this.allPlayers.get(1).setWinner(true);
                return;
            }
            else if (this.allPlayers.get(0).getSpecialScore().equals("5 card trick") && this.allPlayers.get(1).getSpecialScore().equals("5 card trick"))
            {
                this.outcome = String.format("Wow, two 5 card tricks! Draw!");
                return;
            }
            else
            {
                winCheckNormalScore();
            }
        }
        winCheckNormalScore();
    }

    public void winCheckNormalScore() {
        if (this.allPlayers.size() == 2) {
            if (this.allPlayers.get(0).getScore() > this.allPlayers.get(1).getScore())
            {
                this.outcome = String.format(this.allPlayers.get(0).getName() + " wins with " + this.allPlayers.get(0).getScore() + "!");
                this.allPlayers.get(0).setWinner(true);
                return;
            }
            else if (this.allPlayers.get(0).getScore() < this.allPlayers.get(1).getScore())
            {
                this.outcome = String.format(this.allPlayers.get(1).getName() + " wins with " + this.allPlayers.get(1).getScore() + "!");
                this.allPlayers.get(1).setWinner(true);
                return;
            }
            else
            {
                this.outcome = String.format("It's a draw!");
                return;
            }
        }
        else if (this.allPlayers.size() == 1)
        {
            this.outcome = String.format(this.allPlayers.get(0).getName() + " wins with " + this.allPlayers.get(0).getScore() + "!");
            this.allPlayers.get(0).setWinner(true);
            return;
        }
        else if (this.allPlayers.size() == 0)
        {
            this.outcome = String.format("Everyone's bust! Draw!");
            return;
        }
        return;
    }

}