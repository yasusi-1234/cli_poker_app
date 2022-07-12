package org.example.poker.central.phase;

import org.example.poker.central.PokerGameSystem;
import org.example.poker.model.Card;
import org.example.poker.service.PokerPlayer;
import org.example.poker.view.PokerPrinter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

/**
 * DropChoiceクラスのテストクラス
 *
 * @author yasu
 * @version 1.0
 */
@ExtendWith(MockitoExtension.class)
class DropChoiceTest {

    @Mock
    private PokerGameSystem pokerGameSystem;
    @Mock
    private PokerPlayer pokerPlayer;
    @Mock
    private PokerPrinter pokerPrinter;

    @DisplayName("advanceGameメソッドが正しく処理されている事のテスト")
    @Test
    void advanceGameTest() {
        GamePhase phase = DropChoice.getInstance();

        doReturn(pokerPlayer).when(pokerGameSystem).getPokerPlayer();
        doReturn(pokerPrinter).when(pokerGameSystem).getPokerPrinter();

        phase.advanceGame(pokerGameSystem);

        verify(pokerPrinter, times(1)).printUserCards(ArgumentMatchers.<List<Card>>any());
        verify(pokerPlayer, times(1)).cardSelection();
        // pokerGameSystemのgamePhaseにDropChoiceCheckがセットされている事のテスト
        verify(pokerGameSystem, times(1)).setGamePhase(any(DropChoiceCheck.class));
    }


}