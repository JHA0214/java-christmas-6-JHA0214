package christmas.domain;

import java.util.*;

/*
 * 각 메뉴들의 가격을 저장하고 있다.
 * 사용자의 주문을 받아, 총주문 금액을 계산한다.
 * */
public class MenuManager {
    private int appetizer = 0;
    private int main = 0;
    private int dessert = 0;
    private int drink = 0;

    public MenuManager() {
    }

    private static final Map<String, Integer> APPETIZER_MENU = Map.of(
            "양송이수프", 6000,
            "타파스", 5500,
            "시저샐러드", 8000
    );
    private static final Map<String, Integer> MAIN_MENU = Map.of(
            "티본스테이크", 55000,
            "바비큐립", 54000,
            "해산물파스타", 35000,
            "크리스마스파스타", 25000
    );
    private static final Map<String, Integer> DESSERT_MENU = Map.of(
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
            if (price == 0)
                return 0;
            quantity = orderList.get(menuName);
            total += price * quantity;
        }
        return total;
    }

    public int getPrice(String menuName) {
        try {
            validate(menuName);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage() + ErrorMessage.ORDER.getmessages());
            return 0;
        }
        return ckeckMenu(menuName);
    }

    public void validate(String menuName) {
        try {
            ckeckMenu(menuName);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException(ErrorMessage.ERROR.getmessages());
        }
    }

    public int ckeckMenu(String menuName) {
        if (MAIN_MENU.containsKey(menuName) || DRINKS_MENU.containsKey(menuName))
            return getMainOrDrinkPrice(menuName);
        return getAppetizerOrDessertPrice(menuName);
    }

    public int getMainOrDrinkPrice(String menuName) {
        if (MAIN_MENU.containsKey(menuName)) {
            main++;
            return MAIN_MENU.get(menuName);
        }
        drink++;
        return DRINKS_MENU.get(menuName);
    }

    public int getAppetizerOrDessertPrice(String menuName) {
        if (APPETIZER_MENU.containsKey(menuName)) {
            appetizer++;
            return APPETIZER_MENU.get(menuName);
        }
        dessert++;
        return DESSERT_MENU.get(menuName);
    }

    public List<Integer> getOrderQuantity() {
        return Arrays.asList(appetizer, main, dessert, drink);
    }
}
