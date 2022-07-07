package org.example.poker.service;

import org.example.poker.model.Card;
import org.example.poker.model.CardType;

import java.util.*;
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
        return new ArrayList<>(allCards.subList(0, FIRST_DEAL_COUNT));
    }

    /**
     * ユーザーが取捨選択した後に配る事を想定したメソッド
     * dropIndexesのサイズ分の新たに配るカードを抽出し
     * dropIndexesに格納されているインデックスに新たに配るカードをセットし
     * 返却する
     *
     * @param playerCards プレイヤーのカード
     * @param dropIndexes 捨てたいインデックス値のセット
     * @return 必要枚数分のカードリスト
     */
    public List<Card> distributeCards(List<Card> playerCards, Set<Integer> dropIndexes) {
        int dealCount = dropIndexes.size();

        if (dealCount == 0){
            return playerCards;
        }

        List<Card> dealCards = new ArrayList<>(allCards.subList(FIRST_DEAL_COUNT, FIRST_DEAL_COUNT + dealCount));

        List<Card> returnCards = new ArrayList<>(playerCards);

        int counter = 0;
        for(Integer changeIndex : dropIndexes){
            returnCards.set(changeIndex, dealCards.get(counter));
            counter++;
        }
        return returnCards;
    }

    public List<Card> getAllCards() {
        return allCards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardDealer that = (CardDealer) o;
        return Objects.equals(allCards, that.allCards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(allCards);
    }
}
