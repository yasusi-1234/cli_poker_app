package org.example.poker.central.phase;

import org.example.poker.central.PokerGameSystem;
import org.example.poker.model.Card;

import java.util.List;

/**
 * ポーカーゲームのフェーズの初回の処理を表現したクラス
 *
 * @author yasu
 * @version 1.0
 */
public class FirstDraw implements GamePhase{

    private static final GamePhase singleton = new FirstDraw();

    private FirstDraw() {

    }

    /**
     * 初回のカードを配りプレイヤーにカードを格納する
     * その後{@link DropChoice} フェーズを {@link PokerGameSystem} にセットする
     * @param pokerGameSystem {@link PokerGameSystem} ゲームシステム
     */
    @Override
    public void advanceGame(PokerGameSystem pokerGameSystem) {
        // 初回のカードを格納
        List<Card> playerCards = pokerGameSystem.getCardDealer().firstDistributeCards();
        // プレイヤーにカードを格納
        pokerGameSystem.getPokerPlayer().setCards(playerCards);
        // カード取捨選択フェーズ移行
        pokerGameSystem.setGamePhase(DropChoice.getInstance());
    }

    public static GamePhase getInstance() {
        return singleton;
    }
}
