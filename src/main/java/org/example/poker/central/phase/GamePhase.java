package org.example.poker.central.phase;

import org.example.poker.central.PokerGameSystem;

/**
 * ゲームのフェーズを現すインターフェース
 *
 * @author yasu
 * @version 1.0
 */
public interface GamePhase {

    /**
     * フェーズごとに処理したい内容を記載する
     * @param pokerGameSystem {@link PokerGameSystem} ゲームシステム
     */
    void advanceGame(PokerGameSystem pokerGameSystem);

}
