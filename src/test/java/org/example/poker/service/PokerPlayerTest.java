package org.example.poker.service;

import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Set;

import static org.example.poker.service.PlayerResponse.*;
import static org.example.poker.service.PokerHand.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * PokerPlayerクラスのテストクラス
 *
 * @author yasu
 * @version 1.0
 */
class PokerPlayerTest {

    private PokerPlayer pokerPlayer;

    @BeforeEach
    void setUp() {
        pokerPlayer = new PokerPlayer();
    }

    @DisplayName("コンストラクタのテスト")
    @Nested
    class ConstructorTest {

        @DisplayName("scoreフィールドが正しく0で初期化されている")
        @Test
        void scoreInitTest() {
            int expected = 0;
            int actual = pokerPlayer.getScore();

            assertEquals(expected, actual);
        }

        @DisplayName("pokerResultMapフィールドが正しく初期化されている")
        @Test
        void pokerResultMapInitTest() {
            // 全てのPokerHand
            List<PokerHand> hands = Arrays.asList(PokerHand.values());

            EnumMap<PokerHand, Integer> actual = pokerPlayer.getPokerResultMap();

            // 全てのPokerHandが含まれている事
            hands.forEach(hand ->
                    assertTrue(actual.containsKey(hand))
            );

            // 初期値の期待値
            int expectedNum = 0;

            actual.forEach((key, value) -> {
                // actual一要素のkeyに対応するvalue
                int actualNum = value;
                assertEquals(expectedNum, actualNum);
            });
        }
    }

    @DisplayName("ユーザーの返答を要望するメソッド系統のテスト")
    @Nested
    class useScannerAndSystemInTest {

        private final InputStream systemIn = System.in;
        private final PrintStream systemOut = System.out;

        private ByteArrayInputStream testIn;
        private ByteArrayOutputStream testOut;

        @BeforeEach
        public void setUpOutput() {
            testOut = new ByteArrayOutputStream();
            System.setOut(new PrintStream(testOut));
        }

        private void provideInput(String data) {
            testIn = new ByteArrayInputStream(data.getBytes());
            System.setIn(testIn);
        }

        private String getOutput() {
            return testOut.toString();
        }

        @AfterEach
        public void restoreSystemInputOutput() {
            System.setIn(systemIn);
            System.setOut(systemOut);
        }

        @DisplayName("cardSelectionメソッドは1から5の値を解析しその値から" +
                "1減算した値をSetに格納し返却する")
        @Test
        void cardSelectionNormal() {
            // ユーザーからのインプット値
            provideInput("135");

            Set<Integer> actual = pokerPlayer.cardSelection();

            Set<Integer> expected = Set.of(0, 2, 4);

            assertEquals(expected, actual);
        }

        @DisplayName("cardSelectionメソッドは1から5以外の値を無視して正しく処理する")
        @Test
        void cardSelectionEliminateInvalidValues() {
            // ユーザーからのインプット値
            provideInput("23 5 ab.-2あい処");

            Set<Integer> actual = pokerPlayer.cardSelection();

            Set<Integer> expected = Set.of(1, 2, 4);
            assertEquals(expected, actual);
        }

        @DisplayName("cardSelectionメソッドは1から5の値が無い場合空のSetを返却する")
        @Test
        void cardSelectionEmpty() {
            // ユーザーからのインプット値
            provideInput("あい処abd 60");

            Set<Integer> actual = pokerPlayer.cardSelection();

            assertTrue(actual.isEmpty());
        }

        @DisplayName("cardChangeCheckメソッドは入力値にyが含まれる場合trueを返却する")
        @Test
        void cardChangeCheckTrue() {
            // ユーザーからのインプット値
            provideInput("y");

            boolean actual = pokerPlayer.cardChangeCheck();

            assertTrue(actual);
        }

        @DisplayName("cardChangeCheckメソッドは入力値にnが含まれる場合falseを返却する")
        @Test
        void cardChangeCheckFalse() {
            // ユーザーからのインプット値
            provideInput("n");

            boolean actual = pokerPlayer.cardChangeCheck();

            assertFalse(actual);
        }

        @DisplayName("cardChangeCheckメソッドは入力値にyの後にnが入力されていた場合はtrueを返却する")
        @Test
        void cardChangeCheckFirst_Y_True() {
            // ユーザーからのインプット値
            provideInput("yje12n");

            boolean actual = pokerPlayer.cardChangeCheck();

            assertTrue(actual);
        }

