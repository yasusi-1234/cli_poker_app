package org.example.poker.central.phase;

import org.example.poker.central.PokerGameSystem;
import org.example.poker.model.Card;
import org.example.poker.service.CardDealer;
import org.example.poker.service.PokerHand;
import org.example.poker.service.PokerHandAnalyst;
import org.example.poker.service.PokerPlayer;
import org.example.poker.view.PokerPrinter;

import java.util.List;

public class LastDraw implements GamePhase{

    private static final GamePhase singleton = new LastDraw();

    private LastDraw(){}

    @Override
    public void advanceGame(PokerGameSystem pokerGameSystem) {
        PokerPlayer player = pokerGameSystem.getPokerPlayer();
        CardDealer cardDealer = pokerGameSystem.getCardDealer();
        PokerHandAnalyst pokerHandAnalyst = pokerGameSystem.getPokerHandAnalyst();
        PokerPrinter pokerPrinter = pokerGameSystem.getPokerPrinter();

        // 捨てたカードに新たなカードを挿入した結果
        List<Card> newPlayerCards = cardDealer.distributeCards(
                player.getCards(), player.getDropCards());
        // プレイヤーのカードにセット
        player.setCards(newPlayerCards);
        // プレイヤーのカードの手役抽出
        PokerHand hand = pokerHandAnalyst.analyzePokerHand(newPlayerCards);
        // 上がり役の格納と、スコアとゲーム回数をプラス
        player.addPokerResultMap(hand);
        player.addScore(hand.getScore());
        player.addGameCount();
        // 結果表示
        pokerPrinter.printUserCardsResult(
                newPlayerCards, hand, "やったね！");

        // コンテニューするかのフェーズに移行
        pokerGameSystem.setGamePhase(Continue.getInstance());
    }

    public static GamePhase getInstance() {
        return singleton;
    }
}
