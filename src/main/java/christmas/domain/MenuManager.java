package christmas.domain;

import java.util.Map;

/*
 * 각 메뉴의 가격들을 저장하고 있다.
 * 사용자의 주문을 받아, 총주문 금액을 계산한다.
 * 10000원 이상일 경우 할인 이벤트가 적용된다.
 * */
public class MenuManager {
    private static final Map<String, Integer> MENU_ITEMS = Map.of(
            "양송이수프", 6000,
            "타파스", 5500,
            "시저샐러드", 8000,
            "티본스테이크", 55000,
            "바비큐립", 54000,
            "해산물파스타", 35000,
            "크리스마스파스타", 25000,
            "초코케이크", 15000,
            "아이스크림", 5000
            );
    private static final Map<String, Integer> DRINKS_MENU = Map.of(
            "제로콜라", 3000,
            "레드와인", 60000,
            "샴페인", 25000
    );

}
