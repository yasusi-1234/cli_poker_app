package org.example.poker.central.phase;

import org.example.poker.central.PokerGameSystem;
import org.example.poker.service.PokerPlayer;
import org.example.poker.view.PokerPrinter;

/**
 * ポーカーゲームのプレイヤーが選んだ捨てるカードか間違いないかを確認する処理を表現したクラス
 *
 * @author yasu
 * @version 1.0
 */
public class DropChoiceCheck implements GamePhase {

    private static final GamePhase singleton = new DropChoiceCheck();

    private DropChoiceCheck() {
    }

    /**
     * プレイヤーの取捨状況を表示し間違いないかを確認する
     * {@link PokerGameSystem} の {@link GamePhase} は
     * プレイヤーがtrueを返却すると {@link LastDraw} をセットし
     * プレイヤーがfalseを返却すると {@link DropChoice} をセットする
     * @param pokerGameSystem {@link PokerGameSystem} ゲームシステム
     */
    @Override
    public void advanceGame(PokerGameSystem pokerGameSystem) {
        PokerPlayer player = pokerGameSystem.getPokerPlayer();
        PokerPrinter pokerPrinter = pokerGameSystem.getPokerPrinter();
        // プレイヤーのカードの取捨状況の表示
        pokerPrinter.printUserCardsSelection(
                        player.getCards(),
                        player.getDropCards());
        // プレイヤーの取捨状況の確認
        boolean matchChoice = player.cardChangeCheck();

        if (matchChoice) {
            // ゲーム結果まで進行させる
            pokerGameSystem.setGamePhase(LastDraw.getInstance());
        } else {
            // カードの取捨選択処理に戻る
            pokerGameSystem.setGamePhase(DropChoice.getInstance());
        }
    }

    public static GamePhase getInstance() {
        return singleton;
    }
}
