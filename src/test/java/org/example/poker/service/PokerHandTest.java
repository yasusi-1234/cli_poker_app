package org.example.poker.service;

import org.example.poker.model.Card;
import org.example.poker.model.CardType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.example.poker.service.PokerHand.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * PokerHandクラスのテストクラス
 *
 * @author yasu
 * @version 1.0
 */
class PokerHandTest {
    @DisplayName("ロイヤルストレートフラッシュのテスト")
    @Nested
    class RoyalStraightFlash {
        @DisplayName("ロイヤルストレートフラッシュと認識される")
        @Test
        void isThisHandOfROYAL_STRAIGHT_FLASH() {
            List<Card> cards = Arrays.asList(
                    new Card(10, CardType.CLUB),
                    new Card(12, CardType.CLUB),
                    new Card(13, CardType.CLUB),
                    new Card(11, CardType.CLUB),
                    new Card(1, CardType.CLUB)
            );

            boolean actual = ROYAL_STRAIGHT_FLASH.isThisHand(cards);

            assertTrue(actual);
        }
        @DisplayName("ロイヤルストレートフラッシュと認識されない")
        @Test
        void isNotThisHand() {
            List<Card> cards = Arrays.asList(
                    new Card(5, CardType.CLUB),
                    new Card(4, CardType.CLUB),
                    new Card(3, CardType.CLUB),
                    new Card(2, CardType.CLUB),
                    new Card(1, CardType.CLUB)
            );

            boolean actual = ROYAL_STRAIGHT_FLASH.isThisHand(cards);

            assertFalse(actual);
        }

    }

    @DisplayName("ストレートフラッシュと認識される")
    @Test
    void isThisHandOfSTRAIGHT_FLASH() {
        List<Card> cards = Arrays.asList(
                new Card(10, CardType.CLUB),
                new Card(12, CardType.CLUB),
                new Card(8, CardType.CLUB),
                new Card(11, CardType.CLUB),
                new Card(9, CardType.CLUB)
        );

        boolean actual = STRAIGHT_FLASH.isThisHand(cards);

        assertTrue(actual);
    }

    @DisplayName("フォーカードのテスト")
    @Nested
    class FourCards {

        @DisplayName("フォーカードと認識される")
        @Test
        void isThisHand_Expected_True() {
            List<Card> cards = Arrays.asList(
                    new Card(9, CardType.CLUB),
                    new Card(9, CardType.DIAMOND),
                    new Card(9, CardType.SPADE),
                    new Card(11, CardType.CLUB),
                    new Card(9, CardType.HEART)
            );

            boolean actual = FOUR_CARDS.isThisHand(cards);

            assertTrue(actual);
        }

        @DisplayName("フォーカードと認識されない")
        @Test
        void isThisHand_Expected_False() {
            List<Card> cards = Arrays.asList(
                    new Card(9, CardType.CLUB),
                    new Card(9, CardType.DIAMOND),
                    new Card(11, CardType.SPADE),
                    new Card(11, CardType.CLUB),
                    new Card(9, CardType.HEART)
            );

            boolean actual = FOUR_CARDS.isThisHand(cards);

            assertFalse(actual);
        }

    }


    @DisplayName("フルハウスのテスト")
    @Nested
    class FullHouse {

        @DisplayName("フルハウスと認識される")
        @Test
        void isThisHand_Expected_True() {
            List<Card> cards = Arrays.asList(
                    new Card(9, CardType.CLUB),
                    new Card(9, CardType.DIAMOND),
                    new Card(11, CardType.SPADE),
                    new Card(11, CardType.CLUB),
                    new Card(9, CardType.HEART)
            );

            boolean actual = FULL_HOUSE.isThisHand(cards);

            assertTrue(actual);
        }

        @DisplayName("フルハウスと認識されない")
        @Test
        void isThisHand_Expected_False() {
            List<Card> cards = Arrays.asList(
                    new Card(9, CardType.CLUB),
                    new Card(9, CardType.DIAMOND),
                    new Card(9, CardType.SPADE),
                    new Card(11, CardType.CLUB),
                    new Card(9, CardType.HEART)
            );

            boolean actual = FULL_HOUSE.isThisHand(cards);

            assertFalse(actual);
        }

    }

    @DisplayName("フラッシュのテスト")
    @Nested
    class FlushTest {

        @DisplayName("ストレートフラッシュもフラッシュとして認識される")
        @Test
        void isThisHandOfSTRAIGHT_FLASH() {
            List<Card> cards = Arrays.asList(
                    new Card(10, CardType.CLUB),
                    new Card(12, CardType.CLUB),
                    new Card(13, CardType.CLUB),
                    new Card(11, CardType.CLUB),
                    new Card(1, CardType.CLUB)
            );

            boolean actual = FLASH.isThisHand(cards);

            assertTrue(actual);
        }

        @DisplayName("フラッシュと認識される")
        @Test
        void isThisHandOfFLASH() {
            List<Card> cards = Arrays.asList(
                    new Card(10, CardType.CLUB),
                    new Card(7, CardType.CLUB),
                    new Card(13, CardType.CLUB),
                    new Card(3, CardType.CLUB),
                    new Card(1, CardType.CLUB)
            );

            boolean actual = FLASH.isThisHand(cards);

            assertTrue(actual);
        }
    }

    @DisplayName("ストレートのテスト")
    @Nested
    class StraightTest {

        @DisplayName("ロイヤルストレートフラッシュもストレートとして認識される")
        @Test
        void isThisHandOfROYAL_STRAIGHT_FLASH() {
            List<Card> cards = Arrays.asList(
                    new Card(10, CardType.CLUB),
                    new Card(12, CardType.CLUB),
                    new Card(13, CardType.CLUB),
                    new Card(11, CardType.CLUB),
                    new Card(1, CardType.CLUB)
            );

            boolean actual = STRAIGHT.isThisHand(cards);

            assertTrue(actual);
        }

