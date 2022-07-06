package org.example.poker.service;

import java.util.Arrays;
import java.util.Objects;

/**
 * プレイヤーの返答を現すクラス
 * @author yasu
 * @version 1.0
 */
public enum PlayerResponse {

    /** 続行 */
    CONTINUE("y"),
    /** 終了 */
    GAME_END("n"),
    /** ステータス確認 */
    SHOW_STATUS("s");

    /**
     * 返答コード
     */
    private final String response;

    PlayerResponse(String response) {
        this.response = response;
    }

    public static PlayerResponse getByResponse(String str) {
        return Arrays.stream(PlayerResponse.values())
                .filter(pr -> Objects.equals(pr.response, str))
                .findFirst().orElse(CONTINUE);
    }
}
