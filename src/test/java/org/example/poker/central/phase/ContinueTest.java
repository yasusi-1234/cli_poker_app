package org.example.poker.central.phase;

import org.example.poker.central.PokerGameSystem;
import org.example.poker.service.CardDealer;
import org.example.poker.service.PlayerResponse;
import org.example.poker.service.PokerPlayer;
import org.example.poker.view.PokerPrinter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

/**
 * Continueクラスのテストクラス
 *
 * @author yasu
 * @version 1.0
 */
@ExtendWith(MockitoExtension.class)
class ContinueTest {

    @Mock
    private PokerGameSystem pokerGameSystem;
    @Mock
    private PokerPlayer pokerPlayer;
    @Mock
    private PokerPrinter pokerPrinter;
    @Mock
    private CardDealer cardDealer;

    @BeforeEach
    void setUp() {
        doReturn(pokerPlayer).when(pokerGameSystem).getPokerPlayer();
        doReturn(pokerPrinter).when(pokerGameSystem).getPokerPrinter();
        doReturn(cardDealer).when(pokerGameSystem).getCardDealer();
    }

    @DisplayName("advanceGameはpokerPlayerのgameContinueメソッドが" +
            "CONTINUEを返却するとゲームフェーズにFirstDrawをセットする")
    @Test
    void advanceGameWhenPokerPlayerReturnCONTINUE() {
        GamePhase continuePhase = Continue.getInstance();
        // setUp
        doReturn(PlayerResponse.CONTINUE).when(pokerPlayer).gameContinue();
        // 実行
        continuePhase.advanceGame(pokerGameSystem);

        verify(pokerPlayer, times(1)).gameContinue();

        verify(cardDealer, times(1)).cardsShuffle();
        // ゲームフェーズにFirstDrawがセットされている事
        verify(pokerGameSystem, times(1)).setGamePhase(
                any(FirstDraw.class)
        );
    }

    @DisplayName("advanceGameはpokerPlayerのgameContinueメソッドが" +
            "SHOW_STATUSを返却するとゲームフェーズには何もセットしない")
    @Test
    void advanceGameWhenPokerPlayerReturnSHOW_STATUS() {
        GamePhase continuePhase = Continue.getInstance();
        // setUp
        doReturn(PlayerResponse.SHOW_STATUS).when(pokerPlayer).gameContinue();
        // 実行
        continuePhase.advanceGame(pokerGameSystem);

        verify(pokerPlayer, times(1)).gameContinue();
        // ゲームフェーズへのセットの処理がされていない事
        verify(pokerGameSystem, never()).setGamePhase(any());
    }

    @DisplayName("advanceGameはpokerPlayerのgameContinueメソッドが" +
            "GAME_ENDを返却するとゲームフェーズにGameEndをセットする")
    @Test
    void advanceGameWhenPokerPlayerReturnGAME_END() {
        GamePhase continuePhase = Continue.getInstance();
        // setUp
        doReturn(PlayerResponse.GAME_END).when(pokerPlayer).gameContinue();
        // 実行
        continuePhase.advanceGame(pokerGameSystem);

        verify(pokerPlayer, times(1)).gameContinue();
        // ゲームフェーズへのセットの処理がされていない事
        verify(pokerGameSystem, times(1)).setGamePhase(
                any(GameEnd.class));
    }
}