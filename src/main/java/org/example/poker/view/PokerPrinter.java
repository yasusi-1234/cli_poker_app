package org.example.poker.view;

import org.apache.commons.lang3.StringUtils;
import org.example.poker.model.Card;
import org.example.poker.service.PokerHand;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * ポーカーゲームの状態等を表示するためのクラス
 *
 * @author yasu
 * @version 1.0
 */
public class PokerPrinter {

    /**
     * ユーザーのステータスを表示する
     * @param pokerResultMap ユーザーのこれまでの上がり役の結果
     * @param score ユーザーのトータルスコア
     */
    public void printUserScore(Map<PokerHand, Integer> pokerResultMap, int score, int gameCount) {

        System.out.println();
        printOuterFrame();
        System.out.println();

        System.out.println(" あんたのゲームプレイ回数: " + gameCount + " 回");
        System.out.println(" あんたのTotal Score: " + score + " てん!!");
        System.out.println();

        pokerResultMap.forEach((key, value)
                -> System.out.println(" " + key.getHandName() + " : "
                + value + " 回"));

        System.out.println();
        printOuterFrame();
        System.out.println();

        printContinue();
    }

    /**
     * ユーザーに配られたカード情報を表示する
     * @param cards ユーザーが保持しているカード
     */
    public void printUserCards(List<Card> cards) {
        System.out.println();
        printOuterFrame();
        System.out.println();
        System.out.println("あなたのカード");
        System.out.println();

        printUserCardsInner(cards);

        System.out.println();
        printOuterFrame();
        System.out.println();

        System.out.println("捨てたいカードを選んでください 入力例) 13 <=この場合、左から1番目,3番目のカードが捨てられます");
    }

    /**
     * ユーザーの取捨選択状況を表示する
     *
     * @param cards ユーザーが所持しているカード
     * @param dropIndexes ユーザーが選択した捨てるカードインデックスセット
     */
    public void printUserCardsSelection(List<Card> cards, Set<Integer> dropIndexes) {
        System.out.println();

        printOuterFrame();

        System.out.println();
        System.out.println("カードの取捨選択状況");
        System.out.println();

        printUserCardsInner(cards);

        System.out.println();

        printHoldAndDrop(dropIndexes);
        System.out.println();

        printOuterFrame();

        System.out.println();

        System.out.println("上記状態で宜しいですか？ (y/n) y or enter => ゲーム進行, n => やり直し");
    }

    /**
     * ゲームの結果を表示する
     *
     * @param cards ユーザーの保持するカード
     * @param msg 何らかのゲームシステムからのメッセージ
     */
    public void printUserCardsResult(List<Card> cards,
                                     PokerHand hand,
                                     String msg) {
        System.out.println();
        printOuterFrame();
        System.out.println();
        System.out.println("結果");
        System.out.println("上がり役: " + hand.getHandName() + " 得点: " + hand.getScore() + " 点");
        System.out.println();
        System.out.println(msg);
        System.out.println();

        printUserCardsInner(cards);

        System.out.println();
        printOuterFrame();
        System.out.println();

        printContinue();
    }

    private void printContinue() {
        System.out.println("ゲームを続けますか？ (y/n) y or enter => 続行, n => 終了");
        System.out.println("あなたのステータスを確認するには => s");
    }

    private void printUserCardsInner(List<Card> cards) {
        printCardFrameTopOrBottom();
        printCardInnerTop(cards);
        printCardInner();
        printCardInnerMiddle(cards);
        printCardInner();
        printCardInnerBottom(cards);
        printCardFrameTopOrBottom();
    }

    private void printHoldAndDrop(Set<Integer> dropIndexes) {
        StringBuilder sb = new StringBuilder("      "); // スペース6文字
        String hold = "HOLD";
        String drop = "DROP";
        String characterSpaces = "          ";

        for (int i = 0; i < 5; i++) {
            if (dropIndexes.contains(i)){
                sb.append(drop);
                sb.append(characterSpaces); // スペース10文字
            }else {
                sb.append(hold);
                sb.append(characterSpaces);
            }
        }

        System.out.println(sb.toString());
    }

    private void printCardFrameTopOrBottom() {
        System.out.println("  ***********   ***********   ***********   ***********   *********** ");
    }

    private void printCardInnerMiddle(List<Card> cards) {
        List<String> marks = cards.stream().map(card -> card.getCardType().getCardMark())
                .collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();
        sb.append("  ");
        for (int i = 0; i < 5; i++ ){
            sb.append("*    ");
            sb.append(marks.get(i));
            sb.append("    *   ");
        }
        System.out.println(sb.toString());
    }

    private void printCardInnerTop(List<Card> cards) {
        List<Integer> numbers = cards.stream().map(Card::getCardNumber).collect(Collectors.toList());
        // 1枚のカードの文字数11文字 先頭・後方(*) カードナンバー 1スペース(数値)で配置
        StringBuilder sb = new StringBuilder();
        sb.append("  ");
        for (int i = 0; i < 5; i++){
            sb.append("* ");
            Integer num = numbers.get(i);
            int numLength = num > 9 ? 2 : 1;
            sb.append(num);
            sb.append(StringUtils.repeat(" ", (7 - numLength)));
            sb.append(" *   ");
        }
        System.out.println(sb.toString());
    }

    private void printCardInnerBottom(List<Card> cards) {
        List<Integer> numbers = cards.stream().map(Card::getCardNumber).collect(Collectors.toList());
        // 1枚のカードの文字数11文字 先頭・後方(*) カードナンバー 1スペース(数値)で配置
        StringBuilder sb = new StringBuilder();
        sb.append("  ");
        for (int i = 0; i < 5; i++){
            sb.append("*");
            Integer num = numbers.get(i);
            int numLength = num > 9 ? 2 : 1;
            sb.append(StringUtils.repeat(" ", 8 - numLength));
            sb.append(num);
            sb.append(" *   ");
        }
        System.out.println(sb.toString());
    }

    private void printCardInner() {
        System.out.println("  *         *   *         *   *         *   *         *   *         *  ");
    }

    private void printOuterFrame() {
        System.out.println("***********************************************************************");
    }
}
