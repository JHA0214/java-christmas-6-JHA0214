package christmas.domain;

import java.util.*;

/* MenuManager
 * 각 메뉴들의 가격을 저장하고 있다.
 * 사용자가 입력한 주문 중 메뉴와 관련된 예외처리를 한다. (없는 메뉴 주문, 음료만 주문)
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

    public int calculateTotalOrderCost(Map<String, Integer> orderList) { //총주문 금액 계산
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

    public int getPrice(String menuName) { //주문에 대한 예외처리 후 문제가 없을시 주문한 메뉴의 가격 반환
        try {
            validate(menuName);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage() + ErrorMessage.ORDER.getmessages());
            return 0;
        }
        return ckeckMenu(menuName);
    }

    public void validate(String menuName) { //없는 메뉴 입력시 예외처리
        try {
            ckeckMenu(menuName);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException(ErrorMessage.ERROR.getmessages());
        }
    }

    public int ckeckMenu(String menuName) { //주문받은 메뉴의 종류 확인 후 가격 반환
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

    public boolean checkIfOnlyDrinks() { //음료만 주문했을 경우 예외처리
        try {
            if (appetizer == 0 && main == 0 && dessert == 0) {
                throw new IllegalArgumentException(ErrorMessage.ERROR.getmessages());
            }

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage() + ErrorMessage.ORDER.getmessages());
            return false;
        }
        return true;
    }

    public List<Integer> getOrderQuantity() {
        return Arrays.asList(appetizer, main, dessert, drink);
    }
}