        @DisplayName("cardChangeCheckメソッドは入力値にyの後にnが入力されていた場合はtrueを返却する")
        @Test
        void cardChangeCheckFirst_N_False() {
            // ユーザーからのインプット値
            provideInput("nje12y");

            boolean actual = pokerPlayer.cardChangeCheck();

            assertFalse(actual);
        }

        @DisplayName("cardChangeCheckメソッドは入力値にy及びnが含まれない場合はtrueを返却する")
        @Test
        void cardChangeCheckNotIn_Y_And_N() {
            // ユーザーからのインプット値
            provideInput("je128hgpあいうえお");

            boolean actual = pokerPlayer.cardChangeCheck();

            assertTrue(actual);
        }

        @DisplayName("cardChangeCheckメソッドは入力値が空の場合はtrueを返却する")
        @Test
        void cardChangeCheck_Empty() {
            // ユーザーからのインプット値
            provideInput(System.lineSeparator());

            boolean actual = pokerPlayer.cardChangeCheck();

            assertTrue(actual);
        }

        @DisplayName("gameContinueメソッドはyが入力されるとCONTINUEを返却する")
        @Test
        void gameContinueIn_Y() {
            // ユーザーからのインプット値
            provideInput("y");

            PlayerResponse actual = pokerPlayer.gameContinue();

            PlayerResponse expected = CONTINUE;
            assertEquals(expected, actual);
        }

        @DisplayName("gameContinueメソッドはnが入力されるとGAME_ENDを返却する")
        @Test
        void gameContinueIn_N() {
            // ユーザーからのインプット値
            provideInput("n");

            PlayerResponse actual = pokerPlayer.gameContinue();

            PlayerResponse expected = GAME_END;
            assertEquals(expected, actual);
        }

        @DisplayName("gameContinueメソッドはsが入力されるとSHOW_STATUSを返却する")
        @Test
        void gameContinueIn_S() {
            // ユーザーからのインプット値
            provideInput("s");

            PlayerResponse actual = pokerPlayer.gameContinue();

            PlayerResponse expected = SHOW_STATUS;
            assertEquals(expected, actual);
        }

        @DisplayName("gameContinueメソッドは入力値が空の場合はCONTINUEを返却する")
        @Test
        void gameContinueEmpty() {
            // ユーザーからのインプット値
            provideInput(System.lineSeparator());

            PlayerResponse actual = pokerPlayer.gameContinue();

            PlayerResponse expected = CONTINUE;
            assertEquals(expected, actual);
        }
    }

    @DisplayName("addScoreは正しく値を加算する")
    @Test
    void addScore() {
        int nowScore = pokerPlayer.getScore();
        int addScore = 100;
        int actual = pokerPlayer.addScore(addScore);

        int expected = nowScore + addScore;

        assertEquals(expected, actual);

        int nextScore = pokerPlayer.getScore();
        int nextAddScore = 500;
        int nextActual = pokerPlayer.addScore(nextAddScore);

        int nextExpected = nextScore + nextAddScore;

        assertEquals(nextExpected, nextActual);
    }

    @DisplayName("addPokerResultMapは正しく上がり役を上書きする")
    @Test
    void addPokerResultMap() {

        // NO_HANDを5回追加
        for (int i = 0; i < 5; i++) {
            pokerPlayer.addPokerResultMap(NO_HAND);
        }
        // ONE_PAIRを10回追加
        for (int i = 0; i < 10; i++) {
            pokerPlayer.addPokerResultMap(ONE_PAIR);
        }
        // TWO_PAIRを10回追加
        for (int i = 0; i < 7; i++) {
            pokerPlayer.addPokerResultMap(TWO_PAIR);
        }

        int noHandExpected = 5;
        int onePairExpected = 10;
        int twoPairExpected = 7;

        EnumMap<PokerHand, Integer> actual = pokerPlayer.getPokerResultMap();

        assertAll(
                () -> assertEquals(noHandExpected, actual.get(NO_HAND)),
                () -> assertEquals(onePairExpected, actual.get(ONE_PAIR)),
                () -> assertEquals(twoPairExpected, actual.get(TWO_PAIR))
        );

    }

}