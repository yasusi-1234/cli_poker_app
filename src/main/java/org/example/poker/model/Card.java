package org.example.poker.model;

import java.util.Objects;

/**
 * @author yasu
 * @version 1.0
 * ポーカーのカードを現すクラス
 */
public class Card {

    /**
     * カードの番号 1~13
     */
    private final int cardNumber;
    /**
     * カードのマーク スペード・ハート・クラブ・ダイヤの4種類
     */
    private final CardType cardType;

    /**
     * コンストラクタ
     * @param cardNumber カードの番号
     * @param cardType カードのマーク
     */
    public Card(int cardNumber, CardType cardType) {
        if(cardNumber < 1 || cardNumber > 13){
            throw new IllegalArgumentException(
                    "カードの番号が想定の範囲外です。入力された数値 -> " + cardNumber +
                    " 1~13の値を選択してください。");
        }
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
