package org.example.poker.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.example.poker.service.PlayerResponse.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * PlayerResponseクラスのテストクラス
 * @author yasu
 * @version 1.0
 */
class PlayerResponseTest {

    @DisplayName("getByResponseはyを受け取るとCONTINUEを返却する")
    @Test
    void getByResponse_Y() {

        PlayerResponse actual = PlayerResponse.getByResponse("y");
        PlayerResponse expected = CONTINUE;

        assertEquals(expected, actual);
    }

    @DisplayName("getByResponseはnを受け取るとCONTINUEを返却する")
    @Test
    void getByResponse_N() {

        PlayerResponse actual = PlayerResponse.getByResponse("n");
        PlayerResponse expected = GAME_END;

        assertEquals(expected, actual);
    }

    @DisplayName("getByResponseはsを受け取るとSHOW_STATUSを返却する")
    @Test
    void getByResponse_S() {

        PlayerResponse actual = PlayerResponse.getByResponse("s");
        PlayerResponse expected = SHOW_STATUS;

        assertEquals(expected, actual);
    }

    @DisplayName("getByResponseはnull或いはyns以外を受け取るとCONTINUEを返却する")
    @Test
    void getByResponse_Other() {

        PlayerResponse actualOfNull = PlayerResponse.getByResponse(null);
        PlayerResponse expected = CONTINUE;

        assertEquals(expected, actualOfNull);

        PlayerResponse actualOther = PlayerResponse.getByResponse("t");

        assertEquals(expected, actualOther);
    }
}