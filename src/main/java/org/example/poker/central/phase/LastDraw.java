package org.example.poker.central.phase;

import org.example.poker.central.PokerGameSystem;
import org.example.poker.model.Card;
import org.example.poker.service.CardDealer;
import org.example.poker.service.PokerHand;
import org.example.poker.service.PokerHandAnalyst;
import org.example.poker.service.PokerPlayer;
import org.example.poker.view.PokerPrinter;

import java.util.List;

/**
 * ポーカーゲームの最終結果の処理を表現したクラス
 *
 * @author yasu
 * @version 1.0
 */
public class LastDraw implements GamePhase{

    private static final GamePhase singleton = new LastDraw();

    private LastDraw(){}

    /**
     * プレイヤーが選んだ捨てるカードに新たにカードをセットし
     * その結果をプレイヤーの結果に反映させ、画面に表示する
     * その後は {@link PokerGameSystem} の {@link GamePhase}
     * に {@link Continue} をセットする
     *
     * @param pokerGameSystem {@link PokerGameSystem} ゲームシステム
     */
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
                newPlayerCards, hand, getPokerHandMessage(hand));

        // コンテニューするかのフェーズに移行
        pokerGameSystem.setGamePhase(Continue.getInstance());
    }

    public static GamePhase getInstance() {
        return singleton;
    }

    private String getPokerHandMessage(PokerHand hand) {
        switch (hand) {
            case NO_HAND:
                return "Fuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuck!!";
            case THREE_CARDS:
            case TWO_PAIR:
            case ONE_PAIR:
                return "やるじゃない！！";
            case FULL_HOUSE:
            case FLASH:
            case STRAIGHT:
                return "これはなかなか！！";
            case FOUR_CARDS:
            case STRAIGHT_FLASH:
            case ROYAL_STRAIGHT_FLASH:
                return "あんたは神ですか!？";
            default:
                return "やったね！！";
        }
    }
}
