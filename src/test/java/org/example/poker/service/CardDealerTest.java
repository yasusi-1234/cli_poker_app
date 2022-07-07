package org.example.poker.service;

import org.example.poker.model.Card;;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * {@link CardDealer}クラスのテスト
 * @author yasu
 * @version 1.0
 */
class CardDealerTest {

    @DisplayName("コンストラクタの処理でフィールド値が正しく挿入できているかのテスト")
    @Test
    void constructorTest() {
        CardDealer cardDealer = new CardDealer();

        // fieldのallCardsに重複の無い52種のCardオブジェクトが格納されている
        int expectedCardSize = 52;
        int actualSize = cardDealer.getAllCards().size();
        // 期待した要素分のCardオブジェクトが格納されているか
        assertEquals(expectedCardSize, actualSize);
        Set<Card> cardsSet = new HashSet<>(cardDealer.getAllCards());

        int actualCardSetSize = cardsSet.size();

        // 重複が無いか
        assertEquals(expectedCardSize, actualCardSetSize);
    }

    @DisplayName("allCardsフィールドの先頭から5要素分を取得して返却しているかのテスト")
    @Test
    void firstDistributeCardsTest() {
        CardDealer cardDealer = new CardDealer();
        List<Card> actual = cardDealer.firstDistributeCards();

        List<Card> expected = cardDealer.getAllCards().subList(0, 5);

        assertEquals(expected, actual);
    }

    @DisplayName("distributeCardsメソッドのテスト")
    @Nested
    class DistributeCards {

        @DisplayName("第二引数値のセットのサイズが0の場合は第一引数値のリストを返却する")
        @Test
        void returnFirstArgCardsTest() {
            CardDealer cardDealer = new CardDealer();
            // 空のセット
            Set<Integer> set = new HashSet<>();
            // ダミーのカードリスト
            List<Card> expected = cardDealer.firstDistributeCards();
            List<Card> actual = cardDealer.distributeCards(expected, set);

            assertEquals(expected, actual);
        }

        @DisplayName("第二引数で与えたdropIndexesの要素の書き換えが正確に行われている")
        @Test
        void changeCardsTest() {
            CardDealer cardDealer = new CardDealer();
            List<Card> dealCards = cardDealer.firstDistributeCards();

            // 0, 2, 4の配列
            List<Integer> indexes = Arrays.asList(0, 2, 4);
            // 0, 2, 4番目のIndexSet
            Set<Integer> dropIndexes = new HashSet<>(indexes);

            // 期待する変更するカードリスト
            List<Card> expectedChangeList = cardDealer.getAllCards().subList(5, 5 + dropIndexes.size());

            List<Card> actual = cardDealer.distributeCards(dealCards, dropIndexes);

            // 変更するべきカードが全て含まれている
            assertTrue(actual.containsAll(expectedChangeList));

        }

        @DisplayName("第二引数で与えたdropIndexesの以外の要素は書き換えられていない")
        @Test
        void noChangeCardsTest() {
            CardDealer cardDealer = new CardDealer();
            List<Card> dealCards = cardDealer.firstDistributeCards();

            // 0, 2, 4の配列
            List<Integer> indexes = Arrays.asList(0, 2, 4);
            // 0, 2, 4番目のIndexSet
            Set<Integer> dropIndexes = new HashSet<>(indexes);

            List<Card> actual = cardDealer.distributeCards(dealCards, dropIndexes);

            // 変更されたくないインデックス 1, 3
            List<Integer> expectedNotChangeIndexes = Arrays.asList(1,3);

            expectedNotChangeIndexes.forEach(expectIndex -> {
                assertEquals(dealCards.get(expectIndex), actual.get(expectIndex));
            });

        }
    }
}