package org.example.poker.central;

import org.example.poker.model.Card;
import org.example.poker.service.*;
import org.example.poker.view.PokerPrinter;

import java.util.List;
import java.util.Set;

public class PokerGameSystem {

    private final PokerPlayer pokerPlayer;

    private final PokerPrinter pokerPrinter;

    private final PokerHandAnalyst pokerHandAnalyst;

    private final CardDealer cardDealer;

    public PokerGameSystem() {
        this.pokerPlayer = new PokerPlayer();
        this.pokerPrinter = new PokerPrinter();
        this.pokerHandAnalyst = new PokerHandAnalyst();
        this.cardDealer = new CardDealer();
    }

    public void gamePlay() {

        boolean gameContinue = true;

        while(gameContinue){

            // 初回のカードを格納
            List<Card> playerCards = cardDealer.firstDistributeCards();
            // プレイヤーにカードを格納
            pokerPlayer.setCards(playerCards);

            Set<Integer> dropCardIndexes;
            while(true){
                // プレイヤーの配られたカード情報を表示
                pokerPrinter.printUserCards(pokerPlayer.getCards());
                // プレイヤーに捨てるカードを選択してもらう
                dropCardIndexes = pokerPlayer.cardSelection();
                // プレイヤーのカードの取捨状況の表示
                pokerPrinter.printUserCardsSelection(pokerPlayer.getCards(), dropCardIndexes);
                // プレイヤーの取捨状況の確認
                boolean matchChoice = pokerPlayer.cardChangeCheck();

                if (matchChoice){
                    break;
                }

            }

            // 捨てたカードに新たなカードを挿入した結果
            List<Card> newPlayerCards = cardDealer.distributeCards(pokerPlayer.getCards(), dropCardIndexes);
            // プレイヤーのカードにセット
            pokerPlayer.setCards(newPlayerCards);
            // プレイヤーのカードの手役抽出
            PokerHand hand = pokerHandAnalyst.analyzePokerHand(newPlayerCards);
            // 上がり役の格納と、スコアをプラス
            pokerPlayer.addPokerResultMap(hand);
            pokerPlayer.addScore(hand.getScore());
            // 結果表示
            pokerPrinter.printUserCardsResult(newPlayerCards, "やったね！");

            while(true){
                // ゲームを続けるか等をプレイヤーに尋ねる
                PlayerResponse response = pokerPlayer.gameContinue();

                if(response == PlayerResponse.CONTINUE){
                    cardDealer.cardsShuffle();
                    break;
                }else if (response == PlayerResponse.GAME_END){
                    gameContinue = false;
                    break;
                } else if (response == PlayerResponse.SHOW_STATUS) {
                    pokerPrinter.printUserScore(
                            pokerPlayer.getPokerResultMap(), pokerPlayer.getScore());
                }

            }
        }

        System.out.println("ゲームを終了します!!");

    }
}
