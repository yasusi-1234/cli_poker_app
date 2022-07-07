package org.example.poker.service;

import org.example.poker.model.Card;
import org.example.poker.model.CardType;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author yasu
 * @version 1.0
 * ポーカーの手役を現すクラス
 */
public enum PokerHand {
    ROYAL_STRAIGHT_FLASH(1000, "ロイヤルストレートフラッシュ"){
        @Override
        public boolean isThisHand(List<Card> cards) {
            List<Integer> cardNumbers =
                    cards.stream().map(Card::getCardNumber)
                            .sorted()
                            .collect(Collectors.toList());

            int firstCardNum = cardNumbers.get(0);

            return firstCardNum == 1
                    && PokerHand.STRAIGHT_FLASH.isThisHand(cards);
        }
    },
    STRAIGHT_FLASH(500, "ストレートフラッシュ"){
        @Override
        public boolean isThisHand(List<Card> cards) {
            return PokerHand.STRAIGHT.isThisHand(cards)
                    && PokerHand.FLASH.isThisHand(cards);
        }
    },
    FOUR_CARDS(250, "フォーカード"){
        @Override
        public boolean isThisHand(List<Card> cards) {
            Map<Integer, Long> cardsMap =
                    cards.stream()
                            .collect(Collectors.groupingBy(Card::getCardNumber,
                                    Collectors.counting()));

            int carsMapSize = cardsMap.size();
            long cardsMapValueMax = cardsMap.values().stream().max(Long::compareTo)
                    .orElse(0L);

            return carsMapSize == 2 && cardsMapValueMax == 4;
        }
    },
    FULL_HOUSE(125, "フルハウス"){
        @Override
        public boolean isThisHand(List<Card> cards) {
            Map<Integer, Long> cardsMap =
                    cards.stream()
                            .collect(Collectors.groupingBy(Card::getCardNumber,
                                    Collectors.counting()));

            int carsMapSize = cardsMap.size();
            long cardsMapValueMax = cardsMap.values().stream().max(Long::compareTo)
                    .orElse(0L);

            return carsMapSize == 2 && cardsMapValueMax == 3;

        }
    },
    FLASH(75, "フラッシュ"){
        @Override
        public boolean isThisHand(List<Card> cards) {
            CardType firstCardType = cards.get(0).getCardType();
            return cards.stream()
                    .map(Card::getCardType)
                    .allMatch(cardType -> Objects.equals(cardType, firstCardType));
        }
    },
    STRAIGHT(50, "ストレート"){
        @Override
        public boolean isThisHand(List<Card> cards) {
            // 1, 10, 11, 12, 13 this is straight
            // 1, 2, 3, 4, 5 this is straight
            // 1, 2, 11, 12, 13 this is not straight
            List<Integer> cardNumbers = cards.stream().map(Card::getCardNumber)
                    .sorted()
                    .collect(Collectors.toList());

            List<Integer> oneToTen = Arrays.asList(1, 10, 11, 12, 13);

            if(Objects.equals(cardNumbers, oneToTen)){
                return true;
            }

            int firstNum = cardNumbers.get(0);
            List<Integer> serialNumbers = IntStream.range(firstNum, firstNum + 5)
                    .boxed().collect(Collectors.toList());

            return Objects.equals(cardNumbers, serialNumbers);
        }
    },
    THREE_CARDS(25, "スリーカード"){
        @Override
        public boolean isThisHand(List<Card> cards) {
            Map<Integer, Long> cardsMap =
                    cards.stream()
                            .collect(Collectors.groupingBy(Card::getCardNumber,
                                    Collectors.counting()));

            int carsMapSize = cardsMap.size();
            long cardsMapValueMax = cardsMap.values().stream().max(Long::compareTo)
                    .orElse(0L);

            return carsMapSize == 3 && cardsMapValueMax == 3;
        }
    },
    TWO_PAIR(10, "ツーペア"){
        @Override
        public boolean isThisHand(List<Card> cards) {
            Map<Integer, Long> cardsMap =
                    cards.stream()
                            .collect(Collectors.groupingBy(Card::getCardNumber,
                                    Collectors.counting()));

            int cardsMapSize = cardsMap.size();
            long cardsMapValueMax = cardsMap.values().stream().max(Long::compareTo)
                    .orElse(0L);

            return cardsMapSize == 3 && cardsMapValueMax == 2;
        }
    },
    ONE_PAIR(5, "ワンペア"){
        @Override
        public boolean isThisHand(List<Card> cards) {
            Set<Integer> cardNumbers = cards.stream()
                    .map(Card::getCardNumber)
                    .collect(Collectors.toSet());

            return cardNumbers.size() == 4;
        }
    },
    NO_HAND(0, "ノーハンド"){
        @Override
        public boolean isThisHand(List<Card> cards) {
            Set<Integer> cardNumbers = cards.stream()
                    .map(Card::getCardNumber)
                    .collect(Collectors.toSet());
            return cardNumbers.size() == 5
                    && !FLASH.isThisHand(cards)
                    && !STRAIGHT.isThisHand(cards);
        }
    };

    PokerHand(int score, String handName) {
        this.score = score;
        this.handName = handName;
    }

    /**
     * カード役の得点
     */
    private final int score;

    /**
     * カード役の名前
     */
    private final String handName;

    /**
     *
     * @param cards カードリスト(5枚)を想定
     * @return カードの役が一致しているか
     */
    public abstract boolean isThisHand(List<Card> cards);

    public int getScore() {
        return score;
    }

    public String getHandName() {
        return handName;
    }
}
