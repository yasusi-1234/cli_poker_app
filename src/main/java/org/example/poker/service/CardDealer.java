package org.example.poker.service;

import org.example.poker.model.Card;
import org.example.poker.model.CardType;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author yasu
 * @version 1.0
 * 全てのカードを管理するクラス。カードを配る・シャッフルする
 */
public class CardDealer {

    /**
     * 初回に配るカード枚数値
     */
    private static final int FIRST_DEAL_COUNT = 5;

    /**
     * カード全種類52枚を格納しているリスト
     */
    private final List<Card> allCards;

    /**
     * 初回に配ったカードを記憶する領域
     */
    private List<Card> dealCards;

    /**
     * コンストラクタ
     */
    public CardDealer() {
        List<Card> createAllCards = new ArrayList<>();

        for(CardType cardType: CardType.values()){
            IntStream.rangeClosed(1, 13)
                    .mapToObj(num -> new Card(num, cardType))
                    .forEach(createAllCards::add);
        }

        allCards = createAllCards;
        cardsShuffle();
    }

    /**
     * フィールドallCardsの中身をシャッフルする
     */
    public void cardsShuffle(){
        Collections.shuffle(allCards);
    }

    /**
     * ゲーム初めの5枚のカードを配る為のメソッド
     * allCardsの先頭から5個分のリストを抜き出し、抜き出した配列をdealCardsに記憶し
     * 返却する
     * @return allCardsから5個抜き出したカードの配列
     */
    public List<Card> firstDistributeCards() {
        dealCards = new ArrayList<>(allCards.subList(0, FIRST_DEAL_COUNT));
        return dealCards;
    }

    /**
     * ユーザーが取捨選択した後に配る事を想定したメソッド
     * dealCount分のカード枚数分のカードをリスト型で返却する
     * 初回引いた分の次の要素から配列を選択している
     * @param dealCount 必要枚数
     * @return 必要枚数分のカードリスト
     */
    public List<Card> distributeCards(int dealCount) {
        return new ArrayList<>(allCards.subList(FIRST_DEAL_COUNT, FIRST_DEAL_COUNT + dealCount));
    }

    public List<Card> getAllCards() {
        return allCards;
    }

    public List<Card> getDealCards() {
        return dealCards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardDealer that = (CardDealer) o;
        return allCards.equals(that.allCards) && dealCards.equals(that.dealCards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(allCards, dealCards);
    }
}
