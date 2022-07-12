package org.example.poker.central.phase;

import org.example.poker.central.PokerGameSystem;
import org.example.poker.service.CardDealer;
import org.example.poker.service.PokerHand;
import org.example.poker.service.PokerHandAnalyst;
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
 * LastDrawクラスのテストクラス
 *
 * @author yasu
 * @version 1.0
 */
@ExtendWith(MockitoExtension.class)
class LastDrawTest {

    @Mock
    private PokerGameSystem pokerGameSystem;
    @Mock
    private PokerPlayer pokerPlayer;
    @Mock
    private PokerPrinter pokerPrinter;
    @Mock
    private PokerHandAnalyst pokerHandAnalyst;
    @Mock
    private CardDealer cardDealer;


    @BeforeEach
    void setUp() {
        doReturn(pokerPlayer).when(pokerGameSystem).getPokerPlayer();
        doReturn(pokerPrinter).when(pokerGameSystem).getPokerPrinter();
        doReturn(pokerHandAnalyst).when(pokerGameSystem).getPokerHandAnalyst();
        doReturn(cardDealer).when(pokerGameSystem).getCardDealer();
    }

    @DisplayName("advanceGameメソッドが正しく処理される事")
    @Test
    void advanceGameTest() {
        GamePhase lastDraw = LastDraw.getInstance();

        doReturn(PokerHand.ONE_PAIR).when(pokerHandAnalyst).analyzePokerHand(any());
        // 実行
        lastDraw.advanceGame(pokerGameSystem);

        verify(cardDealer, times(1)).distributeCards(
                any(),
                any()
        );
        verify(pokerPlayer, times(1)).setCards(any());
        verify(pokerHandAnalyst, times(1)).analyzePokerHand(any());
        verify(pokerPlayer, times(1)).addGameCount();
        verify(pokerPlayer, times(1)).addScore(anyInt());
        verify(pokerPlayer, times(1)).addPokerResultMap(any());
        verify(pokerPrinter, times(1)).printUserCardsResult(
                any(), any(), anyString()
        );
        // Continueフェーズがセットされいる事
        verify(pokerGameSystem, times(1)).setGamePhase(
                any(Continue.class)
        );
    }
}