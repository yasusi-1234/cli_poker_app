package org.example.poker.model;

public enum CardType {
    SPADE("♠"), HEART("♥"), CLUB("♣"), DIAMOND("♦");

    CardType(String cardMark) {
        this.cardMark = cardMark;
    }

    private final String cardMark;

    public String getCardMark() {
        return cardMark;
    }
}
