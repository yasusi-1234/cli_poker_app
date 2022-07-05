package org.example.poker.view;

import org.apache.commons.lang3.StringUtils;
import org.example.poker.model.Card;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * ポーカーゲームの状態等を表示するためのクラス
 *
 * @author yasu
 * @version 1.0
 */
public class PokerPrinter {

    private static final String BR = System.lineSeparator();

    public void printUserScore() {

    }

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
    }

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

    private void printHoldAndDrop(Set<Integer> numberIndexes) {
        StringBuilder sb = new StringBuilder("      "); // スペース6文字
        String hold = "HOLD";
        String drop = "DROP";
        String characterSpaces = "          ";

        for (int i = 0; i < 5; i++) {
            if (numberIndexes.contains(i + 1)){
                sb.append(hold);
                sb.append(characterSpaces); // スペース10文字
            }else {
                sb.append(drop);
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
