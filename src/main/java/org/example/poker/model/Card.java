package org.example.poker.model;

import java.util.Objects;

/**
 * @author yasu
 * @version 1.0
 * ポーカーのカードを現すクラス
 */
public class Card {

    private final int cardNumber;
    private final CardType cardType;

    public Card(int cardNumber, CardType cardType) {
        this.cardNumber = cardNumber;
        this.cardType = cardType;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public CardType getCardType() {
        return cardType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return cardNumber == card.cardNumber && cardType == card.cardType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, cardType);
    }
}
