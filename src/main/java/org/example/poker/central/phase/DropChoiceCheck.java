package org.example.poker.central.phase;

import org.example.poker.central.PokerGameSystem;
import org.example.poker.service.PokerPlayer;
import org.example.poker.view.PokerPrinter;

public class DropChoiceCheck implements GamePhase {

    private static final GamePhase singleton = new DropChoiceCheck();

    private DropChoiceCheck() {
    }

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
