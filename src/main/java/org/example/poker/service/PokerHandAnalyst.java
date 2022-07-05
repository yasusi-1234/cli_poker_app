package org.example.poker.service;

import org.example.poker.model.Card;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yasu
 * @version 1.0
 */
public class PokerHandAnalyst {

    /**
     * 引数で渡されたカードリストの手役を判定し
     * 合致した{@link PokerHand}を返却する
     * @param cards 精査したいカードリスト(5枚を想定)
     * @return 精査されたカード役
     */
    public PokerHand analyzePokerHand(List<Card> cards) {

        List<PokerHand> pokerHands = Arrays.stream(PokerHand.values())
                .sorted(Comparator.comparingInt(PokerHand::getScore).reversed())
                .collect(Collectors.toList());

        return pokerHands.stream()
                .filter(hand -> hand.isThisHand(cards))
                .findFirst().orElse(PokerHand.NO_HAND);
    }
}
