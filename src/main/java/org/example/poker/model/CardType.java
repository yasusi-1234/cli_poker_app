package org.example.poker.model;

/**
 * @author yasu
 * @version 1.0
 * カードのマークを表現するクラス
 */
public enum CardType {
    SPADE("S"), HEART("H"), CLUB("C"), DIAMOND("D");

    CardType(String cardMark) {
        this.cardMark = cardMark;
    }

    /**
     * カードのマーク
     */
    private final String cardMark;

    public String getCardMark() {
        return cardMark;
    }
}
