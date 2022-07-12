package org.example.poker.central.phase;

import org.example.poker.central.PokerGameSystem;
import org.example.poker.model.Card;
import org.example.poker.service.CardDealer;
import org.example.poker.service.PokerPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

/**
 * FirstDrawクラスのテスト
 *
 * @author yasu
 * @version 1.0
 */
@ExtendWith(MockitoExtension.class)
class FirstDrawTest {

    @Mock
    private PokerGameSystem pokerGameSystem;
    @Mock
    private PokerPlayer pokerPlayer;
    @Mock
    private CardDealer cardDealer;

    @BeforeEach
    void setUp() {
        doReturn(cardDealer).when(pokerGameSystem).getCardDealer();
        doReturn(pokerPlayer).when(pokerGameSystem).getPokerPlayer();
    }


    @DisplayName("advanceGameメソッドが正しく処理されているかのテスト")
    @Test
    void advanceGameTest() {
        GamePhase gamePhase = FirstDraw.getInstance();
        gamePhase.advanceGame(pokerGameSystem);

        verify(cardDealer, times(1)).firstDistributeCards();
        verify(pokerPlayer, times(1))
                .setCards(ArgumentMatchers.<List<Card>>any());
        // pokerGameSystemにPhaseのDropChoiceがセットされているか
        verify(pokerGameSystem, times(1)).setGamePhase(any(DropChoice.class));
    }

}