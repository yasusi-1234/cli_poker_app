package org.example.poker.service;

import org.example.poker.model.Card;

import java.util.*;
import java.util.stream.Collectors;

/**
 * ポーカーのプレイヤーを表すクラス
 *
 * @author yasu
 * @version 1.0
 */
public class PokerPlayer {

    /**
     * プレイヤーのスコア
     */
    private int score;

    /**
     * プレイヤーのゲーム回数
     */
    private int gameCount;

    /**
     * プレイヤーのカード
     */
    private List<Card> cards;

    /**
     * プレイヤーのポーカー上がり役の記録
     */
    private final EnumMap<PokerHand, Integer> pokerResultMap;

    /**
     * コンストラクタ
     */
    public PokerPlayer() {
        this.score = 0;
        // pokerResultMapの初期化
        pokerResultMap = new EnumMap<>(PokerHand.class);
        Arrays.asList(PokerHand.values()).forEach(hand -> pokerResultMap.put(hand, 0));
    }

    /**
     * プレイヤーが選択したカードのインデックスをSet化して返却する
     *
     * @return プレイヤーが選択したカードインデックス
     */
    public Set<Integer> cardSelection() {
        Scanner sc = new Scanner(System.in);
        // 絞りだし用のリスト
        List<String> filterList = Arrays.asList("1", "2", "3", "4", "5");
        // 入力された文字列
        String inputValue = sc.nextLine();
        Set<Integer> valueSet = Arrays.stream(inputValue.split(""))
                .distinct()
                .filter(filterList::contains)
                .map(val -> Integer.parseInt(val) - 1)
                .collect(Collectors.toSet());

        return valueSet;
    }

    /**
     * プレイヤーのカード取捨状況の是非を確認し返却する
     *
     * @return プレイヤーのカード取捨状況の是非
     */
    public boolean cardChangeCheck() {
        Scanner sc = new Scanner(System.in);

        String findFirstVal = Arrays.stream(sc.nextLine().toLowerCase().split(""))
                .filter(ch -> ch.equals("y") || ch.equals("n"))
                .findFirst().orElse("y");
                return findFirstVal.equals("y");
    }

    /**
     * プレイヤーのゲームを続けるか・やめるか・ステータスを確認するかを
     * 選択させ、結果 {@link PlayerResponse} を返却する
     *
     * @return プレイヤーの続けるか・やめるか・ステータスを確認するかの {@link PlayerResponse} 値
     */
    public PlayerResponse gameContinue() {
        Scanner sc = new Scanner(System.in);
        // 絞りだし用のリスト
        List<String> filterList = Arrays.asList("y", "n", "s");
        String inputValue = Arrays.stream(sc.nextLine().toLowerCase().split(""))
                .filter(filterList::contains)
                .findFirst().orElse("y");

        return PlayerResponse.getByResponse(inputValue);
    }

    /**
     * ユーザーのスコアに加算する
     *
     * @param score 追加するスコア
     * @return 追加された後のスコア
     */
    public int addScore(int score) {
        this.score += score;
        return this.score;
    }

    /**
     * ユーザーのゲーム回数を1増やす
     * @return 増やされた後のゲーム回数
     */
    public int addGameCount(){
        gameCount++;
        return gameCount;
    }

    /**
     * ポーカーの上がり役を追加する
     * @param pokerHand 上がったポーカー役
     */
    public void addPokerResultMap(PokerHand pokerHand) {
        pokerResultMap.put(pokerHand, pokerResultMap.get(pokerHand) + 1);
    }

    public int getScore() {
        return score;
    }

    public int getGameCount() {
        return gameCount;
    }

    public EnumMap<PokerHand, Integer> getPokerResultMap() {
        return pokerResultMap;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public List<Card> getCards() {
        return cards;
    }
}
