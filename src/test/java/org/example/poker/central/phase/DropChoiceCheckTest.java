package org.example.poker.central.phase;

import org.example.poker.central.PokerGameSystem;
import org.example.poker.model.Card;
import org.example.poker.service.PokerPlayer;
import org.example.poker.view.PokerPrinter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.*;

/**
 * DropChoiceCheckクラスのテストクラス
 *
 * @author yasu
 * @version 1.0
 */
@ExtendWith(MockitoExtension.class)
class DropChoiceCheckTest {

    @Mock
    private PokerGameSystem pokerGameSystem;
    @Mock
    private PokerPlayer pokerPlayer;
    @Mock
    private PokerPrinter pokerPrinter;

    @BeforeEach
    void setUp() {
        doReturn(pokerPlayer).when(pokerGameSystem).getPokerPlayer();
        doReturn(pokerPrinter).when(pokerGameSystem).getPokerPrinter();
    }

    @DisplayName("advanceGameメソッドはplayer.cardChangeCheckでtrueが返却された場合" +
            "次のフェーズにLastDrawをセットする事")
    @Test
    void advanceGameTestWhenPlayerCardChangeCheckTrue() {
        GamePhase dropChoiceCheck = DropChoiceCheck.getInstance();

        doReturn(true).when(pokerPlayer).cardChangeCheck();

        // 実行
        dropChoiceCheck.advanceGame(pokerGameSystem);

        verify(pokerPrinter, times(1)).printUserCardsSelection(
                ArgumentMatchers.<List<Card>>any(),
                ArgumentMatchers.<Set<Integer>>any());
        verify(pokerPlayer, times(1)).cardChangeCheck();
        // LastDrawがセットされている事のテスト
        verify(pokerGameSystem, times(1)).setGamePhase(
                any(LastDraw.class)
        );
    }

    @DisplayName("advanceGameメソッドはplayer.cardChangeCheckでfalseが返却された場合" +
            "次のフェーズにDropChoiceをセットする事")
    @Test
    void advanceGameTestWhenPlayerCardChangeCheckFalse() {
        GamePhase dropChoiceCheck = DropChoiceCheck.getInstance();

        doReturn(false).when(pokerPlayer).cardChangeCheck();
        // 実行
        dropChoiceCheck.advanceGame(pokerGameSystem);

        verify(pokerPrinter, times(1)).printUserCardsSelection(
                ArgumentMatchers.<List<Card>>any(),
                ArgumentMatchers.<Set<Integer>>any());
        verify(pokerPlayer, times(1)).cardChangeCheck();
        // LastDrawがセットされている事のテスト
        verify(pokerGameSystem, times(1)).setGamePhase(
                any(DropChoice.class)
        );
    }

}