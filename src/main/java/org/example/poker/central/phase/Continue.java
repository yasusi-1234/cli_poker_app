package org.example.poker.central.phase;

import org.example.poker.central.PokerGameSystem;
import org.example.poker.service.CardDealer;
import org.example.poker.service.PlayerResponse;
import org.example.poker.service.PokerPlayer;
import org.example.poker.view.PokerPrinter;

/**
 * ポーカーゲームのゲームを続けるかやめるかステータスを確認するかを表現したクラス
 *
 * @author yasu
 * @version 1.0
 */
public class Continue implements GamePhase{

    private static final GamePhase singleton = new Continue();

    private Continue(){}

    /**
     * ゲームを続けるかをプレイヤーに確認し
     * {@link PlayerResponse} の
     * CONTINUEが返却された場合は {@link PokerGameSystem} の {@link GamePhase}
     * に {@link FirstDraw} をセット
     * SHOE_STATUSが返却された場合は プレイヤーにステータスを表示し
     * GAME_ENDが返却された場合は {@link PokerGameSystem} の {@link GamePhase}
     * に {@link GameEnd} をセットする
     *
     * @param pokerGameSystem {@link PokerGameSystem} ゲームシステム
     */
    @Override
    public void advanceGame(PokerGameSystem pokerGameSystem) {
        PokerPlayer pokerPlayer = pokerGameSystem.getPokerPlayer();
        PokerPrinter pokerPrinter = pokerGameSystem.getPokerPrinter();
        CardDealer cardDealer = pokerGameSystem.getCardDealer();
        // ゲームを続けるか等をプレイヤーに尋ねる
        PlayerResponse response = pokerPlayer.gameContinue();

        switch (response){
            case CONTINUE:
                cardDealer.cardsShuffle();
                // ゲーム初回フェーズへ移行
                pokerGameSystem.setGamePhase(FirstDraw.getInstance());
                break;
            case SHOW_STATUS:
                pokerPrinter.printUserScore(pokerPlayer.getPokerResultMap(),
                                pokerPlayer.getScore(),
                                pokerPlayer.getGameCount());
                break;
            case GAME_END:
                pokerGameSystem.setGamePhase(GameEnd.getInstance());
                break;
        }

    }

    public static GamePhase getInstance() {
        return singleton;
    }
}
