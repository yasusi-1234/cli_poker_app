package org.example.poker.central;

import org.example.poker.central.phase.FirstDraw;
import org.example.poker.central.phase.GameEnd;
import org.example.poker.central.phase.GamePhase;
import org.example.poker.service.*;
import org.example.poker.view.PokerPrinter;


/**
 * ポーカーゲームの進行を管理するクラス
 *
 * @author yasu
 * @version 1.0
 */
public class PokerGameSystem {

    /** {@link PokerPlayer} ポーカーのプレイヤー */
    private final PokerPlayer pokerPlayer;

    /** {@link PokerPrinter} ポーカーの画面表示 */
    private final PokerPrinter pokerPrinter;

    /** {@link PokerHandAnalyst} ポーカーの手役解析をするオブジェクト */
    private final PokerHandAnalyst pokerHandAnalyst;

    /** {@link CardDealer} ポーカーで使うカードを管理するオブジェクト */
    private final CardDealer cardDealer;

    /** {@link GamePhase} ゲームフェーズを現すオブジェクト*/
    private GamePhase gamePhase;

    /** コンストラクタ */
    public PokerGameSystem() {
        this.pokerPlayer = new PokerPlayer();
        this.pokerPrinter = new PokerPrinter();
        this.pokerHandAnalyst = new PokerHandAnalyst();
        this.cardDealer = new CardDealer();
        // ゲームフェーズのセット
        this.setGamePhase(FirstDraw.getInstance());
    }

    /** ゲームプレイ */
    public void gamePlay() {

        while(gamePhase.getClass() != GameEnd.class){
            gamePhase.advanceGame(this);
        }

        System.out.println("プレイして頂きありがとうございます！！");
    }

    public PokerPrinter getPokerPrinter() {
        return this.pokerPrinter;
    }

    public PokerPlayer getPokerPlayer() {
        return pokerPlayer;
    }

    public PokerHandAnalyst getPokerHandAnalyst() {
        return pokerHandAnalyst;
    }

    public CardDealer getCardDealer() {
        return cardDealer;
    }

    public void setGamePhase(GamePhase gamePhase) {
        this.gamePhase = gamePhase;
    }
}
