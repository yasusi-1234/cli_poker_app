package org.example.poker.service;

import org.example.poker.model.Card;
import org.example.poker.model.CardType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.example.poker.service.PokerHand.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * PokerHandAnalystクラスのテストクラス
 * @author yasu
 * @version 1.0
 */
class PokerHandAnalystTest {

    private PokerHandAnalyst pokerHandAnalyst = new PokerHandAnalyst();

    @DisplayName("ロイヤルストレートフラッシュの結果を返す")
    @Test
    void expectedRoyalStraightFlash() {
        List<Card> cards = Arrays.asList(
                new Card(1, CardType.CLUB),
                new Card(13, CardType.CLUB),
                new Card(12, CardType.CLUB),
                new Card(11, CardType.CLUB),
                new Card(10, CardType.CLUB)
        );

        PokerHand expected = ROYAL_STRAIGHT_FLASH;
        PokerHand actual = pokerHandAnalyst.analyzePokerHand(cards);
        assertEquals(expected, actual);
    }

    @DisplayName("ストレートフラッシュの結果を返す")
    @Test
    void expectedStraightFlash() {
        List<Card> cards = Arrays.asList(
                new Card(1, CardType.CLUB),
                new Card(2, CardType.CLUB),
                new Card(3, CardType.CLUB),
                new Card(4, CardType.CLUB),
                new Card(5, CardType.CLUB)
        );

        PokerHand expected = STRAIGHT_FLASH;
        PokerHand actual = pokerHandAnalyst.analyzePokerHand(cards);

        assertEquals(expected, actual);
    }

    @DisplayName("フラッシュの結果を返す")
    @Test
    void expectedFlash() {
        List<Card> cards = Arrays.asList(
                new Card(1, CardType.CLUB),
                new Card(2, CardType.CLUB),
                new Card(3, CardType.CLUB),
                new Card(5, CardType.CLUB),
                new Card(6, CardType.CLUB)
        );

        PokerHand expected = FLASH;
        PokerHand actual = pokerHandAnalyst.analyzePokerHand(cards);
        assertEquals(expected, actual);
    }

    @DisplayName("ストレートの結果を返す")
    @Test
    void expectedStraight() {
        List<Card> cards = Arrays.asList(
                new Card(1, CardType.CLUB),
                new Card(11, CardType.HEART),
                new Card(12, CardType.CLUB),
                new Card(13, CardType.CLUB),
                new Card(10, CardType.SPADE)
        );

        PokerHand expected = STRAIGHT;
        PokerHand actual = pokerHandAnalyst.analyzePokerHand(cards);
        assertEquals(expected, actual);
    }

    @DisplayName("ノーハンドの結果を返す")
    @Test
    void expectedNoHand() {
        List<Card> cards = Arrays.asList(
                new Card(1, CardType.CLUB),
                new Card(11, CardType.HEART),
                new Card(12, CardType.CLUB),
                new Card(7, CardType.CLUB),
                new Card(5, CardType.SPADE)
        );

        PokerHand expected = NO_HAND;
        PokerHand actual = pokerHandAnalyst.analyzePokerHand(cards);
        assertEquals(expected, actual);
    }
}