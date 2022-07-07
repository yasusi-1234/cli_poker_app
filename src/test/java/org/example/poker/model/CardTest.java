package org.example.poker.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.example.poker.model.CardType.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * カードクラスのテストクラス
 *
 * @author yasu
 * @version 1.0
 */
class CardTest {

    @DisplayName("コンストラクタのテスト")
    @Nested
    class ConstructorTest {

        @ParameterizedTest(name = "第一引数{0}でIllegalArgumentException例外がthrowされる")
        @ValueSource(ints = {0, 14})
        void throwIllegalArgumentException(int value) {
            assertThrows(IllegalArgumentException.class,
                    () -> new Card(value, CLUB));
        }

        @ParameterizedTest(name = "{0}は正しくインスタンス化できる")
        @ValueSource(ints = {1, 13})
        void notException(int value) {
            Card actual = new Card(value, CLUB);

            assertNotNull(actual);
        }
    }

    @DisplayName("cardNumberとcardTypeが同じ場合は同じと見なす")
    @Test
    void testEquals() {
        Card card1 = new Card(1, CLUB);
        Card card2 = new Card(1, CLUB);

        boolean actual = card1.equals(card2);

        assertTrue(actual);

        assertNotSame(card1, card2);
    }
}