        @DisplayName("ストレートとして認識される")
        @Test
        void isThisHandOfSTRAIGHT() {
            List<Card> cards = Arrays.asList(
                    new Card(1, CardType.SPADE),
                    new Card(2, CardType.CLUB),
                    new Card(3, CardType.DIAMOND),
                    new Card(4, CardType.SPADE),
                    new Card(5, CardType.CLUB)
            );

            boolean actual = STRAIGHT.isThisHand(cards);

            assertTrue(actual);
        }

        @DisplayName("1と2の境界をまたぐ手はストレートとして認識されない")
        @Test
        void isThisHandOfSTRAIGHT_ExpectedFalse() {
            // 1, 2, 3, 4, 5 this is straight
            // 1, 10, 11, 12, 13 this is straight
            // 13, 1, 2, 3, 4 this is not straight
            List<Card> cards = Arrays.asList(
                    new Card(1, CardType.CLUB),
                    new Card(2, CardType.CLUB),
                    new Card(3, CardType.CLUB),
                    new Card(4, CardType.CLUB),
                    new Card(13, CardType.CLUB)
            );

            boolean actual = STRAIGHT.isThisHand(cards);

            assertFalse(actual);
        }

    }

    @DisplayName("スリーカードのテスト")
    @Nested
    class ThreeCards {

        @DisplayName("スリーカードとして認識される")
        @Test
        void isThisHandOfTHREE_CARDS() {
            List<Card> cards = Arrays.asList(
                    new Card(1, CardType.SPADE),
                    new Card(4, CardType.CLUB),
                    new Card(11, CardType.DIAMOND),
                    new Card(1, CardType.HEART),
                    new Card(1, CardType.CLUB)
            );

            boolean actual = THREE_CARDS.isThisHand(cards);

            assertTrue(actual);
        }

        @DisplayName("スリーカードとして認識されない")
        @Test
        void isThisHandOfTHREE_CARDS_ExpectedFalse() {
            List<Card> cards = Arrays.asList(
                    new Card(1, CardType.SPADE),
                    new Card(4, CardType.CLUB),
                    new Card(11, CardType.DIAMOND),
                    new Card(4, CardType.HEART),
                    new Card(1, CardType.CLUB)
            );

            boolean actual = THREE_CARDS.isThisHand(cards);

            assertFalse(actual);
        }

    }

    @DisplayName("ツーペアのテスト")
    @Nested
    class TwoPair {

        @DisplayName("ツーペアとして認識される")
        @Test
        void isThisHandOfTWO_PAIR() {
            List<Card> cards = Arrays.asList(
                    new Card(1, CardType.SPADE),
                    new Card(4, CardType.CLUB),
                    new Card(5, CardType.DIAMOND),
                    new Card(5, CardType.HEART),
                    new Card(4, CardType.HEART)
            );

            boolean actual = TWO_PAIR.isThisHand(cards);

            assertTrue(actual);
        }

        @DisplayName("ツーペアとして認識されない")
        @Test
        void isThisHandOfTWO_PAIR_ExpectedFalse() {
            List<Card> cards = Arrays.asList(
                    new Card(1, CardType.SPADE),
                    new Card(4, CardType.CLUB),
                    new Card(11, CardType.DIAMOND),
                    new Card(1, CardType.HEART),
                    new Card(1, CardType.CLUB)
            );

            boolean actual = TWO_PAIR.isThisHand(cards);

            assertFalse(actual);
        }

    }

    @DisplayName("ワンペアとして認識される")
    @Test
    void onePairTest () {
        List<Card> cards = Arrays.asList(
                new Card(1, CardType.SPADE),
                new Card(4, CardType.CLUB),
                new Card(11, CardType.DIAMOND),
                new Card(4, CardType.HEART),
                new Card(8, CardType.CLUB)
        );

        boolean actual = ONE_PAIR.isThisHand(cards);

        assertTrue(actual);
    }

    @DisplayName("ノーハンドのテスト")
    @Nested
    class NoHand {

        @DisplayName("ノーハンドとして認識される")
        @Test
        void isThisHandOfNO_HAND() {
            List<Card> cards = Arrays.asList(
                    new Card(1, CardType.SPADE),
                    new Card(4, CardType.CLUB),
                    new Card(7, CardType.DIAMOND),
                    new Card(9, CardType.HEART),
                    new Card(12, CardType.HEART)
            );

            boolean actual = NO_HAND.isThisHand(cards);

            assertTrue(actual);
        }

        @DisplayName("フラッシュの場合ノーハンドとして認識されない")
        @Test
        void isThisHandOfFlush() {
            List<Card> cards = Arrays.asList(
                    new Card(1, CardType.SPADE),
                    new Card(4, CardType.SPADE),
                    new Card(11, CardType.SPADE),
                    new Card(7, CardType.SPADE),
                    new Card(9, CardType.SPADE)
            );

            boolean actual = NO_HAND.isThisHand(cards);

            assertFalse(actual);
        }

        @DisplayName("ストレートの場合ノーハンドとして認識されない")
        @Test
        void isThisHandOfStraight() {
            List<Card> cards = Arrays.asList(
                    new Card(1, CardType.SPADE),
                    new Card(2, CardType.SPADE),
                    new Card(3, CardType.SPADE),
                    new Card(4, CardType.SPADE),
                    new Card(5, CardType.SPADE)
            );

            boolean actual = NO_HAND.isThisHand(cards);

            assertFalse(actual);
        }

    }

}