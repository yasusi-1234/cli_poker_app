package org.example.poker.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PokerPlayerTest {

    private PokerPlayer pokerPlayer = new PokerPlayer();

    @Nested
    class cardSelectionTest {

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

        @Test
        void cardSelection() {
            provideInput("1357fgdgfdgfdg13509");

            Set<Integer> result = pokerPlayer.cardSelection();

            System.out.println("adfjkfj;aldfkj;");
            System.out.println(result);
            assertEquals(Set.of(0, 2, 4),result);
        }
    }

    @Test
    void cardChangeCheck() {
    }

    @Test
    void gameContinue() {
    }

    @Test
    void addScore() {
    }

    @Test
    void getScore() {
    }

    @Test
    void setCards() {
    }

    @Test
    void getCards() {
    }
}