package org.example.poker.central.phase;

import org.example.poker.central.PokerGameSystem;
import org.example.poker.service.PokerPlayer;
import org.example.poker.view.PokerPrinter;

public class DropChoice implements GamePhase{

    private static final GamePhase singleton = new DropChoice();

    private DropChoice() {

    }

    @Override
    public void advanceGame(PokerGameSystem pokerGameSystem) {
        PokerPlayer player = pokerGameSystem.getPokerPlayer();
        PokerPrinter pokerPrinter = pokerGameSystem.getPokerPrinter();

        // プレイヤーの配られたカード情報を表示
        pokerPrinter.printUserCards(player.getCards());
        // プレイヤーに捨てるカードを選択してもらう
        player.cardSelection();
        // プレイヤーのカード取捨選択の確認処理フェーズに移行
        pokerGameSystem.setGamePhase(DropChoiceCheck.getInstance());
    }

    public static GamePhase getInstance() {
        return singleton;
    }
}
