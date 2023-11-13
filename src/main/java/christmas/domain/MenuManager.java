package christmas.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 각 메뉴들의 가격을 저장하고 있다.
 * 사용자의 주문을 받아, 총주문 금액을 계산한다.
 * */
public class MenuManager {
    private static final Map<String, Integer> FOOD_MENU = Map.of(
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

    public int calculateTotalOrderCost(Map<String, Integer> orderList) {
        List<String> menuNames = new ArrayList<>(orderList.keySet());
        int total = 0;
        int quantity = 0;
        int price = 0;
        for (String menuName : menuNames) {
            price = getPrice(menuName);
            quantity = orderList.get(menuName);
            total += price * quantity;
        }
        return total;
    }

    public int getPrice(String menuName) {
        if (FOOD_MENU.containsKey(menuName))
            return FOOD_MENU.get(menuName);
        return DRINKS_MENU.get(menuName);
    }
}
